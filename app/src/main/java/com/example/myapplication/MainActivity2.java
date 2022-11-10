/***so after the first answer, it tells you that you're wrong all the time
 * Not really sure how to fix that. Maybe the product isn't updating?
 * Maybe it's using an old version of the password? It's also worth mentioning
 * that I only tested it on level 1; I have to deal with the decimals in level 3
 * and I'm not sure how that's going to work out
 */

package com.example.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    Button button;
    Button button1;
    Button button2;
    TextView textView;

    boolean isButtonOn = false;
    boolean isButton1On = false;
    boolean isButton2On = false;

    Double product;
    int game;
    int level;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        EditText password = findViewById(R.id.password);
        Button validate = findViewById(R.id.validate);

        button = (Button) findViewById(R.id.easy_btn);
        button.setOnClickListener(v -> {
            if (isButton1On || isButton2On) { //if any other buttons are selected, turn them off
                turnOffButton(button1);
                isButton1On = false;
                turnOffButton(button2);
                isButton2On = false;
            }
            isButtonOn = !isButtonOn; //toggle whether button is selected
            if (isButtonOn) { //if the button is selected, change background and start game
                button.setBackgroundColor(Color.BLUE);
                textView = (TextView)findViewById(R.id.textview);
                //level = 0;
                product = startGame(level);
            } else {
                turnOffButton(button);
            }
            //debugging messages
            Log.d("DEBUG", "Button 0 state: " + isButtonOn);
            Log.d("DEBUG", "Button 1 state: " + isButton1On);
            Log.d("DEBUG", "Button 2 state: " + isButton2On);
        });

        button1 = (Button) findViewById(R.id.medium_btn);
        button1.setOnClickListener(v -> {
            if (isButtonOn || isButton2On) { //if any other buttons are selected, turn them off
                turnOffButton(button);
                isButtonOn = false;
                turnOffButton(button2);
                isButton2On = false;
            }
            isButton1On = !isButton1On; //toggle whether button is selected
            if (isButton1On) { //if the button is selected, change background and start game
                button1.setBackgroundColor(Color.BLUE);
                textView = (TextView)findViewById(R.id.textview);
                textView.setText("what is the product of ");
                level = 1;
                product = startGame(level);
            } else {
                turnOffButton(button1);
            }
            //debugging messages
            Log.d("DEBUG", "Button 0 state: " + isButtonOn);
            Log.d("DEBUG", "Button 1 state: " + isButton1On);
            Log.d("DEBUG", "Button 2 state: " + isButton2On);
        });

        button2 = (Button) findViewById(R.id.hard_btn);
        button2.setOnClickListener(v -> {
            if (isButtonOn || isButton1On) { //if any other buttons are selected, turn them off
                turnOffButton(button);
                isButtonOn = false;
                turnOffButton(button1);
                isButton1On = false;
            }
            isButton2On = !isButton2On; //toggle whether button is selected
            if (isButton2On) { //if the button is selected, change background and start game
                button2.setBackgroundColor(Color.BLUE);
                textView = (TextView)findViewById(R.id.textview);
                textView.setText("what is the product of ");
                level = 2;
                product = startGame(level);
            } else {
               turnOffButton(button2);
            }
            //debugging messages
            Log.d("DEBUG", "Button 0 state: " + isButtonOn);
            Log.d("DEBUG", "Button 1 state: " + isButton1On);
            Log.d("DEBUG", "Button 2 state: " + isButton2On);
        });

        validate.setOnClickListener(v -> { //listening for the validate button to check answer
            String userAnswer = password.getText().toString();
            Log.d("DEBUG", "Level: " + String.valueOf(level));
            game++;
            Log.d("DEBUG", "Game: " + game);
            if (Integer.parseInt(userAnswer) == product) {
                //NEED score plus one
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
                password.setText("");
                if (game < 10) {
                    startGame(level);
                } else restart();
            } else {
                Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
                password.setText("");
                if (game < 10) {
                    startGame(level);
                } else restart();
            }
        });
    }

    //used when button is turned off !!Update to reset problems and score when a new level is selected!!
    public void turnOffButton(Button button) {
        button.setBackgroundColor((Color.TRANSPARENT));
        textView.setText("");
        //reset score keeper
    }

    public double startGame(int level) {
        Random rand = new Random();
        String equationString;
        double product = 0; //if for some reason the level is not 0, 1, or 2, the product will return as 0
        if (level == 0) {
            for (int i = 0; i < 10; i++) { //generate 10 equations
                //generates two integers between 0 and 12
                int term1 = rand.nextInt(13);
                int term2 = rand.nextInt(13);
                product = (term1*term2); //should convert to double implicitly

                //creates equation message
                equationString = "What is " + term1 + " X " + term2 + "?";
                textView.setText(equationString);
            }
        } else if (level == 1) {
            for (int i = 0; i < 10; i++) { //generate 10 equations
                //generates two integers between 0 and 99
                int term1 = rand.nextInt(100);
                int term2 = rand.nextInt(100);
                product = term1 * term2; //should convert to double implicitly

                //creates equation message
                equationString = "What is " + term1 + " X " + term2 + "?";
                textView.setText(equationString);
            }
        } else if (level == 2) {
            for (int i = 0; i < 10; i++) { //generate 10 equations
                //generates two decimals between 0.000 and 999.999
                double term1 = (rand.nextInt(1000000)) / 1000; //should convert to double implicitly
                double term2 = (rand.nextInt(1000000)) / 1000; //should convert to double implicitly
                product = term1 * term2; //should convert to double implicitly

                //creates equation message
                equationString = "What is " + term1 + " X " + term2 + "?";
                textView.setText(equationString);
            }
        }
        Log.d("DEBUG", "Product: " + String.valueOf(product));
        return product;
    }

    public void restart() { //what happens when the user gets to 10 games and starts over
        isButtonOn = false;
        isButton1On = false;
        isButton2On = false;
        turnOffButton(button);
        turnOffButton(button1);
        turnOffButton(button2);
        textView.setText("");
        level = 0;
        game = 0;
        //reset score counter
        //whatever else should reset when the game resets
        //run onCreate method??
    }

}