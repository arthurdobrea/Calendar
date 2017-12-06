package com.calendar.project.model.enums;

public enum TagType {

    APPLICATION_MANAGEMENT("Application Management", "#de681b"),
    DEVELOPMENT("Development", "#de251b"),
    TESTING("Testing", "#6f1894"),
    TOWER("Tower", "#dea11b"),
    NBC("NBC", "#737b8a"),
    ALL_STAFF("All Staff", "#213e96");

    private final String view;
    private final String color;

    TagType(String view, String color) {
        this.view = view;
        this.color = color;
    }

    public String view() {
        return view;
    }

    public String color() {
        return color;
    }
}
