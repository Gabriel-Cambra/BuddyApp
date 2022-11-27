package com.example.csci526prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Beginner_workouts extends AppCompatActivity {
    private User mainUser;
    private ArrayList<UserModel> users;
    private Button chest, arm, back, leg, ret;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beginner_workouts);


        if(getIntent().getExtras() != null) {

            mainUser = (User) getIntent().getSerializableExtra("mainUser");
            users = (ArrayList<UserModel>) getIntent().getSerializableExtra("users");
        }

        chest = findViewById(R.id.viewBtn1);
        arm = findViewById(R.id.viewBtn2);
        back = findViewById(R.id.viewBtn3);
        leg = findViewById(R.id.viewBtn4);
        ret = findViewById(R.id.button);

        chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beginner_workouts.this, chest_workouts.class);
                intent.putExtra("mainUser",mainUser);
                intent.putExtra("users",users);
                startActivity(intent);
            }
        });

        arm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beginner_workouts.this, arm_workouts.class);
                intent.putExtra("mainUser",mainUser);
                intent.putExtra("users",users);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beginner_workouts.this, back_workouts.class);
                intent.putExtra("mainUser",mainUser);
                intent.putExtra("users",users);
                startActivity(intent);
            }
        });

        leg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beginner_workouts.this, leg_workouts.class);
                intent.putExtra("mainUser",mainUser);
                intent.putExtra("users",users);
                startActivity(intent);
            }
        });

        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beginner_workouts.this, HomeActivity.class);
                intent.putExtra("mainUser",mainUser);
                intent.putExtra("users",users);
                startActivity(intent);
            }
        });

    }
}