package com.jonathan.androidmusicflashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Button confirmButton = findViewById(R.id.confirmButton);

        confirmButton.setOnClickListener(v -> {
            RadioGroup radioGroup = findViewById(R.id.radioGroup1);
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