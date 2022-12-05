package com.example.csci526prototype;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User extends UserModel implements Serializable {
    private int id;
    private String name;
    private String about;
    private String email;
    private ArrayList<Integer> friends;
    public User(int id, String name, String about, String email, ArrayList<Integer> friends) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.email = email;
        this.friends = friends;
    }

    public User(){
        this.id = 0;
        this.name = "tempname";
        this.about = "temp about";
        this.email = "temp@gmail";
        this.friends = new ArrayList<>();
        friends.add(1);
        friends.add(2);
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
