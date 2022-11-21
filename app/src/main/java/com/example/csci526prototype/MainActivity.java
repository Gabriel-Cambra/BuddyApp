package com.example.csci526prototype;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.csci526prototype.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper = new DatabaseHelper();
    private ActivityMainBinding binding;
    private ArrayList<Sessions> sessions = new ArrayList<>();
    private ArrayList<UserModel> users = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();


        try {
            JSONObject jsonUsers = new JSONObject(JsonData("Users.json"));
            JSONArray jsonArrayUsers = jsonUsers.getJSONArray("users");
            for (int i = 0; i < jsonArrayUsers.length(); i++){
                JSONObject tempData = jsonArrayUsers.getJSONObject(i);
                JSONArray tempFriends = tempData.getJSONArray("friends");
                List<Integer> friends = new ArrayList<>();
                for (int j = 0; j < tempFriends.length(); j++){
                    friends.add(tempFriends.getJSONObject(j).getInt("id"));
                    System.out.println(friends.get(j));
                }

                UserModel tempUser = new UserModel(tempData.getInt("id"), tempData.getString("name"), tempData.getString("about"), tempData.getString("email"), tempData.getString("password"), friends);
                users.add(tempUser);
            }
            JSONObject jsonSessions = new JSONObject(JsonData("Sessions.json"));
            JSONArray jsonArraySessions = jsonSessions.getJSONArray("sessions");
            for (int i = 0; i < jsonArraySessions.length(); i++){
                JSONObject tempData = jsonArraySessions.getJSONObject(i);
                JSONArray tempParticipants = tempData.getJSONArray("participants");
                List<Integer> participants = new ArrayList<>();
                for (int j = 0; j < tempParticipants.length(); j++){
                    participants.add(tempParticipants.getJSONObject(j).getInt("id"));
                    System.out.println(participants.get(j));
                }

                Sessions tempSession = new Sessions(tempData.getInt("id"), tempData.getInt("creator"), tempData.getString("muscles"), tempData.getString("name"), tempData.getString("description"), tempData.getString("date"), tempData.getString("time"),tempData.getString("location"), participants);
                sessions.add(tempSession);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String JsonData(String file) {
        String json = null;
        try {
            InputStream inputStream = getAssets().open(file);
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    public void removeSession(int id){
        sessions = databaseHelper.removeSession(sessions, id);
    }
    public void addUser(String name, String email, String password){
        users = databaseHelper.addUser(users, name, email, password);
    }
    public void addSession(String name, String location, String date, String time, String muscles, String description, int creator){
        sessions = databaseHelper.addSession(sessions, name, location, date, time, muscles, description, creator);
    }


}