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

    private boolean gameStatus = false;
    private String userAnswer="?";
    int attempt = 0;
    int qLength;
    boolean hints = true;
    int questions = 0;

    private TextView resultField, questionField, timerField;

    private GameLogic gameQuestions;

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

        int diff = getIntent().getIntExtra("Difficulty", 0);

        gameQuestions = new GameLogic(diff);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.keypad1:
                input("1");
                break;
            case R.id.keypad2:
                input("2");
                break;
            case R.id.keypad3:
                input("3");
                break;
            case R.id.keypad4:
                input("4");
                break;
            case R.id.keypad5:
                input("5");
                break;
            case R.id.keypad6:
                input("6");
                break;
            case R.id.keypad7:
                input("7");
                break;
            case R.id.keypad8:
                input("8");
                break;
            case R.id.keypad9:
                input("9");
                break;
            case R.id.keypad0:
                input("0");
                break;
            case R.id.keypadDEL:
                delete();
                break;
            case R.id.keypadMinus:
                input("-");
                break;
            case R.id.keypadHash:
                if(!gameStatus)startGame();
                else checkAnswer(userAnswer);
                System.out.println(userAnswer);

                break;
        }
    }

    public void startGame(){
        gameStatus = true;
            GetQuestion();
        timer();
    }

    public void GetQuestion(){

            String Q = gameQuestions.createQuestion();
            //System.out.println("Q: " + Q);
            questionField.setText(Q + userAnswer);
            qLength = Q.length();
            questions++;
            System.out.println("question number: "+ questions);

    }

    public void input( String key){
        if(gameStatus) {
            if(userAnswer.equals("?")){
                userAnswer = "";
                questionField.setText(questionField.getText().subSequence(0,questionField.length()-1));
            }
            questionField.append(key);
            userAnswer = userAnswer.concat(key);
        }
    }

    public void checkAnswer(String userAnswer){
        int answer = gameQuestions.getAnswer();
        try {
            int userInput = Integer.parseInt(userAnswer);
            if (userInput == answer) {
                resultField.setTextColor(getResources().getColor(R.color.correct_answer));
                resultField.setText("CORRECT");
            } else {
                if (hints && attempt < 3) {
                    resultField.setTextColor(getResources().getColor(R.color.default_colour));
                    if (userInput > answer) {
                        resultField.setText("LESS");
                        attempt++;
                    } else if (userInput < answer) {
                        resultField.setText("GREATER");
                        attempt++;
                    }
                } else {
                    resultField.setTextColor(getResources().getColor(R.color.incorrect_answer));
                    resultField.setText("WRONG");

                }
            }

        }catch (Exception e){
            resultField.setText("Input Answer!");
        }
    }

    public void delete(){
        if(gameStatus) {
            System.out.println(questionField.length());
            System.out.println(qLength);
            if (questionField.length() > qLength) {
                questionField.setText(questionField.getText().subSequence(0,questionField.length()-1));
                userAnswer = userAnswer.substring(0,userAnswer.length()-1);
            }
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


