package com.jonathan.androidmusicflashcard;

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
    private List<FlashCard> questions;


    public Game(Theme theme) {
        this.theme = theme;
    }

    public Theme getTheme() {
        return theme;
    }

    public List<FlashCard> getQuestions() {
        return questions;
    }

    public List<FlashCard> fetchGameQuestions() {
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

       /* try {

            JSONParser jsonParser = new JSONParser();

            JSONArray a = (JSONArray) jsonParser.parse(new FileReader("../../res.json." + jsonString + ""));


            JSONArray obj = new JSONArray(jsonString);

            for (int i = 0; i < obj.length(); i++) {
                Object o = obj.get(i);
                Log.i("Game", o.toString());

            }

        } catch (JSONException | FileNotFoundException e) {
            Log.e("Game", "Error on read json", e);
        }*/

        return new ArrayList<>();
    }

}
