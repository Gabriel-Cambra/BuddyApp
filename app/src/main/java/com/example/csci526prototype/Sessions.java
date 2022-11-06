package com.example.csci526prototype;

import java.util.Arrays;

public class Sessions {
    private String userEmail;
    private String name;
    private String desc;
    private String date;
    private String time;
    private String[] participants;

    public Sessions(String userEmail, String name, String desc, String date, String time, String[] participants) {
        this.userEmail = userEmail;
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.time = time;
        this.participants = participants;
    }

    public Sessions() {
    }

    @Override
    public String toString() {
        return "Sessions{" +
                "userEmail='" + userEmail + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", participants=" + Arrays.toString(participants) +
                '}';
    }

    // Getters
    public String getUserEmail() {
        return userEmail;
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

    public String[] getParticipants() {
        return participants;
    }

    // Setters
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public void setParticipants(String[] participants) {
        this.participants = participants;
    }
}
