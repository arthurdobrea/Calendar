package com.calendar.project.model;

/**
 * Created by mhristiniuc on 10/25/2017.
 */
public enum TagType {

    AM_STREAM ("AM Stream", "ORANGE"),
    DEVELOPMENT ("Development", "RED"),
    TESTING ("Testing", "VIOLET"),
    TOWER ("Tower", "YELLOW"),
    NBC("NBC", "GREY"),
    ALL_STAFF ("All Staff", "BLUE");

    private final String view;
    private final String color;
    TagType(String view,String color){
        this.view=view;
        this.color=color;
    }
    public String view() { return view; }
    public String color() { return color; }
}
