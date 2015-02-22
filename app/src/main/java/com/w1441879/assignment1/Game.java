package com.w1441879.assignment1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Game extends Activity implements OnClickListener{
    private static final String TAG = "BrainTrainer" ;

    private int time;
    private boolean keypadEnabled = false;
    private String userAnswer;
    private int attempt = 0;
    private int qLength;
    private boolean hints;
    private int questions = 0;
    private boolean questionAnswered = false;
    private String Q;

    private TextView resultField, questionField, timerField;

    private GameLogic gameQuestions;
    private CountDownTimer timer;
    GameScore scoreboard;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameview);
        Log.d(TAG, "onCreate");

        resultField=(TextView)findViewById(R.id.resultField);
        questionField=(TextView)findViewById(R.id.questionField);
        timerField=(TextView)findViewById(R.id.timerField);
        //Buttons
        View btn1=findViewById(R.id.keypad1);
        View btn2=findViewById(R.id.keypad2);
        View btn3=findViewById(R.id.keypad3);
        View btn4=findViewById(R.id.keypad4);
        View btn5=findViewById(R.id.keypad5);
        View btn6=findViewById(R.id.keypad6);
        View btn7=findViewById(R.id.keypad7);
        View btn8=findViewById(R.id.keypad8);
        View btn9=findViewById(R.id.keypad9);
        View btn0=findViewById(R.id.keypad0);
        View btnDEL=findViewById(R.id.keypadDEL);
        View btnMinus=findViewById(R.id.keypadMinus);
        View btnHash=findViewById(R.id.keypadHash);
        //Listeners
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btnDEL.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnHash.setOnClickListener(this);

        SharedPreferences sharedprefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        hints = sharedprefs.getBoolean("hints",false);

        int diff = getIntent().getIntExtra("Difficulty", 0);
        gameQuestions = new GameLogic(diff);
        scoreboard = new GameScore();

        timer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerField.setText("Time remaining: " + millisUntilFinished / 1000);
                time++;
            }

            public void onFinish() {
                displayResult(false);
                startTurn();
            }
        };

    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.keypad1:
                userInput("1");
                break;
            case R.id.keypad2:
                userInput("2");
                break;
            case R.id.keypad3:
                userInput("3");
                break;
            case R.id.keypad4:
                userInput("4");
                break;
            case R.id.keypad5:
                userInput("5");
                break;
            case R.id.keypad6:
                userInput("6");
                break;
            case R.id.keypad7:
                userInput("7");
                break;
            case R.id.keypad8:
                userInput("8");
                break;
            case R.id.keypad9:
                userInput("9");
                break;
            case R.id.keypad0:
                userInput("0");
                break;
            case R.id.keypadDEL:
                delete();
                break;
            case R.id.keypadMinus:
                userInput("-");
                break;
            case R.id.keypadHash:
                if(!keypadEnabled)startTurn();
                else if(questionAnswered){
                    startTurn();
                }else checkAnswer(userAnswer);
                break;
        }
    }

    private void startTurn(){
        reset();
        if(questions < 10) {
            userAnswer = "?";
            questionAnswered = false;
            keypadEnabled = true;
            GetQuestion();
            timer.start();
        } else {
            questionField.setText("GAMEOVER!");
            keypadEnabled =false;
            resultField.setText("GAMESCORE = " + Integer.toString(scoreboard.getScore()));
        }
    }

    private void GetQuestion(){

            Q = gameQuestions.createQuestion();
            //System.out.println("Q: " + Q);
            questionField.setText(Q + userAnswer);
            qLength = Q.length();
            questions++;
            System.out.println("question number: "+ questions);

    }

    private void userInput(String key){
        if(keypadEnabled) {
            if(userAnswer.equals("?")){
                userAnswer = "";
                questionField.setText(questionField.getText().subSequence(0,questionField.length()-1));
            }
            questionField.append(key);
            userAnswer = userAnswer.concat(key);
        }
    }

    private void checkAnswer(String userAnswer){
        resultField.setTextColor(getResources().getColor(R.color.default_colour));
        int answer = gameQuestions.getAnswer();
        try {
            int userInput = Integer.parseInt(userAnswer);
            if (userInput == answer) {
                displayResult(true);
            } else {
                if (hints && attempt < 3) {
                    if (userInput > answer) {
                        resultField.setText("LESS");
                        attempt++;
                        clear();
                    } else if (userInput < answer) {
                        resultField.setText("GREATER");
                        attempt++;
                        clear();
                    }
                } else {
                    displayResult(false);
                }
            }
        }catch (Exception e){
            resultField.setText("Input Answer!");
        }
    }

    private void displayResult(boolean result){

        if (result){
        resultField.setTextColor(getResources().getColor(R.color.correct_answer));
        resultField.setText("CORRECT");
        scoreboard.setScore(time);
            time=0;
        } else{
            resultField.setTextColor(getResources().getColor(R.color.incorrect_answer));
            resultField.setText("WRONG");

        }
        timer.cancel();
        questionAnswered = true;
        keypadEnabled = false;
    }

    private void delete(){
        if(keypadEnabled) {
            if (questionField.length() > qLength) {
                questionField.setText(questionField.getText().subSequence(0,questionField.length()-1));
                userAnswer = userAnswer.substring(0,userAnswer.length()-1);
            }
        }
    }

    private void reset(){
        attempt =0;
        resultField.setText(" ");
        timerField.setText(" ");
    }

    private void clear(){
        questionField.setText(Q);
        userAnswer = "";
    }




    }


