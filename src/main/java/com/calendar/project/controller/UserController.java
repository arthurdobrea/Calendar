package com.calendar.project.controller;

import com.calendar.project.model.Event;
import com.calendar.project.model.Role;
import com.calendar.project.model.User;
import com.calendar.project.service.EventService;
import com.calendar.project.service.RoleService;
import com.calendar.project.service.SecurityService;
import com.calendar.project.service.UserService;
import com.calendar.project.validator.EditFormValidator;
import com.calendar.project.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private EditFormValidator editFormValidator;

    @Autowired
    private RoleService roleService;

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

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("userForm", new User());

        return "addUser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
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

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(ModelMap modelMap) {
        List<User> users = userService.findAllUsers();

        modelMap.addAttribute("users", users);
        modelMap.addAttribute("loggedinuser", getPrincipal());

        return "admin";
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

    @RequestMapping(value = "/userControlPanel", method = RequestMethod.POST)
    public String userControlPanel(@ModelAttribute("userForm") User userForm, Model model) {
        User user = userService.findByUsername(userForm.getUsername());

        user.setFirstname(userForm.getFirstname());
        user.setLastname(userForm.getLastname());
        user.setEmail(userForm.getEmail());

        userService.update(user);

        return "redirect:/index";
    }

    @RequestMapping(value = "/userPage", method = RequestMethod.GET)
    public String showMyEvents(Model model, User user) {
        user = securityService.findLoggedInUsername();
        List<Event> eventsByAuthor = eventService.getEventsByAuthor(user.getId());
        List<Event> eventsByUser = eventService.getEventsByUser(user.getId());

        model.addAttribute("userAuthor", userService.getUser(user.getId()));
        model.addAttribute("eventsByAuthor", eventsByAuthor);
        model.addAttribute("eventsByUser", eventsByUser);

        return "userPage";
    }

    @RequestMapping(value = "/eventTypeLink", method = RequestMethod.POST)
    public String userPage(Model model, @RequestParam("checkboxName") Set<String> checkboxValue) {
        User user = securityService.findLoggedInUsername();
        StringBuilder stringBuilder = new StringBuilder();

        for (String ptr : checkboxValue) {
            stringBuilder.append(ptr + ',');
        }
        String res = stringBuilder.toString();

        user.setLabels(res);
        user.setLastname("OLEG");

        userService.update(user);

        return "userPage";
    }

    @ModelAttribute("list_of_roles")
    public List<Role> initializeProfiles() {
        return roleService.findAll();
    }

    @RequestMapping(value = "/edit-user-{username}", method = RequestMethod.GET)
    public String editUser(@PathVariable String username, ModelMap model) {
        User user = userService.findByUsername(username);

//        model.addAttribute("list_of_roles", roleService.findAll());
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());

        return "userEdit";
    }

    @RequestMapping(value = "/edit-user-{username}", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user, BindingResult bindingResult, @PathVariable String username) {
        editFormValidator.validate(user, bindingResult);

        for (Role r : user.getRoles()) {
            r.setId(roleService.findRoleIdByValue(r.getName()));
        }

        if (bindingResult.hasErrors()) {
            return "userEdit";
        }

        userService.updateUser(user);

        return "redirect:/admin";
    }

    @RequestMapping(value = "/delete-user-{username}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);

        return "redirect:/admin";
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }

        return userName;
    }
}
