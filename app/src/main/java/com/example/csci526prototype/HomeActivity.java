package com.example.csci526prototype;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csci526prototype.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    DBUserHandler userDB;
    DBSessionsHandler sessionsDB;
    DBFriendsHandler friendsDB;
    DBParticipantsHandler participantsDB;
    User mainUser;
    ArrayList<UserModel> users;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        if(getIntent().getExtras() != null) {
//            userDB = (DBUserHandler) getIntent().getSerializableExtra("userDB");
//            sessionsDB = (DBSessionsHandler) getIntent().getSerializableExtra("sessionsDB");
//            friendsDB = (DBFriendsHandler) getIntent().getSerializableExtra("friendsDB");
//            participantsDB = (DBParticipantsHandler) getIntent().getSerializableExtra("participantsDB");
            mainUser = (User) getIntent().getSerializableExtra("mainUser");
            users = (ArrayList<UserModel>) getIntent().getSerializableExtra("users");
        }
        userDB = new DBUserHandler(HomeActivity.this);
        userDB.readUsers();

    }
}