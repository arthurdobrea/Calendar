package com.calendar.project.controller;

import com.calendar.project.controller.resources.Converter;
import com.calendar.project.controller.resources.EventResource;
import com.calendar.project.controller.resources.UserResource;
import com.calendar.project.model.Event;
import com.calendar.project.model.Role;
import com.calendar.project.model.Tag;
import com.calendar.project.service.UserService;
import com.calendar.project.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jersey.repackaged.com.google.common.base.Preconditions;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.calendar.project.model.User;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.Collectors;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/json")
public class JSONController {

    private static final Logger LOGGER = Logger.getLogger(JSONController.class);

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

        @RequestMapping(value = "/users",
                method = RequestMethod.GET,
                produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseStatus(HttpStatus.OK)
        public @ResponseBody List<UserResource> getAllUsers() {
            List<User> users = userService.getAllUsers();

            List<UserResource> userResources = new ArrayList<>();
            for(User u : users) {
                userResources.add(Converter.convert(u));
            }

            return userResources;}
//    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> getAllUsers() throws IOException{
//        List<User> users = userService.getAllUsers();
//        String userString = userService.getUserJson(users);
//        return new ResponseEntity<>(userString, HttpStatus.OK);
//    }



//        @RequestMapping(value = "/events",
//                method = RequestMethod.GET,
//                produces = MediaType.APPLICATION_JSON_VALUE)
//        @ResponseStatus(HttpStatus.OK)
//        public @ResponseBody List<Event> getEventInJSON() {
//            return eventService.getAllEvents();
//        }

    @GetMapping(value="/allEvents", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllEvents() throws IOException{
        List<Event> events = eventService.getAllEvents();
        String eventString = eventService.getEventJson(events);
        return new ResponseEntity<>(eventString, HttpStatus.OK);}
    }

    @GetMapping(value="/date", params = "date", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getEventsByDate(@PathVariable @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") String start) throws IOException {
        List<Event> events = eventService.getEventsByDate(start);
        String eventString = eventService.getEventJson(events);
        return new ResponseEntity<>(eventString, HttpStatus.OK);
    }

    @GetMapping(value="/period", params = {"firstDate", "secondDate"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getEventsByPeriod(@PathVariable @RequestParam("firstDate") @DateTimeFormat(pattern="yyyy-MM-dd") String firstDate,
                                                         @PathVariable @RequestParam("secondDate") @DateTimeFormat(pattern="yyyy-MM-dd") String secondDate) throws IOException{
        List<Event> events = eventService.getEventsByPeriod(firstDate, secondDate);
        String eventString = eventService.getEventJson(events);
        return new ResponseEntity<>(eventString, HttpStatus.OK);
    }

    @GetMapping(value="/countEventsByPeriod", params = {"firstDate", "secondDate"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Event>> getEventCountByPeriod(@PathVariable @RequestParam("firstDate") @DateTimeFormat(pattern="yyyy-MM-dd") String date1,
                                                         @PathVariable @RequestParam("secondDate") @DateTimeFormat(pattern="yyyy-MM-dd") String date2)
     {
         List<Event> events = eventService.getEventCountByPeriod(date1, date2);
         return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registration")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void createUser(@RequestBody User user) {
        Preconditions.checkNotNull(user);
        userService.save(user);
    }



    @RequestMapping(value = "/getEvent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventResource> showEvent(int eventId){
        Event event = eventService.getEvent(eventId);
        EventResource er = Converter.convert(event);

        return new ResponseEntity<>(er, HttpStatus.OK);
    }
}
