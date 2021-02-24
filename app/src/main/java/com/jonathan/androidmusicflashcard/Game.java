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
    private List<Integer> questions;


    public Game(Theme theme) {
        this.theme = theme;
    }

    public Theme getTheme() {
        return theme;
    }

    public List<Integer> getQuestions() {
        return questions;
    }

    public List<Question> fetchGameQuestions() {
        return new ArrayList<>();
    }

}
