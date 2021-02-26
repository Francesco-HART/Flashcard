package com.jonathan.androidmusicflashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView nameTextView = findViewById(R.id.nameTextView);
        nameTextView.setText("App Music");

        TextView emailTextView = findViewById(R.id.byTextView);
        emailTextView.setText("This app was created by Francesco, Guillaume et Jonathan even if Francesco is a noob");

        TextView versionTextView = findViewById(R.id.versionTextView);
        versionTextView.setText("v. " + String.valueOf(BuildConfig.VERSION_CODE));
    }
}