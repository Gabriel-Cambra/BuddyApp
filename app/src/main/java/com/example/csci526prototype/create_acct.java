package com.example.csci526prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class create_acct extends AppCompatActivity {
    private EditText createUsername, email, newPass, redoPass;
    private Button createAccount, cancel;
    private DBUserHandler userDB;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acct);
        createUsername = findViewById(R.id.createUsername);
        email = findViewById(R.id.WWUemail);
        newPass = findViewById(R.id.NewPass);
        redoPass = findViewById(R.id.redoPass);
        createAccount = findViewById(R.id.creatAcctBtn);
        cancel = findViewById(R.id.cancelButton);
        userDB = new DBUserHandler(create_acct.this);
        createAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String username = createUsername.getText().toString();
                String emailField = email.getText().toString();
                String password1 = newPass.getText().toString();
                String password2 = redoPass.getText().toString();
                if(username.isEmpty()){
                    Toast.makeText(create_acct.this, "Enter Username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (emailField.isEmpty()){
                    Toast.makeText(create_acct.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password1.isEmpty()){
                    Toast.makeText(create_acct.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password1.equals(password2)){
                    Toast.makeText(create_acct.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!userDB.checkUser(username)){
                    Toast.makeText(create_acct.this, "Username already in use", Toast.LENGTH_SHORT).show();
                    return;

                }


                userDB.addNewUser(username, "", emailField, password1);
                User user = userDB.login(username, password1);

//                userDB.addNewSession(user.getId(), 0, "Testing", "description is this", "JAN 02 2024", "13:42", "WWU");
                ArrayList<UserModel> users = userDB.getUsers(username);
                Intent intent = new Intent(create_acct.this, HomeActivity.class);

                intent.putExtra("mainUser",user);
                intent.putExtra("users",users);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(create_acct.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}