package com.example.csci526prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class forget_pass extends AppCompatActivity {
    private EditText email;
    private Button forgot, cancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        cancel = findViewById(R.id.passCancel);
        forgot = findViewById(R.id.passreset);
        email = findViewById(R.id.westernEmail);

        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(forget_pass.this, MainActivity.class);
                startActivity(intent);
            }
        });

        forgot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(email.getText().toString().isEmpty()){
                    Toast.makeText(forget_pass.this, "Enter a Email", Toast.LENGTH_SHORT).show();

                }
                Toast.makeText(forget_pass.this, "Reset Email Sent", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(forget_pass.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }
}