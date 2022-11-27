package com.example.csci526prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class leg_workouts extends AppCompatActivity {
    private User mainUser;
    private ArrayList<UserModel> users;
    private Button back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leg_workouts);

        if(getIntent().getExtras() != null) {

            mainUser = (User) getIntent().getSerializableExtra("mainUser");
            users = (ArrayList<UserModel>) getIntent().getSerializableExtra("users");
        }

        back = findViewById(R.id.button);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(leg_workouts.this, Beginner_workouts.class);
                intent.putExtra("mainUser",mainUser);
                intent.putExtra("users",users);
                startActivity(intent);
            }
        });
    }
}