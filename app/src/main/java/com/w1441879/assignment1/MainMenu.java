package com.w1441879.assignment1;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.MenuInflater;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

public class MainMenu extends Activity implements OnClickListener {

    View continueButton, newButton, aboutButton, exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getActionBar();

        /*try{
            actionBar.setTitle("hello");
        }
        catch(Exception e){
            System.out.println("whoops");
        }*/

        //Buttons
        continueButton=findViewById(R.id.continue_button);
        newButton = findViewById(R.id.new_game_button);
        aboutButton = findViewById(R.id.about_button);
        exitButton = findViewById(R.id.exit_button);

        //Listeners
        continueButton.setOnClickListener(this);
        newButton.setOnClickListener(this);
        aboutButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.continue_button:
                startGame(-1);
                break;
            case R.id.new_game_button:
                openNewGameDialog();
                break;
            case R.id.about_button:
                Intent i = new Intent(this, About.class);
                startActivity(i);
                break;
            case R.id.exit_button:
                finish();
                break;
        }
    }

    //User interface for difficulty
    private static final String TAG = "Brain Trainer";

    private void openNewGameDialog(){
        new AlertDialog.Builder(this)
                .setTitle(R.string.difficulty_label)
                .setItems(R.array.difficulty,
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialogInterface,
                                                int i){
                                startGame(i);
                            }
                        })
                .show();
    }

    private void startGame(int selectedDifficulty){
        Log.d(TAG, "clicked on " + selectedDifficulty);
        Intent gameIntent = new Intent(MainMenu.this, Game.class);
        gameIntent.putExtra("Difficulty", selectedDifficulty);
        startActivity(gameIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch(item.getItemId()){
            case R.id.settings:
                startActivity(new Intent(this, Settings.class));
                return true;
        }
        return false;
        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);*/

    }
}
