package com.jonathan.androidmusicflashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FlashCardProvider provider;
    private FlashCardProvider flashCard;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = new Intent(this, MainActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button questions = findViewById(R.id.playButton);
        questions.setOnClickListener(v -> {
            //openPopUp();
            //TODO : theme by popup select
            provider = new FlashCardProvider();
            //Game.Theme theme = Game.Theme.HipHop;
            //ArrayList<FlashCard> flashCards = provider.loadFlashCards(this, theme);
            //game = new Game(Game.getThemes2()[2], flashCards);
            //Intent intentQuestionActivity = new Intent(MainActivity.this, QuestionActivity.class);
            //intentQuestionActivity.putExtra("game", game);
            //startActivity(intentQuestionActivity);

            //val singleItems = arrayOf("Item 1", "Item 2", "Item 3")
            int checkedItem = -1;

            MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(MainActivity.this);
             dialog.setTitle("title");

             dialog.setSingleChoiceItems(Game.getThemes(), checkedItem, new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     Game.Theme theme = Game.getThemes2()[which];
                     ArrayList<FlashCard> flashCards = provider.loadFlashCards(MainActivity.this, theme);
                     game = new Game(theme, flashCards);
                     Log.i("MainActivity", "onClick: theme "+theme);
                     String themeString = theme.toString();

                     Intent intentQuestionActivity = new Intent(MainActivity.this, QuestionActivity.class);
                     intentQuestionActivity.putExtra("game", game);
                     intentQuestionActivity.putExtra("theme", themeString);
                     startActivity(intentQuestionActivity);
                 }
             });
             dialog.show();
        });
    }

    public void openPopUp() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.show();
    }

    private void openPopUpSelectTheme() {

    }


}

