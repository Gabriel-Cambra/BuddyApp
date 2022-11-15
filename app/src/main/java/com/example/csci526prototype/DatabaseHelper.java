package com.example.csci526prototype;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    public ArrayList<Sessions> removeSession(ArrayList<Sessions> sessions, int id){
        for(Sessions session:sessions){
            if(session.getId() == id){
                sessions.remove(session);
                break;
            }
        }
        return sessions;
    }

    public ArrayList<UserModel> addUser(ArrayList<UserModel> users, String name, String email, String password){
        int id = users.get(users.size()-1).getId();
        ArrayList<Integer> friend = new ArrayList<>();
        UserModel user = new UserModel(id, name, "", email, password, friend);
        users.add(user);
        return users;
    }

    public ArrayList<Sessions> addSession(ArrayList<Sessions> sessions, String name, String location, String date, String time, String muscles, String description, int creator){
        int id = sessions.get(sessions.size()-1).getId();
        ArrayList<Integer> participants = new ArrayList<>();
        Sessions session = new Sessions(id, creator, muscles, name, description, date, time, location, participants);
        sessions.add(session);
        return sessions;
    }
}
