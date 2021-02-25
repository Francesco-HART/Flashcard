package com.jonathan.androidmusicflashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    public boolean isPlay = false;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        game = getIntent().getParcelableExtra("game");
        RadioGroup radioGroup = findViewById(R.id.radioGroup1);

        //get flashCards
        List<FlashCard> flashCards = game.getFlashCards();

        //get answers by question index
        List<String> answers = game.randomiseAnswers();

        //correct answer
        String correctAnswer = game.getCorrectAnswer();

        //Random answers
        final RadioButton[] generatedRadioButtons = new RadioButton[answers.size()];

        for (int i = 0; i < answers.size(); i++) {
            generatedRadioButtons[i] = new RadioButton(this);
            radioGroup.addView(generatedRadioButtons[i]);
            generatedRadioButtons[i].setText(answers.get(i));
        }

        Button confirmButton = findViewById(R.id.confirmButton);

        confirmButton.setOnClickListener(v -> {
            RadioButton checkedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());

            String userAnswer = checkedRadioButton.getText().toString();

            TextView resultTextView = findViewById(R.id.resultTextView);

            if (game.checkAnswer(userAnswer)) {
                resultTextView.setTextColor(Color.GREEN);
                resultTextView.setText("Correct answer");
            } else {
                resultTextView.setTextColor(Color.RED);
                resultTextView.setText("Wrong answer. The right answer was " + correctAnswer);
            }
            game.setQuestionIndex(game.increaseQuestionIndex());
        });


        Button playSong = findViewById(R.id.playAudioButton);

        playSong.setOnClickListener(v -> {
            MediaPlayer mediaPlayer = MediaPlayer.create(QuestionActivity.this, R.raw.music2);
            mediaPlayer.start();
        });


    }
}