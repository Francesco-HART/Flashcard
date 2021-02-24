package com.jonathan.androidmusicflashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FlashCardProvider provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = new Intent(this, MainActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        provider = new FlashCardProvider();
        Game.Theme theme = Game.Theme.HipHop;
        ArrayList<FlashCard> flashCards = provider.loadFlashCards(this, theme);

        Button questions = findViewById(R.id.playButton);
        questions.setOnClickListener(v -> {
            openPopUp();

        });
    }

    public void openPopUp() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.show();
    }

    private void openPopUpSelectTheme() {

    }


}

