package com.example.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
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
    TextView score;

    boolean isButtonOn = false;
    boolean isButton1On = false;
    boolean isButton2On = false;

    Double product;
    int game;
    int level;
    int points;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        EditText password = findViewById(R.id.password);
        Button validate = findViewById(R.id.validate);
        score = findViewById(R.id.score);


        ///////////////////////////////////////////////////

        button = (Button) findViewById(R.id.easy_btn);
        button.setOnClickListener(v -> {

            turnOffButton(button1);
            isButton1On = false;
            turnOffButton(button2);
            isButton2On = false;
            button.setBackgroundColor(Color.BLUE);
            textView = (TextView)findViewById(R.id.textview);
            level = 0;
            product = startGame(level);
        });

        ///////////////////////////////////////////////////

        button1 = (Button) findViewById(R.id.medium_btn);
        button1.setOnClickListener(v -> {

            turnOffButton(button);
            isButtonOn = false;
            turnOffButton(button2);
            isButton2On = false;
            button1.setBackgroundColor(Color.BLUE);
            textView = (TextView)findViewById(R.id.textview);
            level = 1;
            product = startGame(level);
        });


        ///////////////////////////////////////////////////


        button2 = (Button) findViewById(R.id.hard_btn);
        button2.setOnClickListener(v -> {

            turnOffButton(button);
            isButtonOn = false;
            turnOffButton(button1);
            isButton1On = false;
            button2.setBackgroundColor(Color.BLUE);
            textView = (TextView)findViewById(R.id.textview);
            level = 2;
            product = startGame(level);

        });

        //////////////////////////////////////////////////////////////////


        validate.setOnClickListener(v -> {
            String userAnswer = password.getText().toString();
            double answer;
            game++;
            if(level == 2){
                String[] rat = userAnswer.split("/");
                answer = Double.parseDouble(rat[0]) / Double.parseDouble(rat[1]);
                if (answer == product) {
                    Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
                    points++;
                    score.setText(points + "/10");
                } else {
                    Toast.makeText(this, "Incorrect" , Toast.LENGTH_SHORT).show();
                    score.setText(points + "/10");
                }
            }
            else{
                if (Integer.parseInt(userAnswer) == product) {
                    Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
                    points++;
                    score.setText(points + "/10");
                } else {
                    Toast.makeText(this, "Incorrect, the right answer is " + product, Toast.LENGTH_SHORT).show();
                    score.setText(points + "/10");
                }
            }
            password.setText("");
            if (game < 10) {
                product = startGame(level);
            } else{ restart();}
        });
    }


    /////////////////////////////////////////////////////////////////

    public void turnOffButton(Button button) {
        button.setBackgroundColor((Color.TRANSPARENT));
    }

    /////////////////////////////////////////////////////////////

    public double startGame(int level) {
        Random rand = new Random();
        String equationString;
        double product = 0;
        if (level == 0) {
            for (int i = 0; i < 10; i++) {
                int term1 = rand.nextInt(13);
                int term2 = rand.nextInt(13);
                product = (term1*term2);
                equationString = "What is " + term1 + " X " + term2 + "?";
                textView.setText(equationString);
            }
        } else if (level == 1) {
            for (int i = 0; i < 10; i++) {
                int term1 = rand.nextInt(100) + 10;
                int term2 = rand.nextInt(100) + 10;
                product = term1 * term2;
                equationString = "What is " + term1 + " X " + term2 + "?";
                textView.setText(equationString);
            }
        } else {
            for (int i = 0; i < 10; i++) {
                double term1 = rand.nextInt(10) + 1;
                double term2 = rand.nextInt(10) + 1;
                double term3 = rand.nextInt(10) + 1;
                double term4 = rand.nextInt(10) + 1;
                product = (term1/term2) * (term3/term4);
                equationString = "What is " + (int)term1 + "/" + (int)term2 + " X " + (int)term3 + "/" + (int)term4 + "?";
                textView.setText(equationString);
            }
        }
        return product;
    }

    //////////////////////////////////////////////////////

    @SuppressLint("SetTextI18n")
    public void restart() {
        isButtonOn = false;
        isButton1On = false;
        isButton2On = false;
        turnOffButton(button);
        turnOffButton(button1);
        turnOffButton(button2);
        textView.setText("");
        level = 0;
        game = 0;
        points = 0;
        score.setText(" - /10");
    }

}
