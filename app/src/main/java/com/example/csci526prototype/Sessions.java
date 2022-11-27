package com.example.csci526prototype;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Sessions implements Serializable {
    private int id;
    private int creator;
    private int muscles;
    private String name;
    private String desc;
    private String date;
    private String time;
    private String location;
    private ArrayList<Integer> participants;

    public Sessions(int id, int creator, int muscles, String name, String desc, String date, String time, String location, ArrayList<Integer> participants) {
        this.id = id;
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
                ", location='" + location + '\'' +
                ", participants=" + participants +
                '}';
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getCreator() {
        return creator;
    }

    public int getMuscles() {
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

    public ArrayList<Integer> getParticipants() {
        return participants;
    }

    // Setters
    public void setCreator(int creator) {
        this.creator = creator;
    }

    public void setMuscles(int muscles) {
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

    public void setParticipants(ArrayList<Integer> participants) {
        this.participants = participants;
    }
}
