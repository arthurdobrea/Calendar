package com.calendar.project.controller;

import com.calendar.project.model.Event;
import com.calendar.project.service.UserService;
import com.calendar.project.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.calendar.project.model.User;
import java.util.List;

@RestController
@RequestMapping("/json")
public class JSONController {

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

//        @RequestMapping(value = "/register", method = RequestMethod.POST,
//                consumes = MediaType.APPLICATION_JSON_VALUE)
//        @ResponseStatus(HttpStatus.CREATED)
//        public void handleJsonPostRequest (@RequestBody User user, Model model) {
//            System.out.println("saving user: "+ user);
//            userService.save(user);
//        }

    @RequestMapping(value = "/users",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<User> getUserInJSON() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/events",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Event> getEventInJSON() {
        return eventService.getAllEvents();
    }
}