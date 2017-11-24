package com.calendar.project.controller;

import com.calendar.project.model.Event;
import com.calendar.project.model.Notification;
import com.calendar.project.model.Role;
import com.calendar.project.model.User;
import com.calendar.project.model.dto.UserResource;
import com.calendar.project.service.EventService;
import com.calendar.project.service.*;
import com.calendar.project.validator.EditFormValidator;
import com.calendar.project.validator.UserResourceValidator;
import com.calendar.project.validator.UserValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.*;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserResourceValidator userResourceValidator;

    @Autowired
    private TagService tagService;

    @Autowired
    private EditFormValidator editFormValidator;

    @Autowired
    private RoleService roleService;

    @Autowired
    private NotificationService notificationService;


    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        LOGGER.info("Request of \"/registration\" page GET");
        model.addAttribute("userForm", new UserResource());

        LOGGER.info("Opening of \"/registration\" page");
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") @Valid UserResource userForm,
                               BindingResult bindingResult)
            {
        LOGGER.info("Request of \"/registration\" page POST");

        userResourceValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            LOGGER.info("Opening of \"/registration\" page");
            return "registration";
        }
        User user = Converter.convert(userForm);
        userService.save(user);

        securityService.autoLogin(userForm.getUsername(), userForm.getPassword());

        LOGGER.info("Redirect to \"/index\" page");
        return "redirect:/index";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(Model model) {
        LOGGER.info("Request of \"/addUser\" page GET");
        model.addAttribute("userForm", new User());

        LOGGER.info("Opening of \"/addUser\" page");
        return "addUser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, @PathVariable String id) {
        LOGGER.info("Request of \"/addUser\" page POST");
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            LOGGER.info("Opening of \"/addUser\" page");
            return "addUser";
        }

        userService.save(userForm);

        LOGGER.info("Opening of \"registrationSuccess\" page");
        return "/registrationSuccess";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        LOGGER.info("Request of \"/login\" page GET");

        if(securityService.findLoggedInUsername() != null) {
            return "redirect:/index";
        }

        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        // Вася, вот главный метод который отправляет данные на мыло, в классе настороишь его так как нужно.
        //EmailSender.send();
        LOGGER.info("Opening of \"/login\" page");
        return "login";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model) {
        List<Notification> checkedNotifications = notificationService.getChekedEvents(securityService.findLoggedInUsername());
        List<Notification> uncheckedNotifications = notificationService.getUnchekedEvents(securityService.findLoggedInUsername());

        model.addAttribute("checkedNotifications", checkedNotifications);
        model.addAttribute("uncheckedNotifications", uncheckedNotifications);

        LOGGER.info("Request of \"/welcome\" page GET");
        LOGGER.info("Opening of \"/welcome\" page");
        return "welcome";
    }

    @RequestMapping(value = { "/index", "/"}, method = RequestMethod.GET)
    public String index(Model model){
        LOGGER.info("Request of \"/index\" page GET");

        Event event = new Event();
        List<User> participants = userService.getAllUsers().stream().collect(Collectors.toList());
        event.setParticipants(participants);
        model.addAttribute("eventForm", event);
        model.addAttribute("events", eventService.getEvent(event.getId()));

        LOGGER.info("Opening of \"/index\" page");
        return "index";
    }

    @RequestMapping(value = { "/index", "/"}, method = RequestMethod.POST)
    public String createEvent(@ModelAttribute("eventForm") Event eventForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        LOGGER.info("Request of \"/index\" page POST");
        if (bindingResult.hasErrors()) {
            LOGGER.info("Opening of \"/index\" page");
            return "index";
        }
        List<User> participants = new ArrayList<>();
        for (User u : eventForm.getParticipants()) {
            u.setId(Long.parseLong(u.getUsername()));
            participants.add(userService.getUser(u.getId()));
        }

        eventForm.setParticipants(participants);
        User user = securityService.findLoggedInUsername();
        eventForm.setAuthor(userService.findByUsername(user.getUsername()));
        eventService.saveEvent(eventForm);
        redirectAttributes.addAttribute("eventId", eventForm.getId());

        LOGGER.info("Redirect to \"/index\" page");
        return "redirect:/index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(ModelMap modelMap, HttpServletRequest request) {
        LOGGER.info("Request of \"/admin\" page GET");

        List<User> users = userService.findAllUsers();

        modelMap.addAttribute("users", users);
        modelMap.addAttribute("request", request);
        modelMap.addAttribute("loggedinuser", securityService.findLoggedInUsername());

        LOGGER.info("Opening of \"/admin\" page");

        return "admin";
    }

    @RequestMapping(value = "/userControlPanel", method = RequestMethod.GET)
    public String userControlPanel(Model model, @ModelAttribute("userForm") User userForm) {
        LOGGER.info("Request of \"/userControlPanel\" page GET");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userForm = userService.findByUsername(auth.getName());
        model.addAttribute("username", userForm.getUsername());
        model.addAttribute("firstname", userForm.getFirstname());
        model.addAttribute("lastname", userForm.getLastname());
        model.addAttribute("email", userForm.getEmail());

        LOGGER.info("Opening of \"/userControlPanel\" page");

        return "userControlPanel";
    }

    @RequestMapping(value = "/userControlPanel", method = RequestMethod.POST)
    public String userControlPanel(@ModelAttribute("userForm") User userForm, Model model) {
        LOGGER.info("Request of \"/userControlPanel\" page POST");
        User user = userService.findByUsername(userForm.getUsername());
        user.setFirstname(userForm.getFirstname());
        user.setLastname(userForm.getLastname());
        user.setEmail(userForm.getEmail());

        userService.update(user);
        LOGGER.info("Redirect to \"/index\" page");
        return "redirect:/index";
    }


    @RequestMapping(value = "/userPage", method = RequestMethod.GET)
    public String showMyEvents(  Model model, User user){
        LOGGER.info("Request of \"/userPage\" page GET");
        user = securityService.findLoggedInUsername();
        List<Event> eventsByAuthor = eventService.getEventsByAuthor(user.getId());
        List<Event> eventsByUser = eventService.getEventsByUser(user.getId());
        model.addAttribute("userLabels", user.getSubscriptionByEventTypeAsEnums());
        model.addAttribute("userAuthor", userService.getUser(user.getId()) );
        model.addAttribute("eventsByAuthor", eventsByAuthor);
        model.addAttribute("eventsByUser", eventsByUser);
        model.addAttribute("eventsList", eventService.getEventTypeList());
        model.addAttribute("image", Base64.encode(userService.getUser(user.getId()).getImage()));
        LOGGER.info("Opening of \"/userPage\" page");
        return "userPage";
    }

    @RequestMapping(value = "/eventTypeLink", method = RequestMethod.POST)
    public String userPage(Model model,@RequestParam("checkboxName")Set<String> checkboxValue) {
        LOGGER.info("Request of \"/eventTypeLink\" page POST");
        User user = securityService.findLoggedInUsername();

        StringBuilder stringBuilder = new StringBuilder();
        for(String ptr: checkboxValue) {
            if (!ptr.equals("")) {
                stringBuilder.append(ptr + ',');
            }
        }

        String res = stringBuilder.toString();
        user.setSubscriptionByEventType(res);

        userService.update(user);
        //is mailing all events to current user  by his labels and event types.
        userService.mailToUser(user);
        LOGGER.info("Opening of \"/userPage\" page");
        return "userPage";
    }

    @ModelAttribute("list_of_roles")
    public List<Role> initializeProfiles() {
        List<Role> list = roleService.findAll();
        list.remove(3);     // to clarify
        LOGGER.info("Return list of roles");
        return list;
    }

    @RequestMapping(value = "/edit-user-{username}", method = RequestMethod.GET)
    public String editUser(@PathVariable String username, ModelMap model) {
        LOGGER.info("Request of \"/edit-user-{username}\" page GET");
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", securityService.findLoggedInUsername());
        LOGGER.info("Opening of \"/userEdit\" page");
        return "userEdit";
    }

    @RequestMapping(value = "/edit-user-{username}", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user, BindingResult bindingResult, @PathVariable String username) {
        LOGGER.info("Request of \"/edit-user-{username}\" page POST");
        editFormValidator.validate(user, bindingResult);

        for (Role r : user.getRoles()) {
            r.setId(roleService.findRoleIdByValue(r.getName()));
        }

        if (bindingResult.hasErrors()) {
            LOGGER.info("Opening of \"/userEdit\" page");
            return "userEdit";
        }

        userService.updateUser(user);
        LOGGER.info("Redirect to \"/admin\" page");
        return "redirect:/admin";
    }

    @RequestMapping(value = "/delete-user-{username}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable String username) {
        LOGGER.info("Request of \"/delete-user-{username}\" page GET");
        userService.deleteUserByUsername(username);
        LOGGER.info("Redirect to \"/admin\" page");
        return "redirect:/admin";
    }

    // is mailing all events to all users when labels are equals to event types.
    @RequestMapping(value = "/mailing", method = RequestMethod.GET)
    public String mailing() {
        LOGGER.info("Request of \"/mailing\" page GET");
        LOGGER.info("Opening of \"/mailing\" page");
        return "mailing";
    }

    @RequestMapping(value = "/mailing", method = RequestMethod.POST)
    public String mailing(Model model) {
        LOGGER.info("Request of \"/mailing\" page POST");
        userService.mailToAllUsers();
        LOGGER.info("Opening of \"/mailing\" page");
        return "mailing";
    }

    @RequestMapping(value = "/usersTag", method = RequestMethod.GET)
    public String setUsersTag(Model model) {
        LOGGER.info("Request of \"/usersTag\" page GET");
        model.addAttribute("usersList", userService.getAllUsers());
        model.addAttribute("tagsList", tagService.getTagsTypeList());
        LOGGER.info("Opening of \"/usersTags\" page");
        return "usersTags";
    }

    @RequestMapping(value = "/usersTag", method = RequestMethod.POST)
    public String setUsersTag(Model model,@RequestParam("checkboxName")Set<String> checkboxValue,@RequestParam("user")User user) {
        LOGGER.info("Request of \"/usersTag\" page POST");
        StringBuilder stringBuilder = new StringBuilder();

        for(String ptr: checkboxValue) {
            stringBuilder.append(ptr + ',');
        }
        String tagSet = stringBuilder.toString();

        user.setSubscriptionByTagType(tagSet);

        userService.update(user);
        LOGGER.info("Opening of \"/usersTags\" page");
        return "usersTags";
    }
}
