package com.calendar.project.controller;

import com.calendar.project.dao.UserDao;
import com.calendar.project.mail.EmailSender;
import com.calendar.project.model.Event;
import com.calendar.project.model.EventType;
import com.calendar.project.model.Role;
import com.calendar.project.model.User;
import com.calendar.project.service.EventService;
import com.calendar.project.service.RoleService;
import com.calendar.project.service.RoleService;
import com.calendar.project.service.SecurityService;
import com.calendar.project.service.TagService;
import com.calendar.project.service.UserService;
import com.calendar.project.testDomain.Tag;
import com.calendar.project.validator.EditFormValidator;
import com.calendar.project.validator.EditFormValidator;
import com.calendar.project.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;
import java.util.List;
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
    private TagService tagService;

    @Autowired
    private EditFormValidator editFormValidator;

    @Autowired
    private RoleService roleService;

    //________________________________________________

    @RequestMapping(value = "/getUsernames", method = RequestMethod.GET)
    public @ResponseBody
    List<User> getUsernames(@RequestParam String userName) {
        return simulateSearchResultForUsername(userName);
    }

    @RequestMapping(value = "/getFirstnames", method = RequestMethod.GET)
    public @ResponseBody
    List<User> getFirstName(@RequestParam String firstName) {

        return simulateSearchResultForFirstName(firstName);
    }

    @RequestMapping(value = "/getLastnames", method = RequestMethod.GET)
    public @ResponseBody
    List<User> getLastName(@RequestParam String lastName) {

        return simulateSearchResultForLastName(lastName);
    }

    @RequestMapping(value = "/getEmails", method = RequestMethod.GET)
    public @ResponseBody
    List<User> getEmails(@RequestParam String email) {

        return simulateSearchResultForEmail(email);
    }
    @RequestMapping(value = "/getRoles", method = RequestMethod.GET)
    public @ResponseBody
    List<User> getRoles(@RequestParam String role) {
        return simulateSearchResultForRoles(role);
    }

    private List<User> simulateSearchResultForUsername(String userName) {
        List<User> listOfUsers = userService.findAllUsers();
        List<User> result = new ArrayList<>();
        for (User user : listOfUsers) {
            if (user.getUsername().contains(userName)) {
                result.add(user);
            }
        }
        return result;
    }
    private List<User> simulateSearchResultForFirstName(String firstName) {
        List<User> listOfUsers = userService.findAllUsers();
        List<User> result = new ArrayList<>();
        for (User user : listOfUsers) {
            if (user.getFirstname().contains(firstName)) {
                result.add(user);
            }
        }
        return result;
    }
    private List<User> simulateSearchResultForLastName(String lastName) {
        List<User> listOfUsers = userService.findAllUsers();
        List<User> result = new ArrayList<>();
        for (User user : listOfUsers) {
            if (user.getLastname().contains(lastName)) {
                result.add(user);
            }
        }
        return result;
    }
    private List<User> simulateSearchResultForEmail(String email) {
        List<User> listOfUsers = userService.findAllUsers();
        List<User> result = new ArrayList<>();
        for (User user : listOfUsers) {
            if (user.getEmail().contains(email)) {
                result.add(user);
            }
        }
        return result;
    }
    private List<User> simulateSearchResultForRoles(String role) {
        List<User> listOfUsers = userService.findAllUsers();
        List<User> result = new ArrayList<>();
        for (User user : listOfUsers) {
            if (user.getRoles().contains(role)) {
                result.add(user);
            }
        }
        return result;
    }


    //________________________________________________

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/index";
    }
    @RequestMapping(value = {"/addUser"}, method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("userForm", new User());
        return "addUser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, @PathVariable String id) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "addUser";
        }

        userService.save(userForm);

        return "registrationSuccess";
    }

    @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        // Вася, вот главный метод который отправляет данные на мыло, в классе настороишь его так как нужно.
        //EmailSender.send();
        return "login";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String index(Model model){
        Event event = new Event();
        List<User> participants = userService.getAllUsers().stream().collect(Collectors.toList());
        event.setParticipants(participants);
        model.addAttribute("eventForm", event);
        if (securityService.findLoggedInUsername().equals("anonymousUser")) {
            return "redirect:/login";
        } else
        return "index";}

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String createEvent(@ModelAttribute("eventForm") Event eventForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "index";
        }
        List<User> participants = new LinkedList<>();
        for (User u : eventForm.getParticipants()) {
            u.setId(Long.parseLong(u.getUsername()));
            participants.add(userService.getUser(u.getId()));
        }

        eventForm.setParticipants(participants);
        User user = securityService.findLoggedInUsername();
        eventForm.setAuthor(userService.findByUsername(user.getUsername()));
        eventService.saveEvent(eventForm);
        redirectAttributes.addAttribute("eventId", eventForm.getId());


        return "redirect:/index";
    }


    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public void admin (Model model, HttpServletRequest request) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("request", request);
        model.addAttribute("loggedinuser", securityService.findLoggedInUsername());

    }

    @RequestMapping(value = "/editUser-{usernameFromController}", method = RequestMethod.GET)
    public Model editUser (Model model2, @PathVariable String usernameFromController) {
        User user = userService.findByUsername(usernameFromController);
        model2.addAttribute("user", user);
        model2.addAttribute("edit", true);
        return model2;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
        public String updateUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
            editFormValidator.validate(user, bindingResult);
            for (Role r : user.getRoles()) {
                r.setId(roleService.findRoleIdByValue(r.getName()));
            }
            if (bindingResult.hasErrors()) {
                return "admin";
            }
            userService.updateUser(user);
            return "redirect:/admin";
    }
    @RequestMapping(value = "/userControlPanel", method = RequestMethod.POST)
    public String userControlPanel(@ModelAttribute("userForm") User userForm, Model model) {
        User user = userService.findByUsername(userForm.getUsername());

        user.setFirstname(userForm.getFirstname());
        user.setLastname(userForm.getLastname());
        user.setEmail(userForm.getEmail());

        userService.update(user);

        return "redirect:/index";
    }

    @RequestMapping(value = "/userControlPanel", method = RequestMethod.GET)
    public String userControlPanel(Model model, @ModelAttribute("userForm") User userForm) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userForm = userService.findByUsername(auth.getName());

        model.addAttribute("username", userForm.getUsername());
        model.addAttribute("firstname", userForm.getFirstname());
        model.addAttribute("lastname", userForm.getLastname());
        model.addAttribute("email", userForm.getEmail());

        return "userControlPanel";
    }

    @RequestMapping(value = "/userPage", method = RequestMethod.GET)
    public String showMyEvents(  Model model, User user){
        user = securityService.findLoggedInUsername();
        List<Event> eventsByAuthor = eventService.getEventsByAuthor(user.getId());
        List<Event> eventsByUser = eventService.getEventsByUser(user.getId());
        model.addAttribute("userLabels", user.getSubscriptionByEventTypeAsEnums());
        model.addAttribute("userAuthor", userService.getUser(user.getId()) );
        model.addAttribute("eventsByAuthor", eventsByAuthor);
        model.addAttribute("eventsByUser", eventsByUser);
        model.addAttribute("eventsList", eventService.getEventTypeList());

        return "userPage";
    }

    @RequestMapping(value = "/eventTypeLink", method = RequestMethod.POST)
    public String userPage(Model model,@RequestParam("checkboxName")Set<String> checkboxValue) {
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

        return "userPage";
    }

    @ModelAttribute("list_of_roles")
    public List<Role> initializeProfiles() {
        List<Role> list = roleService.findAll();
        list.remove(3);
        return list;
    }

