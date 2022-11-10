package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.login);
        button.setOnClickListener(v -> navigateToSecondActivity());


    }

    void navigateToSecondActivity(){
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
        }
}