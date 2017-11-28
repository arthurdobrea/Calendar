package com.calendar.project.controller;

import com.calendar.project.model.Event;
import com.calendar.project.model.Tag;
import com.calendar.project.model.User;
import com.calendar.project.model.dto.EventResource;
import com.calendar.project.model.enums.EventType;
import com.calendar.project.model.enums.TagType;
import com.calendar.project.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import com.calendar.project.service.UserService;
import com.calendar.project.service.EventService;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

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

    @Autowired
    UserDetailsService userDetailsService;

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

    @RequestMapping(value = "/createEvent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody void createEvent(@RequestBody EventResource eventResource) {

        Event event = Converter.convert(eventResource);
        event.setAuthor(securityService.findLoggedInUsername());
        eventService.saveEvent(event);
    }
    @RequestMapping(value = "/updateEvent", params = "id", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity updateEvent(@PathVariable @RequestParam("id") int id, @RequestBody EventResource eventResource) {
        User user = securityService.findLoggedInUsername();
        Event firstEvent = eventService.getEvent(id);
        Event event = eventService.updateEventForRest(firstEvent, eventResource);
        if ((!user.getId().equals(event.getAuthor().getId()))&&
                (!user.getId().equals(userService.findByUsername("admin").getId())))
            return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
        eventService.updateEvent(event);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteEvent", params = {"id"}, method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity deleteEvent(@PathVariable @RequestParam("id") int id) {
        User user = securityService.findLoggedInUsername();
        Event event = eventService.getEvent(id);
        if ((!user.getId().equals(event.getAuthor().getId()))&&
                (!user.getId().equals(userService.findByUsername("admin").getId())))
            return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
        eventService.deleteEvent(event);
        return new ResponseEntity(HttpStatus.OK);
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

    @RequestMapping(value = "/editUser", params = "username", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity editUser(@PathVariable @RequestParam("username") String username, @RequestBody User user) {
        User firstUser = userService.findByUsername(username);
        User userFinal = userService.updateUserForRest(firstUser, user);
        User user1 = securityService.findLoggedInUsername();
        if ((!user1.getId().equals(userFinal.getId()))&&
                (!user1.getId().equals(userService.findByUsername("admin").getId())))
            return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
        userService.update(userFinal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void createUser(@RequestBody User user) throws IOException {
        userService.save(user);
    }

    @RequestMapping(value = "/deleteUser", params = "username", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity deleteUser(@PathVariable @RequestParam("username") String username) throws IOException {
        User user1 = securityService.findLoggedInUsername();
        User firstUser = userService.findByUsername(username);
        if ((!user1.getId().equals(firstUser.getId()))&&
                (!user1.getId().equals(userService.findByUsername("admin").getId())))
            return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
        userService.deleteUserByUsername(firstUser.getUsername());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getEventsByUser", params = {"id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getEventsByUser(@PathVariable @RequestParam("id") long id) throws IOException {
        List<Event> events = eventService.getEventsByUser(id);
        String eventString = eventService.getEventsJson(events);
        return new ResponseEntity<>(eventString, HttpStatus.OK);
    }

    @GetMapping(value = "/getEventsByAuthor", params = {"id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getEventsByAuthor(@PathVariable @RequestParam("id") long id) throws IOException {
        List<Event> events = eventService.getEventsByAuthor(id);
        String eventString = eventService.getEventsJson(events);
        return new ResponseEntity<>(eventString, HttpStatus.OK);
    }

    @GetMapping(value = "/getEventsByTag", params = {"tag"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getEventsByTag(@PathVariable @RequestParam("tag") TagType tag) throws IOException {
        List<Event> events = eventService.getEventsByTag(tag);
        String eventString = eventService.getEventsJson(events);
        return new ResponseEntity<>(eventString, HttpStatus.OK);
    }

    @GetMapping(value = "/getEventsByType", params = {"type"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getEventsByType(@PathVariable @RequestParam("type") EventType type) throws IOException {
        List<Event> events = eventService.getEventsByType(type);
        String eventString = eventService.getEventsJson(events);
        return new ResponseEntity<>(eventString, HttpStatus.OK);
    }
}

