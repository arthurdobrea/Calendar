package com.calendar.project.controller;

import com.calendar.project.mail.EmailSender;
import com.calendar.project.model.Event;
import com.calendar.project.model.EventType;
import com.calendar.project.model.User;
import com.calendar.project.service.EventService;
import com.calendar.project.service.SecurityService;
import com.calendar.project.service.UserService;
import com.calendar.project.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

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
    public String admin() {
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

    @RequestMapping(value = "/userPage", method = RequestMethod.GET)
    public String showMyEvents(  Model model, User user){
        user = securityService.findLoggedInUsername();
        List<Event> eventsByAuthor = eventService.getEventsByAuthor(user.getId());
        List<Event> eventsByUser = eventService.getEventsByUser(user.getId());
        model.addAttribute("userAuthor", userService.getUser(user.getId()) );
        model.addAttribute("eventsByAuthor", eventsByAuthor);
        model.addAttribute("eventsByUser", eventsByUser);

        return "userPage";
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

    @RequestMapping(value = "/eventTypeLink", method = RequestMethod.POST)
    public String userPage(Model model,@RequestParam("checkboxName")Set<String> checkboxValue) {
        User user = securityService.findLoggedInUsername();
        StringBuilder stringBuilder = new StringBuilder();
        for(String ptr: checkboxValue){
            stringBuilder.append(ptr + ',');
        }
        String res = stringBuilder.toString();
        user.setLabels(res);
        user.setLastname("OLEG");
        userService.update(user);

        return "userPage";
    }
}
