package com.calendar.project.controller;

import com.calendar.project.service.MobilePushNotificationsService;
import com.calendar.project.model.*;
import com.calendar.project.model.enums.EventType;
import com.calendar.project.model.enums.TagType;
import com.calendar.project.service.*;
import com.calendar.project.service.UserService;
import com.calendar.project.service.EventService;
import com.calendar.project.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import java.util.*;

@RestController
@RequestMapping("/json")
public class JSONController {

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    MobilePushNotificationsService mobilePushNotificationsService;

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

    @GetMapping(value = "/getParticipantsByEvent", params = {"id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getParticipants(@PathVariable @RequestParam("id") int id) throws IOException {
        List<User> users = eventService.getParticipantsByEvent(id);
        String usersString = userService.getUsersJson(users);

        return new ResponseEntity<>(usersString, HttpStatus.OK);
    }
}