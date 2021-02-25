package com.jonathan.androidmusicflashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    public boolean isPlay = false;
    private Game game;
    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        game = getIntent().getParcelableExtra("game");

        nextQuestion(true);

    }

    private void nextQuestion(boolean playAgain)
    {
        TextView questionIndexTextView = findViewById(R.id.questionIndexTextView);
        questionIndexTextView.setText(game.getCurrentIndexDisplay() + "/" + game.getLastIndex());
        RadioGroup radioGroup = findViewById(R.id.radioGroup1);
        radioGroup.removeAllViews();

        //get flashCards
        //List<FlashCard> flashCards = game.getFlashCards();

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
            mediaPlayer.release();
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
            if (playAgain)
            {
                game.setQuestionIndex(game.increaseQuestionIndex());

                if (!game.isLastIndex())
                {
                    nextQuestion(true);
                }
                else
                {
                    nextQuestion(false);
                }
            }
            else
            {
                Button backToMenuButton = findViewById(R.id.backToMenuButton);
                backToMenuButton.setVisibility(View.VISIBLE);
                backToMenuButton.setOnClickListener(v1 -> {
                    Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
                    startActivity(intent);
                });
            }
        });


        Button playSong = findViewById(R.id.playAudioButton);

        playSong.setOnClickListener(v -> {
            mediaPlayer = MediaPlayer.create(QuestionActivity.this, game.getSong(QuestionActivity.this));
            mediaPlayer.start();
        });


    }
}