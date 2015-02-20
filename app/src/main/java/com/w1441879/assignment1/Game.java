package com.w1441879.assignment1;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Game extends Activity implements OnClickListener{
    private static final String TAG = "BrainTrainer" ;

    public static final String KEY_DIFFICULTY =
            "com.w1441879.assignment1.difficulty";

    public static final int DIFFICULTY_NOVICE = 0;
    public static final int DIFFICULTY_EASY = 1;
    public static final int DIFFICULTY_MEDIUM = 2;
    public static final int DIFFICULTY_GURU = 3;

    private TextView resultField, questionField, timerField;
    private View btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDEL, btnMinus, btnHash;

    private GameLogic gameQuestions;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameview);
        Log.d(TAG, "onCreate");

        resultField=(TextView)findViewById(R.id.resultField);
        questionField=(TextView)findViewById(R.id.questionField);
        timerField=(TextView)findViewById(R.id.timerField);
        //Buttons
        btn1=findViewById(R.id.keypad1);
        btn2=findViewById(R.id.keypad2);
        btn3=findViewById(R.id.keypad3);
        btn4=findViewById(R.id.keypad4);
        btn5=findViewById(R.id.keypad5);
        btn6=findViewById(R.id.keypad6);
        btn7=findViewById(R.id.keypad7);
        btn8=findViewById(R.id.keypad8);
        btn9=findViewById(R.id.keypad9);
        btn0=findViewById(R.id.keypad0);
        btnDEL=findViewById(R.id.keypadDEL);
        btnMinus=findViewById(R.id.keypadMinus);
        btnHash=findViewById(R.id.keypadHash);

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

        int diff = getIntent().getIntExtra("Difficulty", 0);

        gameQuestions = new GameLogic(diff);
    }

    public void onClick(View v){
        switch(v.getId()){

        }
    }

    public void timer(){

        final TextView mTextField = (TextView) this.findViewById(R.id.timerField);

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTextField.setText("Time remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mTextField.setText("done!");
            }
        }.start();
    }
}


