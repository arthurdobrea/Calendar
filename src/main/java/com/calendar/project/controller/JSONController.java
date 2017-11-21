package com.calendar.project.controller;

import com.calendar.project.controller.resources.Converter;
import com.calendar.project.controller.resources.EventResource;
import com.calendar.project.controller.resources.UserResource;
import com.calendar.project.model.Event;
import com.calendar.project.service.UserService;
import com.calendar.project.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
import java.util.stream.Collectors;

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

            return userResources;
    }



//        @RequestMapping(value = "/events",
//                method = RequestMethod.GET,
//                produces = MediaType.APPLICATION_JSON_VALUE)
//        @ResponseStatus(HttpStatus.OK)
//        public @ResponseBody List<Event> getEventInJSON() {
//            return eventService.getAllEvents();
//        }

    @RequestMapping(value="/allEvents", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllEvents() {
        List<Event> events = eventService.getAllEvents();

        JsonArray eventsJsonArr = new JsonArray();
        for (Event e : events) {
            JsonObject eventAsJson = new JsonObject();
            eventAsJson.addProperty("id", e.getId());
            eventAsJson.addProperty("title", e.getTitle());
            eventAsJson.addProperty("start", e.getStart().toString());
            eventAsJson.addProperty("end", e.getEnd().toString());
            eventAsJson.addProperty("participants", e.getParticipants().stream().map(User::getFullName).collect(Collectors.toSet()).toString());
            eventsJsonArr.add(eventAsJson);
        }

        return new ResponseEntity<>(eventsJsonArr.toString(), HttpStatus.OK);
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

    @RequestMapping(value = "/getEvent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventResource> showEvent(int eventId){
        Event event = eventService.getEvent(eventId);
        EventResource er = Converter.convert(event);

        return new ResponseEntity<>(er, HttpStatus.OK);
    }
}
