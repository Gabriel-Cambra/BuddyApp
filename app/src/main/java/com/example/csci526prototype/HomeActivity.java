package com.example.csci526prototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csci526prototype.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private DBUserHandler userDB;
    private User mainUser;
    private ArrayList<UserModel> users;
    private ArrayList<Sessions> sessions;
    private Button createSession, findSession, beginnerWorkout;
    private EditText username;
    private RecyclerView sessionsList;
    private SessionRVAdapter sessionRVAdapter;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        if(getIntent().getExtras() != null) {
            mainUser = (User) getIntent().getSerializableExtra("mainUser");
            users = (ArrayList<UserModel>) getIntent().getSerializableExtra("users");
        }

        userDB = new DBUserHandler(HomeActivity.this);
        //sessions = sessionsDB. ;
        sessions = userDB.getUserSessions(mainUser.getId());

        sessionRVAdapter = new SessionRVAdapter(sessions,mainUser,HomeActivity.this);

        username = findViewById(R.id.editTextTextPersonName);
        createSession = findViewById(R.id.create_session_btn);
        findSession = findViewById(R.id.FindSessionBtn);
        beginnerWorkout = findViewById(R.id.button2);
        sessionsList = findViewById(R.id.idRVSessions);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeActivity.this, RecyclerView.VERTICAL, false);
        sessionsList.setLayoutManager(linearLayoutManager);

        sessionsList.setAdapter(sessionRVAdapter);
        username.setText(mainUser.getName());

        createSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, create_session.class);
                intent.putExtra("mainUser",mainUser);
                intent.putExtra("users",users);
                startActivity(intent);
            }
        });

        findSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, findSession.class);
                intent.putExtra("mainUser",mainUser);
                intent.putExtra("users",users);
                startActivity(intent);
            }
        });

        beginnerWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Beginner_workouts.class);
                intent.putExtra("mainUser",mainUser);
                intent.putExtra("users",users);
                startActivity(intent);
            }
        });


    }
}