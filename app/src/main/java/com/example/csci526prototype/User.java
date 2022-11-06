package com.example.csci526prototype;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String about;
    private String email;
    private String password;
    private List<String> friends;
    public User(String name, String about, String email, String password, List<String> friends) {
        this.name = name;
        this.about = about;
        this.email = email;
        this.password = password;
        this.friends = friends;
    }

    public User(){
        this.name = "tempname";
        this.about = "temp about";
        this.email = "temp@gmail";
        this.password = "pass123";
        this.friends = new ArrayList<>();
        friends.add("temp friend 1");
        friends.add("temp friend 2");
    }

    //getters
    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getFriends() {
        return friends;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
