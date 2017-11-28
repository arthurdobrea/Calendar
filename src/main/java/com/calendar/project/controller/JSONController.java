package com.calendar.project.controller;


import com.calendar.project.exception.FirebaseException;
import com.calendar.project.exception.JacksonUtilityException;
import com.calendar.project.model.*;
import com.calendar.project.model.enums.EventType;
import com.calendar.project.model.enums.TagType;
import com.calendar.project.service.*;
import com.calendar.project.model.dto.EventResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.calendar.project.service.UserService;
import com.calendar.project.service.EventService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import com.calendar.project.model.User;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/json")
public class JSONController {

    private static final Logger LOGGER = Logger.getLogger(JSONController.class);

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    NotificationService notificationService;


//    @RequestMapping(value = "/sendToFirebase",method = RequestMethod.GET)
//    public String sendTOfirebase() throws FirebaseException, UnsupportedEncodingException, JacksonUtilityException {
//        Event event;
//        event = eventService.getEvent(1);
//
//        User user;
//        user = userService.getUser(1);
//
//        Notification notification = new Notification();
//        notification.setEvent(event);
//        notification.setUser(user);
//
//
//        // get the base-url (ie: 'http://gamma.firebase.com/username')
//        String firebase_baseUrl = "https://fir-tutorial-61989.firebaseio.com/";
//
//        // create the firebase
//        Firebase firebase = new Firebase( firebase_baseUrl );
//
//        // "PUT" (test-map into the fb4jDemo-root)
//        Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
//        dataMap.put( "event 1", notification.toString());
//        FirebaseResponse response = firebase.put( dataMap );
//
//        return "welcome";
//    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllUsers() throws IOException {
        List<User> users = userService.getAllUsers();
        String userString = userService.getUsersJson(users);
        return new ResponseEntity<>(userString, HttpStatus.OK);
    }

    @GetMapping(value = "/allEvents", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllEvents() throws IOException {
        List<Event> events = eventService.getAllEvents();
        String eventString = eventService.getEventsJson(events);
        return new ResponseEntity<>(eventString, HttpStatus.OK);
    }

    @GetMapping(value = "/getUserByUsername", params = {"username"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUser(@PathVariable @RequestParam("username") String username) throws IOException {
        User user = userService.findByUsername(username);
        String userString = userService.getUserJson(user);
        return new ResponseEntity<>(userString, HttpStatus.OK);
    }

    @GetMapping(value = "/searchEvents", params = {"tag", "type", "authorId", "participantId"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchEvents(@PathVariable @RequestParam("tag") TagType tag,
                                               @PathVariable @RequestParam("type") EventType type,
                                               @PathVariable @RequestParam("authorId") Long authorId,
                                               @PathVariable @RequestParam("participantId") Long participantId) throws IOException {
        List<Event> events = eventService.searchEvents(type, tag, authorId, participantId);
        String eventString = eventService.getEventsJson(events);
        return new ResponseEntity<>(eventString, HttpStatus.OK);
    }

}
