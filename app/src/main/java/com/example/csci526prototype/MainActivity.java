package com.example.csci526prototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private DatabaseHelper databaseHelper = new DatabaseHelper();
    private ActivityMainBinding binding;
    private EditText username, password;
    private Button login;
    private TextView forgotPass, createAccount;
//    private ArrayList<Sessions> sessions = new ArrayList<>();
//    private ArrayList<UserModel> users = new ArrayList<>();
    private DBUserHandler userDB;
    private User mainUser;
    private ArrayList<UserModel> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();


        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.button3);
        createAccount = findViewById(R.id.newuser);
        forgotPass = findViewById(R.id.forgetpass);

        userDB = new DBUserHandler(MainActivity.this);

        createAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, create_acct.class);
                startActivity(intent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();

                // validating if the text fields are empty or not.
                if (usernameText.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter a Username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(passwordText.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter a Password", Toast.LENGTH_SHORT).show();
                    return;

                }
                User tempUser = userDB.login(usernameText, passwordText);

                if (tempUser != null ){
                    mainUser = tempUser;
                    users = userDB.getUsers(tempUser.getName());
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//                    intent.putExtra("userDB", userDB);
//                    intent.putExtra("sessionsDB",sessionsDB);
//                    intent.putExtra("friendsDB",friendsDB);
//                    intent.putExtra("participantsDB",participantsDB);
                    intent.putExtra("mainUser",mainUser);
                    intent.putExtra("users",users);
                    startActivity(intent);
                    //TODO Login successfull
                }else{
                    Toast.makeText(MainActivity.this, "Wrong Username/Password", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, forget_pass.class);
                startActivity(intent);
            }
        });

//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.signin);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);


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

/*
        writeSessions();

    private void writeSessions(){
        String temp = databaseHelper.sessionsString(sessions);
        try {
            File file = new File(getFilesDir(), "Sessions.json");
            Writer output = null;
            System.out.println(file.getPath());

            output = new BufferedWriter(new FileWriter(file));
            output.write(temp);
            output.close();
            System.out.println("we did it?");

        } catch (FileNotFoundException e) {
            System.out.println("DIDNT FIND ITTTTTTTTTTTTTTTTTT");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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


    */
}