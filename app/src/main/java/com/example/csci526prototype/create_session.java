package com.example.csci526prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class create_session extends AppCompatActivity {
    private Button createSession, muscleButton, date, time, backButton;
    private ImageView arm, chest, leg, back;
    private TextInputEditText desc;
    private Spinner location;
    private EditText username, name;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private DBUserHandler userDB;
    private User mainUser;
    private ArrayList<UserModel> users;

    private int hour, minute, muscle;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_session);

        createSession = findViewById(R.id.create_session_btn);
        muscleButton = findViewById(R.id.button4);
        desc = findViewById(R.id.textInputEditDesc);
//        date = findViewById(R.id.textInputEditDate);
        date = findViewById(R.id.datePickerButton);
        name = findViewById(R.id.nameSession);
        username = findViewById(R.id.editTextTextPersonName);
        time = findViewById(R.id.timePickerButton);
        arm = findViewById(R.id.armImage);
        leg = findViewById(R.id.legImage);
        back = findViewById(R.id.backImage);
        chest = findViewById(R.id.chestImage);
        location = findViewById(R.id.locationSpinner);
        backButton = findViewById(R.id.backButton);
        date.setText(getTodaysDate());
        datePicker();
        popTimePicker();
        muscle = 0;

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(create_session.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Loaction));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        location.setAdapter(arrayAdapter);
        if(getIntent().getExtras() != null) {

            mainUser = (User) getIntent().getSerializableExtra("mainUser");
            users = (ArrayList<UserModel>) getIntent().getSerializableExtra("users");
            String tempDate = (String) getIntent().getSerializableExtra("date");
            String tempTime = (String) getIntent().getSerializableExtra("time");
            String tempName = (String) getIntent().getSerializableExtra("name");
            String tempDesc = (String) getIntent().getSerializableExtra("desc");
            Integer tempLocation = (Integer) getIntent().getSerializableExtra("location");
            Integer tempMuscle = (Integer) getIntent().getSerializableExtra("muscle");
            if (tempDate!= null){

                date.setText(tempDate);
            }
            if (tempTime!= null){
                time.setText(tempTime);
            }

            if (tempName!= null){
                name.setText(tempName);
            }
            if (tempDesc!= null){
                desc.setText(tempDesc);
            }
            if (tempLocation!= null){
                location.setSelection(tempLocation);
            }
            if (tempMuscle!= null){
                muscle = tempMuscle;
            }

        }
        userDB = new DBUserHandler(create_session.this);
        userDB.readUsers();
        username.setText(mainUser.getName());

        arm.setVisibility(View.INVISIBLE);
        leg.setVisibility(View.INVISIBLE);
        chest.setVisibility(View.INVISIBLE);
        back.setVisibility(View.INVISIBLE);
        switch (muscle){
            case 0:
                break;
            case 1:
                arm.setVisibility(View.VISIBLE);
                break;
            case 2:
                chest.setVisibility(View.VISIBLE);
                break;
            case 3:
                leg.setVisibility(View.VISIBLE);
                break;
            case 4:
                back.setVisibility(View.VISIBLE);
                break;
        }

        muscleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(create_session.this, muscle_groups.class);
                intent.putExtra("mainUser",mainUser);
                intent.putExtra("users",users);
                intent.putExtra("muscle",muscle);
                intent.putExtra("date",date.getText().toString());
                intent.putExtra("time",time.getText().toString());
                intent.putExtra("desc",desc.getText().toString());
                intent.putExtra("location",location.getSelectedItemPosition());
                intent.putExtra("name",name.getText().toString());
                startActivity(intent);
            }
        });

        createSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDB.addNewSession(mainUser.getId(),muscle,name.getText().toString(), desc.getText().toString(), date.getText().toString(), time.getText().toString(), location.getSelectedItem().toString());
                Intent intent = new Intent(create_session.this, HomeActivity.class);
                intent.putExtra("mainUser",mainUser);
                intent.putExtra("users",users);
                Toast.makeText(create_session.this, "Created Session", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(create_session.this, HomeActivity.class);
                intent.putExtra("mainUser",mainUser);
                intent.putExtra("users",users);
                startActivity(intent);
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker(v);
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimePicker(v);
            }
        });

    }


    private void datePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String selectedDate = makeDateString(day, month, year);
                date.setText(selectedDate);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.setTitle("Select Date");

    }

    private void popTimePicker(){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                time.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };
        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Select Time");
    }

    private String makeDateString(int day, int month, int year){
        String mon = "JAN";
        switch (month){
            case 1:
                mon = "JAN";
                break;
            case 2:
                mon = "FEB";
                break;
            case 3:
                mon = "MAR";
                break;
            case 4:
                mon = "APR";
                break;
            case 5:
                mon = "MAY";
                break;
            case 6:
                mon = "JUN";
                break;
            case 7:
                mon = "JUL";
                break;
            case 8:
                mon = "AUG";
                break;
            case 9:
                mon = "SEP";
                break;
            case 10:
                mon = "OCT";
                break;
            case 11:
                mon = "NOV";
                break;
            case 12:
                mon = "DEC";
                break;
        }


        return mon + " " + day + " " + year;
    }
    private String getTodaysDate(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    public void openDatePicker(View view){
        datePickerDialog.show();
    }
    public void openTimePicker(View view){
        timePickerDialog.show();
    }

}