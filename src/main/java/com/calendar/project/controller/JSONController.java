package com.calendar.project.controller;

import com.calendar.project.model.Event;
import com.calendar.project.service.UserService;
import com.calendar.project.service.EventService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.calendar.project.model.User;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/json")
public class JSONController {

    private static final Logger LOGGER = Logger.getLogger(JSONController.class);

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value="/allEvents", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RequestMapping(value="/date", params = "date", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Event>> getEventsByDate(@PathVariable @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") String start) {
        List<Event> events = eventService.getEventsByDate(start);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RequestMapping(value="/period", params = {"firstDate", "secondDate"}, method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Event>> getEventsByPeriod(@PathVariable @RequestParam("firstDate") @DateTimeFormat(pattern="yyyy-MM-dd") String firstDate,
                                                         @PathVariable @RequestParam("secondDate") @DateTimeFormat(pattern="yyyy-MM-dd") String secondDate) {
        List<Event> events = eventService.getEventsByPeriod(firstDate, secondDate);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RequestMapping(value="/countEventsByPeriod", params = {"firstDate", "secondDate"}, method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Event>> getEventCountByPeriod(@PathVariable @RequestParam("firstDate") @DateTimeFormat(pattern="yyyy-MM-dd") String date1,
                                                         @PathVariable @RequestParam("secondDate") @DateTimeFormat(pattern="yyyy-MM-dd") String date2)
     {
         List<Event> events = eventService.getEventCountByPeriod(date1, date2);

        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
