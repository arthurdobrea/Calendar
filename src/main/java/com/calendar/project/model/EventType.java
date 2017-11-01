package com.calendar.project.model;

/**
 * Created by mhristiniuc on 10/25/2017.
 */
public enum EventType {

    MEETING ("Meeting"),
    TRAINING ("Training"),
    STAND_UP ("Stand up"),
    OFFLINE ("Offline"),
    TEAM_BUILDING("Team building"),
    WORKSHOP ("Workshop"),
    OTHER ("Other");

    private final String view;
    EventType(String view){
        this.view=view;
    }
    public String view() { return view; }
}
