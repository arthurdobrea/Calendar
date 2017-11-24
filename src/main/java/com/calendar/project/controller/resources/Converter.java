package com.calendar.project.controller.resources;

import com.calendar.project.model.Event;
import com.calendar.project.model.Role;
import com.calendar.project.model.Tag;
import com.calendar.project.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        er.setStart(event.getStart());
        er.setEnd(event.getEnd());
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
}
