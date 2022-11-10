package com.example.csci526prototype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sessions {
    private String creator;
    private String muscles;
    private String name;
    private String desc;
    private String date;
    private String time;
    private String location;
    private List<String> participants;

    public Sessions(String creator, String muscles, String name, String desc, String date, String time, String location, List<String> participants) {
        this.creator = creator;
        this.muscles = muscles;
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.time = time;
        this.location = location;
        this.participants = participants;
    }

    public Sessions() {
    }

    @Override
    public String toString() {
        return "Sessions{" +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", participants=" + participants +
                '}';
    }

    // Getters
    public String getCreator() {
        return creator;
    }

    public String getMuscles() {
        return muscles;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public List<String> getParticipants() {
        return participants;
    }

    // Setters
    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setMuscles(String muscles) {
        this.muscles = muscles;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }
}
