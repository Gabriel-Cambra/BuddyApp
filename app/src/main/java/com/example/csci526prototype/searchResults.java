package com.example.csci526prototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class searchResults extends AppCompatActivity {
    private User mainUser;
    private ArrayList<UserModel> users;
    private Button back;
    private TextView username;
    private RecyclerView sessionsList;
    private String date, time, location;
    private Integer muscle;
    DBUserHandler userDB;
    ArrayList<Sessions> sessions = new ArrayList<Sessions>();
    private SearchRVAdapter searchRVAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashboard);
        userDB = new DBUserHandler(this);

        if(getIntent().getExtras() != null) {

            mainUser = (User) getIntent().getSerializableExtra("mainUser");
            users = (ArrayList<UserModel>) getIntent().getSerializableExtra("users");
            date = (String) getIntent().getSerializableExtra("date");
            time = (String) getIntent().getSerializableExtra("time");
            location = (String) getIntent().getSerializableExtra("location");
            muscle = (Integer) getIntent().getSerializableExtra("muscle");

            if (date.isEmpty() && time.isEmpty()){
                sessions = userDB.getSearchSessions(mainUser.getId(), location,true, true, date, time, muscle);
            }else if(date.isEmpty()){
                sessions = userDB.getSearchSessions(mainUser.getId(), location,true, false, date, time, muscle);

            }else if(time.isEmpty()){
                sessions = userDB.getSearchSessions(mainUser.getId(), location,false, true, date, time, muscle);

            }else{
                sessions = userDB.getSearchSessions(mainUser.getId(), location,false, false, date, time, muscle);
            }

        }

        searchRVAdapter = new SearchRVAdapter(sessions, mainUser, searchResults.this);
        username = findViewById(R.id.editTextTextPersonName);
        sessionsList = findViewById(R.id.idRVSessions);
        back = findViewById(R.id.backButton);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(searchResults.this, RecyclerView.VERTICAL, false);
        sessionsList.setLayoutManager(linearLayoutManager);
        sessionsList.setAdapter(searchRVAdapter);
        username.setText(mainUser.getName());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(searchResults.this, findSession.class);
                intent.putExtra("mainUser",mainUser);
                intent.putExtra("users",users);
                startActivity(intent);
            }
        });


    }
}
