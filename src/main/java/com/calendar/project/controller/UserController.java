package com.calendar.project.controller;

import com.calendar.project.dao.UserDao;
import com.calendar.project.model.Role;
import com.calendar.project.model.User;
import com.calendar.project.service.RoleService;
import com.calendar.project.service.SecurityService;
import com.calendar.project.service.UserService;
import com.calendar.project.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private RoleService roleService;
    UserDao userDao;

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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){return "index";}

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(ModelMap modelMap) {
        List<User> users = userService.findAllUsers();
        modelMap.addAttribute("users", users);
        modelMap.addAttribute("loggedinuser", getPrincipal());
        return "admin";
    }

    @RequestMapping(value = { "/edit-user-{username}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable String username, ModelMap model) {
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "userEdit";
    }

    @RequestMapping(value = "/edit-user-{username}", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user, BindingResult bindingResult, @PathVariable String username) {
//        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "userEdit";
        }
        userService.updateUser(user);

        return "redirect:/admin";
    }

    @ModelAttribute("list_of_roles")
    public List<Role> initializeProfiles() {
        return roleService.findAll();
    }

    @RequestMapping(value = { "/delete-user-{username}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return "redirect:/list";
    }

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
