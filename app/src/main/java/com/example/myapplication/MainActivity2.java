package com.example.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    Button button;
    Button button1;
    Button button2;
    TextView textView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        button = (Button) findViewById(R.id.easy_btn);
        button.setOnClickListener(v -> {
            button.setBackgroundColor(Color.BLUE);
            textView = (TextView)findViewById(R.id.textview);
            textView.setText("what is the product of ");
        });

        button1 = (Button) findViewById(R.id.medium_btn);
        button1.setOnClickListener(v -> {
            button1.setBackgroundColor(Color.BLUE);
            textView = (TextView)findViewById(R.id.textview);
            textView.setText("what is the product of ");
        });

        button2 = (Button) findViewById(R.id.hard_btn);
        button2.setOnClickListener(v -> {
            button2.setBackgroundColor(Color.BLUE);
            textView = (TextView)findViewById(R.id.textview);
            textView.setText("what is the product of ");
        });


    }
}