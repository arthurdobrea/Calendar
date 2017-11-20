package com.calendar.project.controller.resources;

import com.calendar.project.model.Event;
import com.calendar.project.model.enums.TagType;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by mhristiniuc on 11/18/2017.
 */
public class TagResource {

    private Long id;

    private TagType tag;

    private String color;

    private Set<EventResource> events;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TagType getTag() {
        return tag;
    }

    public void setTag(TagType tag) {
        this.tag = tag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set<EventResource> getEvents() {
        return events;
    }

    public void setEvents(Set<EventResource> events) {
        this.events = events;
    }
}
