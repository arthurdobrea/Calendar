package com.calendar.project.controller;

import com.calendar.project.model.*;
import com.calendar.project.model.dto.UserResource;
import com.calendar.project.model.enums.EventType;
import com.calendar.project.service.*;
import com.calendar.project.model.dto.EventResource;
import com.calendar.project.service.impl.Firebase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.calendar.project.service.UserService;
import com.calendar.project.service.EventService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.calendar.project.model.User;
import org.springframework.web.util.UriComponentsBuilder;

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
    TagService tagService;

    @Autowired
    SecurityService securityService;

    @Autowired
    NotificationService notificationService;


    @RequestMapping(value = "/sendToFirebase",method = RequestMethod.GET)
    public String sendTOfirebase() throws FirebaseException, UnsupportedEncodingException, JacksonUtilityException {
        Event event;
        event = eventService.getEvent(1);

        User user;
        user = userService.getUser(1);

        Notification notification = new Notification();
        notification.setEvent(event);
        notification.setUser(user);


        // get the base-url (ie: 'http://gamma.firebase.com/username')
        String firebase_baseUrl = "https://fir-tutorial-61989.firebaseio.com/";

        // create the firebase
        Firebase firebase = new Firebase( firebase_baseUrl );

        // "PUT" (test-map into the fb4jDemo-root)
        Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
        dataMap.put( "event 1", notification.toString());
        FirebaseResponse response = firebase.put( dataMap );

        return "welcome";
    }

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

    @GetMapping(value = "/date", params = "date", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getEventsByDate(@PathVariable @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String start) throws IOException {
        List<Event> events = eventService.getEventsByDate(start);
        String eventString = eventService.getEventsJson(events);
        return new ResponseEntity<>(eventString, HttpStatus.OK);
    }

    @GetMapping(value = "/period", params = {"firstDate", "secondDate"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getEventsByPeriod(@PathVariable @RequestParam("firstDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String firstDate,
                                                    @PathVariable @RequestParam("secondDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String secondDate) throws IOException {
        List<Event> events = eventService.getEventsByPeriod(firstDate, secondDate);
        String eventString = eventService.getEventsJson(events);
        return new ResponseEntity<>(eventString, HttpStatus.OK);
    }

    @GetMapping(value = "/countEventsByPeriod", params = {"firstDate", "secondDate"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Event>> getEventCountByPeriod(@PathVariable @RequestParam("firstDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String date1,
                                                             @PathVariable @RequestParam("secondDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String date2) {
        List<Event> events = eventService.getEventCountByPeriod(date1, date2);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RequestMapping(value = "/createEventJson", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody void createEvent(@RequestBody EventResource eventResource) {
        Event event = Converter.convert(eventResource);
        event.setAuthor(securityService.findLoggedInUsername());
        eventService.saveEvent(event);
    }

    @RequestMapping(value = "/updateEventJson", params = "id", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void updateEvent(@PathVariable @RequestParam("id") int id, @RequestBody EventResource eventResource) {
        Event firstEvent = eventService.getEvent(id);
        Event event = eventService.updateEventForRest(firstEvent, eventResource);
        eventService.updateEvent(event);
    }

    @RequestMapping(value = "/deleteEventJson", params = {"id"}, method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void deleteEvent(@PathVariable @RequestParam("id") int id) {
        Event event = eventService.getEvent(id);
//        Event event = Converter.convert(eventResource);
        eventService.deleteEvent(event);
    }

    @GetMapping(value = "/allTags", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllTags() throws IOException {
        List<Tag> tags = tagService.getAllTags();
        String tagString = tagService.getTagsJson(tags);
        return new ResponseEntity<>(tagString, HttpStatus.OK);
    }

    @GetMapping(value = "/allTypes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllTypes() throws IOException {
        List<EventType> types = eventService.getEventTypeList();
        ObjectMapper mapper = new ObjectMapper();
        JsonArray typesJsonArr = new JsonArray();
        for (EventType type : types) {
            JsonObject typesAsJson = new JsonObject();
            typesAsJson.addProperty("type", type.toString());
            typesAsJson.addProperty("events", eventService.getEventsByType(type).stream().map(Event::getTitleAndId).collect(Collectors.toList()).toString());
            typesJsonArr.add(typesAsJson);
        }
        String eventsString = typesJsonArr.toString();
        Object json = mapper.readValue(eventsString, Object.class);
        return new ResponseEntity<>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json), HttpStatus.OK);
    }

    @GetMapping(value = "/getEvent", params = {"id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getEvent(@PathVariable @RequestParam("id") int id) throws IOException {
        Event event = eventService.getEvent(id);
        String eventString = eventService.getEventJson(event);
        return new ResponseEntity<>(eventString, HttpStatus.OK);
    }

    @GetMapping(value = "/getUserById", params = {"id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUserById(@PathVariable @RequestParam("id") long id) throws IOException {
        User user = userService.getUser(id);
        String userString = userService.getUserJson(user);
        return new ResponseEntity<>(userString, HttpStatus.OK);
    }

    @GetMapping(value = "/getUserByUsername", params = {"username"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUser(@PathVariable @RequestParam("username") String username) throws IOException {
        User user = userService.findByUsername(username);
        String userString = userService.getUserJson(user);
        return new ResponseEntity<>(userString, HttpStatus.OK);
    }

    @RequestMapping(value = "/editUserJson", params = "username", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void editUser(@PathVariable @RequestParam("username") String username, @RequestBody User user) {
        User firstUser = userService.findByUsername(username);
        User userFinal = userService.updateUserForRest(firstUser, user);
        userService.update(userFinal);
    }

//    @RequestMapping(value = "/loginJson", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    public @ResponseBody void loginJson(@RequestBody User user) {
//
//    }


}
