package com.example.braintrainner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    ArrayList<Integer>answer = new ArrayList<Integer>();
    int locationOfCorrectAnswers;
    TextView resultTextView;
    TextView scoreTextView;
    int score = 0;
    int numberOfQuestions = 0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timmerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;

    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        timmerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        resultTextView.setText("");
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timmerTextView.setText(String.valueOf(millisUntilFinished / 1000 ) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!!!!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }
    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswers).equals(view.getTag().toString())) {
            resultTextView.setText("correct");
            score++;
        }
        else {
            resultTextView.setText("wrong");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();

    }

    public void start(View view){

        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAganiButtton));
    }
    public void newQuestion (){
        Random rand = new Random();
        int a =rand.nextInt(21);
        int b = rand.nextInt(21);

        locationOfCorrectAnswers = rand.nextInt(4);

        answer.clear();

        sumTextView.setText(Integer.toString(a) + "+" +Integer.toString(b));
        for(int i=0; i<4; i++){
            if (i == locationOfCorrectAnswers) {
                answer.add(a+b);
            }
            else {
                int wrongAnswers = rand.nextInt(41);

                while (wrongAnswers == a+b) {
                    wrongAnswers = rand.nextInt(41);
                }
                answer.add(wrongAnswers);
            }
        }
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         sumTextView = findViewById(R.id.sumTextView);
         button0 = findViewById(R.id.button0);
         button1 = findViewById(R.id.button1);
         button2 = findViewById(R.id.button2);
         button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timmerTextView = findViewById(R.id.timmerTextView);
        playAgainButton = findViewById(R.id.playAganiButtton);
        gameLayout = findViewById(R.id.gameLayout);

        goButton = (Button)findViewById(R.id.goButton);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

        }

    }
