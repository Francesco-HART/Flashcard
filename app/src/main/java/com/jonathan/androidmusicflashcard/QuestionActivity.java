package com.jonathan.androidmusicflashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        RadioGroup radioGroup = findViewById(R.id.radioGroup1);

        List<String> answersList = new ArrayList<String>();
        answersList.add("Answer 1");
        answersList.add("Answer 2");
        answersList.add("Answer 3");

        Collections.shuffle(answersList);

        final RadioButton[] generatedRadioButtons = new RadioButton[answersList.size()];
        for(int i=0; i<answersList.size(); i++){
            generatedRadioButtons[i]  = new RadioButton(this);
            radioGroup.addView(generatedRadioButtons[i]);
            generatedRadioButtons[i].setText(answersList.get(i));
        }

        Button confirmButton = findViewById(R.id.confirmButton);

        confirmButton.setOnClickListener(v -> {
            RadioButton checkedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());

            String userAnswer = checkedRadioButton.getText().toString();
            String correctAnswer = "Answer 1";

            TextView resultTextView = findViewById(R.id.resultTextView);

            if (userAnswer.equals(correctAnswer))
            {
                resultTextView.setTextColor(Color.GREEN);
                resultTextView.setText("Correct answer");
            }
            else
            {
                resultTextView.setTextColor(Color.RED);
                resultTextView.setText("Wrong answer. The right answer was " + correctAnswer);
            }
        });
    }
}