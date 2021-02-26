package com.jonathan.androidmusicflashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    public boolean isPlay = false;
    private Game game;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private boolean isSubmit = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        game = getIntent().getParcelableExtra("game");


        playStatus(true);

    }

    private void playStatus(boolean playAgain) {
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
            mediaPlayer = new MediaPlayer();
            RadioButton checkedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());

            if (radioGroup.getCheckedRadioButtonId() != -1) {
                String userAnswer = checkedRadioButton.getText().toString();

                TextView resultTextView = findViewById(R.id.resultTextView);


                if (isSubmit) {
                    if (game.getFlashCards().size() > 1) {
                        resultTextView.setVisibility(View.VISIBLE);
                        checkAnswer(resultTextView, userAnswer, correctAnswer);
                        confirmButton.setText("next question");
                        enabledRadioButtons(generatedRadioButtons, false);
                        this.isSubmit = false;
                    } else {
                        resultTextView.setVisibility(View.VISIBLE);
                        checkAnswer(resultTextView, userAnswer, correctAnswer);
                        confirmButton.setText("back");
                        this.isSubmit = false;
                    }
                } else {
                    if (game.getFlashCards().size() > 1) {
                        nextQuestion(playAgain);
                        radioGroup.clearCheck();
                        confirmButton.setText("confirm");
                        resultTextView.setVisibility(View.INVISIBLE);
                        enabledRadioButtons(generatedRadioButtons, true);
                        this.isSubmit = true;
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                QuestionActivity.this.finish();
                            }
                        });
                    }

                }

            } else {
                runToast();
            }


        });


        Button playSong = findViewById(R.id.playAudioButton);

        playSong.setOnClickListener(v -> {
            if(mediaPlayer != null && !mediaPlayer.isPlaying()){
                mediaPlayer = MediaPlayer.create(QuestionActivity.this, game.getSong(QuestionActivity.this));
                mediaPlayer.start();
            }
        });


    }

    private void nextQuestion(boolean playAgain) {

        if (playAgain) {
            game.setQuestionIndex(game.increaseQuestionIndex());

            if (!game.isLastIndex()) {
                playStatus(true);
            } else {
                playStatus(false);
            }
        } else {
            String themeString = getIntent().getStringExtra("theme");

            QuestionActivity.this.setContentView(R.layout.result_screen);

            TextView themeTextView = findViewById(R.id.themeTextView);
            TextView scoreTextView = findViewById(R.id.scoreTextView);
            TextView percentTextView = findViewById(R.id.percentTextView);

            themeTextView.setText(themeString);
            scoreTextView.setText(game.getScore() + " / " + game.getNumberQuestion());
            percentTextView.setText(Math.round(game.getScorePercentage()) + "% of your answers were correct");


            Button backToMenuButton = findViewById(R.id.backToMenuButton);
            backToMenuButton.setOnClickListener(v1 -> {
                Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
                startActivity(intent);
            });


        }
    }

    private void checkAnswer(TextView resultTextView, String userAnswer, String correctAnswer) {
        this.isSubmit = false;
        if (game.checkAnswer(userAnswer)) {
            resultTextView.setTextColor(Color.GREEN);
            resultTextView.setText("Correct answer");
            game.addScore();
        } else {
            resultTextView.setTextColor(Color.RED);
            resultTextView.setText("Wrong answer. The right answer was " + correctAnswer);
        }

    }

    private void enabledRadioButtons(RadioButton[] generatedRadioButtons, boolean isEnabled) {
        for (int i = 0; i < generatedRadioButtons.length; i++) {
            generatedRadioButtons[i].setEnabled(isEnabled);
        }
    }


    private void runToast() {
        Toast myToast = Toast.makeText(QuestionActivity.this, "Value cannot be empty", Toast.LENGTH_SHORT);
       /* LayoutInflater inflater = (LayoutInflater) QuestionActivity.this.getSystemService(QuestionActivity.this.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.your_custom_layout, null);
        toast.setView(view);*/
        myToast.show();
    }
}