//    @RequestMapping(value = "/edit-user-{username}", method = RequestMethod.GET)
//    public void editUser(@PathVariable String username, ModelMap model) {
//        User user = userService.findByUsername(username);
//        model.addAttribute("user", user);
//        model.addAttribute("edit", true);
//        model.addAttribute("loggedinuser", securityService.findLoggedInUsername());
////        return "admin";
//    }
//
//    @RequestMapping(value = "/edit-user-{username}", method = RequestMethod.POST)
//    public String updateUser(@ModelAttribute("user") User user, BindingResult bindingResult, @PathVariable String username) {
//        editFormValidator.validate(user, bindingResult);
//        for (Role r : user.getRoles()) {
//            r.setId(roleService.findRoleIdByValue(r.getName()));
//        }
//        if (bindingResult.hasErrors()) {
//            return "admin";
//        }
//        userService.updateUser(user);
//        return "redirect:/admin";
//    }

    @RequestMapping(value = { "/delete-user-{username}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return "admin";
    }

    // is mailing all events to all users when labels are equals to event types.
    @RequestMapping(value = "/mailing", method = RequestMethod.GET)
    public String mailing() {
        return "mailing";
    }

    @RequestMapping(value = "/mailing", method = RequestMethod.POST)
    public String mailing(Model model) {
        userService.mailToAllUsers();
        return "mailing";
    }

    @RequestMapping(value = "/usersTag", method = RequestMethod.GET)
    public String setUsersTag(Model model) {
        model.addAttribute("usersList", userService.getAllUsers());
        model.addAttribute("tagsList", tagService.getTagsTypeList());

        return "usersTags";
    }

    @RequestMapping(value = "/usersTag", method = RequestMethod.POST)
    public String setUsersTag(Model model,@RequestParam("checkboxName")Set<String> checkboxValue,@RequestParam("user")User user) {
        StringBuilder stringBuilder = new StringBuilder();

        for(String ptr: checkboxValue) {
            stringBuilder.append(ptr + ',');
        }
        String tagSet = stringBuilder.toString();

        user.setSubscriptionByTagType(tagSet);

        userService.update(user);

        return "usersTags";
    }
}
