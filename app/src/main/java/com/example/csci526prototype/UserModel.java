package com.example.csci526prototype;

import java.util.Arrays;
import java.util.List;

public class UserModel {
    private int id;
    private String name;
    private String about;
    private String email;
    private String password;
    private List<Integer> friends;

    public UserModel(int id, String name, String about, String email, String password, List<Integer> friends) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.email = email;
        this.password = password;
        this.friends = friends;
    }

    public UserModel(){

    }

    @Override
    public String toString() {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", friends=" + friends +
                '}';
    }

    //getters
    public int getId(){
        return id;
    }

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

    public List<Integer> getFriends() {
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

    public void setFriends(List<Integer> friends) {
        this.friends = friends;
    }
}
