package com.calendar.project.controller;

import com.calendar.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.calendar.project.model.User;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class JSONController {

        @Autowired
        UserService userService;

        @RequestMapping(value="/all", method = RequestMethod.GET)
        public @ResponseBody List<User> getUserInJSON() {
            return userService.getAllUsers();
        }

}
