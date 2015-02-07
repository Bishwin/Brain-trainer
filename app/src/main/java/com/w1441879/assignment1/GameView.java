package com.w1441879.assignment1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class GameView extends Activity {
    private static final String TAG = "BrainTrainer" ;

    public static final String KEY_DIFFICULTY =
            "com.w1441879.assignment1.difficulty";

    public static final int DIFFICULTY_NOVICE = 0;
    public static final int DIFFICULTY_EASY = 1;
    public static final int DIFFICULTY_MEDIUM = 2;
    public static final int DIFFICULTY_GURU = 3;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        int diff = getIntent().getIntExtra(KEY_DIFFICULTY, DIFFICULTY_EASY);




    }


}
