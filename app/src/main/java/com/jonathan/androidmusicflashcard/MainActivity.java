package com.jonathan.androidmusicflashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FlashCardProvider provider;
    private FlashCardGame flashCardGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(v -> {
            provider = new FlashCardProvider();

            // The dialog checked item needs to be set to -1 to prevent the dialog from picking an item by default
            int checkedItem = -1;

            MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(MainActivity.this);
            dialog.setTitle("Choose your theme !");
            dialog.setSingleChoiceItems(FlashCardGame.getThemesNames(), checkedItem, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int dialogPickedItem) {
                    FlashCardGame.Theme theme = FlashCardGame.Theme.values()[dialogPickedItem];
                    ArrayList<FlashCard> flashCards = provider.loadFlashCards(MainActivity.this, theme);
                    flashCardGame = new FlashCardGame(theme, flashCards);

                    dialog.dismiss();

                    Intent questionActivityIntent = new Intent(MainActivity.this, QuestionActivity.class);
                    questionActivityIntent.putExtra("game", flashCardGame);
                    questionActivityIntent.putExtra("theme", theme.toString());
                    startActivity(questionActivityIntent);
                }
            });
            dialog.show();
        });

        Button aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(v -> {
            Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(aboutIntent);
        });

        Button questionsListButton = findViewById(R.id.questionsListButton);
        questionsListButton.setOnClickListener(v -> {
            Intent questionActivityListIntent = new Intent(MainActivity.this, QuestionActivityList.class);
            startActivity(questionActivityListIntent);
        });
    }
}

