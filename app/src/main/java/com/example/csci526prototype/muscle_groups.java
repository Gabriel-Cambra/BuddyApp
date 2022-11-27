package com.example.csci526prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class muscle_groups extends AppCompatActivity {
    private User mainUser;
    private ArrayList<UserModel> users;
    int muscle;
    String date, time, name, desc;
    Integer location;
    private Boolean find;
    private Button backButton, confirm;
    private TextView arm1, arm2, arm3, arm4, chest1, leg1, back1;
    private ImageView legs, arms, chest, back;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.muscle_groups);

        if(getIntent().getExtras() != null) {

            mainUser = (User) getIntent().getSerializableExtra("mainUser");
            users = (ArrayList<UserModel>) getIntent().getSerializableExtra("users");
            date = (String) getIntent().getSerializableExtra("date");
            time = (String) getIntent().getSerializableExtra("time");
            name = (String) getIntent().getSerializableExtra("name");
            desc = (String) getIntent().getSerializableExtra("desc");
            location = (Integer) getIntent().getSerializableExtra("location");
            muscle = (Integer) getIntent().getSerializableExtra("muscle");
            location = (Integer) getIntent().getSerializableExtra("location");
            find = (Boolean) getIntent().getSerializableExtra("find");
            if (find == null){
                find = false;
            }
        }

        backButton = findViewById(R.id.backButton);
        confirm = findViewById(R.id.button5);
        arm1 = findViewById(R.id.arm1);
        arm2 = findViewById(R.id.arm2);
        arm3 = findViewById(R.id.arm3);
        arm4 = findViewById(R.id.arm4);
        chest1 = findViewById(R.id.chest1);
        leg1 = findViewById(R.id.leg1);
        back1 = findViewById(R.id.back1);

        arms = findViewById(R.id.armImage);
        chest = findViewById(R.id.chestImage);
        legs = findViewById(R.id.legImage);
        back = findViewById(R.id.backImage);

        arms.setVisibility(View.INVISIBLE);
        chest.setVisibility(View.INVISIBLE);
        legs.setVisibility(View.INVISIBLE);
        back.setVisibility(View.INVISIBLE);

        switch (muscle){
            case 1:
                arms.setVisibility(View.VISIBLE);
                break;
            case 2:
                chest.setVisibility(View.VISIBLE);
                break;
            case 3:
                legs.setVisibility(View.VISIBLE);
                break;
            case 4:
                back.setVisibility(View.VISIBLE);
                break;
        }

        arm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arms.setVisibility(View.VISIBLE);
                chest.setVisibility(View.INVISIBLE);
                legs.setVisibility(View.INVISIBLE);
                back.setVisibility(View.INVISIBLE);

                muscle = 1;
            }
        });
        arm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arms.setVisibility(View.VISIBLE);
                chest.setVisibility(View.INVISIBLE);
                legs.setVisibility(View.INVISIBLE);
                back.setVisibility(View.INVISIBLE);

                muscle = 1;
            }
        });
        arm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arms.setVisibility(View.VISIBLE);
                chest.setVisibility(View.INVISIBLE);
                legs.setVisibility(View.INVISIBLE);
                back.setVisibility(View.INVISIBLE);

                muscle = 1;
            }
        });
        arm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arms.setVisibility(View.VISIBLE);
                chest.setVisibility(View.INVISIBLE);
                legs.setVisibility(View.INVISIBLE);
                back.setVisibility(View.INVISIBLE);

                muscle = 1;
            }
        });
        chest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arms.setVisibility(View.INVISIBLE);
                chest.setVisibility(View.VISIBLE);
                legs.setVisibility(View.INVISIBLE);
                back.setVisibility(View.INVISIBLE);

                muscle = 2;
            }
        });
        leg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arms.setVisibility(View.INVISIBLE);
                chest.setVisibility(View.INVISIBLE);
                legs.setVisibility(View.VISIBLE);
                back.setVisibility(View.INVISIBLE);

                muscle = 3;
            }
        });
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arms.setVisibility(View.INVISIBLE);
                chest.setVisibility(View.INVISIBLE);
                legs.setVisibility(View.INVISIBLE);
                back.setVisibility(View.VISIBLE);

                muscle = 4;
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (find){
                    intent = new Intent(muscle_groups.this, findSession.class);
                }else{
                    intent = new Intent(muscle_groups.this, create_session.class);
                }
                intent.putExtra("mainUser",mainUser);
                intent.putExtra("users",users);
                intent.putExtra("muscle",muscle);
                intent.putExtra("date",date);
                intent.putExtra("time",time);
                intent.putExtra("desc",desc);
                intent.putExtra("location",location);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (find){
                    intent = new Intent(muscle_groups.this, findSession.class);
                }else{
                    intent = new Intent(muscle_groups.this, create_session.class);
                }                intent.putExtra("mainUser",mainUser);
                intent.putExtra("users",users);
                intent.putExtra("muscle",muscle);
                intent.putExtra("date",date);
                intent.putExtra("time",time);
                intent.putExtra("desc",desc);
                intent.putExtra("location",location);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });


    }
}