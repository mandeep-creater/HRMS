package com.hrms.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AttendanceStatus {
    PRESENT, ABSENT, LEAVE, HOLIDAY,HALFDAY,WEEKOFF,
    NOT_JOINED;


    @JsonCreator
    public static Status fromString(String key) {
        if (key == null) return null;
        try {
            return Status.valueOf(key.toUpperCase()); // convert user input to uppercase
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status: " + key);
        }
    }
}
