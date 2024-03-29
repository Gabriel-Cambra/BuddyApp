package com.example.csci526prototype;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserModel implements Serializable {
    private int id;
    private String name;
    private String about;
    private String email;
    private ArrayList<Integer> friends;

    public UserModel(int id, String name, String about, String email, ArrayList<Integer> friends) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.email = email;
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

    public ArrayList<Integer> getFriends() {
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

    public void setFriends(ArrayList<Integer> friends) {
        this.friends = friends;
    }
}
