package com.calendar.project.controller;

import com.calendar.project.model.Event;
import com.calendar.project.model.User;
import com.calendar.project.service.EventService;
import com.calendar.project.service.SecurityService;
import com.calendar.project.service.UserService;
import com.calendar.project.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    EventService eventServiceImpl;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

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

    @RequestMapping(value = {"/login","/"}, method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){return "index";}

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "admin";
    }

    @RequestMapping(value = "/userControlPanel", method = RequestMethod.GET)
    public String userControlPanel(Model model, @ModelAttribute("userForm") User userForm) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());

        model.addAttribute("username", user.getUsername());
        model.addAttribute("firstname", user.getFirstname());
        model.addAttribute("lastname", user.getLastname());
        model.addAttribute("email", user.getEmail());

        return "userControlPanel";
    }

    @RequestMapping(value = "/userControlPanel", method = RequestMethod.POST)
    public String userControlPanel(@ModelAttribute("userForm") User userForm, Model model) {
        model.addAttribute("userForm", new User());

        userService.update(userForm);

        return "redirect:/index";
    }
}
