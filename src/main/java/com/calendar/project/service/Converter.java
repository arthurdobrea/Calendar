package com.calendar.project.service;

import com.calendar.project.model.*;
import com.calendar.project.model.dto.EventResource;
import com.calendar.project.model.dto.RoleResource;
import com.calendar.project.model.dto.TagResource;
import com.calendar.project.model.dto.UserResource;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by mhristiniuc on 11/19/2017.
 */
public class Converter {

    public static UserResource convert(User user) {
        UserResource ur = new UserResource();
        ur.setId(user.getId());
        ur.setUsername(user.getUsername());
        ur.setFirstname(user.getFirstname());
        ur.setLastname(user.getLastname());
        ur.setEmail(user.getEmail());

//        List<EventResource> events = new ArrayList<>();
//        for(Event e : user.getEvents()){
//            events.add(convert(e));
//        }
        ur.setEvents(null);

//        List<EventResource> eventsOfAuthor = new ArrayList<>();
//        for(Event e : user.getEventsOfAuthor()){
//            eventsOfAuthor.add(convert(e));
//        }
        ur.setEventsOfAuthor(null);

//        Set<RoleResource> roles = new HashSet<>();
//        for(Role r : user.getRoles()) {
//            roles.add(convert(r));
//        }
        ur.setRoles(null);
        return ur;
    }

    public static EventResource convert(Event event){
        EventResource er = new EventResource();
        er.setId(event.getId());
        er.setTitle(event.getTitle());
        er.setEventType(event.getEventType());
        er.setAuthor(convert(event.getAuthor()));
        er.setLocation(event.getLocation());

//        List<UserResource> participantsByEvent = new ArrayList<>();
//        for(User u : event.getParticipants()){
//            participantsByEvent.add(convert(u));
//        }
        er.setParticipants(null);
        er.setStart(event.getStart().toString());
        er.setEnd(event.getEnd().toString());
        er.setEventCreated(event.getEventCreated());
        er.setDescription(event.getDescription());
        Set<TagResource> tags = new HashSet<>();
//        for(Tag t : event.getTags()){
//            tags.add(convert(t));
//        }
        er.setTags(null);

        return er;
    }

    public static RoleResource convert(Role role){
        RoleResource rr = new RoleResource();
        rr.setId(role.getId());
        rr.setName(role.getName());

//        Set<UserResource> users = new HashSet<>();
//        for(User u : role.getUsers()){
//            users.add(convert(u));
//        }
        rr.setUsers(null);

        return rr;
    }

    public static TagResource convert(Tag tag){
        TagResource tr = new TagResource();
        tr.setId(tag.getId());
        tr.setTag(tag.getTag());
        tr.setColor(tag.getColor());

//        Set<EventResource> events = new HashSet<>();
//        for(Event e : tag.getEvents()){
//            events.add(convert(e));
//        }
        tr.setEvents(null);

        return tr;
    }

    public static Event convert(EventResource eventResource){
        Event ev = new Event();
        ev.setId(eventResource.getId());
        ev.setTitle(eventResource.getTitle());
        ev.setStart(eventResource.getStart());
        ev.setEnd(eventResource.getEnd());
        ev.setEventType(eventResource.getEventType());
        ev.setLocation(eventResource.getLocation());
        ev.setDescription(eventResource.getDescription());
        //ev.setParticipants(eventResource.getParticipants());
        return ev;
    }
}
