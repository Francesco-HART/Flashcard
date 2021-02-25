package com.jonathan.androidmusicflashcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class QuestionActivityList extends AppCompatActivity {

    private QuestionAdapter adapter;
    ArrayList<FlashCard> flashCardsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);


        String[] tests = new String[0];
        try {
            tests = QuestionActivityList.this.getAssets().list("json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String test : tests)
        {
            Log.i("arrayTest", test);
            loadFromJson(test);
        }


        adapter = new QuestionAdapter(flashCardsList);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void loadFromJson(String filename) {

        try {

            InputStream inputStream = getAssets().open("json/"+filename);

            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String content = new String(buffer);
            JSONArray array = new JSONArray(content);

            for (int i = 0; i < array.length(); i++) {
                List<String> answers = new ArrayList<>();
                JSONObject obj = (JSONObject) array.get(i);
                JSONArray jsonArray = obj.getJSONArray("answers");

                for (int j = 0; j < jsonArray.length(); j++) {

                    answers.add((String) jsonArray.get(j));
                }

                List<String> answersCopy = new ArrayList<>(answers);
                FlashCard flashCard = new FlashCard(obj.getString("songName"), answersCopy, (obj.getString("correctAnswer")));
                Log.i("QuestionActivity", "loadFlashCards: " + flashCard);
                flashCardsList.add(flashCard);
            }

        } catch (IOException | JSONException e) {
            Log.e("QuestionActivity", "loadFlashCards: " + flashCardsList);

        }
    }
}
