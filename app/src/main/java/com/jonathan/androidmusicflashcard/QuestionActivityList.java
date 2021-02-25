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

        loadFromJson();


        adapter = new QuestionAdapter(flashCardsList);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        Game.Theme theme = Game.Theme.HipHop;
//
//        flashCards = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++){
//            flashCards.add(new FlashCard("theme",,""));
//        }
//        adapter = new QuestionAdapter(games);
//
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
  }

    private void loadFromJson() {

        try {

            InputStream inputStream = getAssets().open("classic.json");

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