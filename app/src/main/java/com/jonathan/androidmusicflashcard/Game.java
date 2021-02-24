package com.jonathan.androidmusicflashcard;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {

    enum Theme {
        HipHop,
        Classic,
        ELECTRO
    }


    private final int numberQuestion = 10;
    private Theme theme;
    private List<Question> questions;


    public Game(Theme theme) {
        this.theme = theme;
    }

    public Theme getTheme() {
        return theme;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<Question> fetchGameQuestions() {
        JSONArray a = (JSONArray) parser.parse(new FileReader("c:\\exer4-courses.json"));
        String jsonString = "";

        switch (this.theme) {

            case HipHop:
                jsonString = "hip-hop";
                break;
            case Classic:
                jsonString = "classic";
                break;
            case ELECTRO:
                jsonString = "electro";
                break;

        }
        try {
            JSONArray obj = new JSONArray(jsonString);

            for (int i = 0; i < obj.length(); i++) {
                Object o = obj.get(i);
                Log.i("Game", o.toString());

            }

        } catch (JSONException e) {
            Log.e("Game", "Error on read json", e);
        }

        return new ArrayList<>();
    }

}
