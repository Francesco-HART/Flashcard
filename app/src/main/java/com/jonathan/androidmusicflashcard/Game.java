package com.jonathan.androidmusicflashcard;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Game implements Parcelable {

    enum Theme {
        HipHop,
        Classic,
        ELECTRO
    }

    public int getIsQuestion() {
        return isQuestion;
    }

    public void setIsQuestion(int isQuestion) {
        this.isQuestion = isQuestion;
    }

    private int numberQuestion = 2;
    private int isQuestion = 0;
    private Theme theme;
    private List<FlashCard> flashCards;


    public Game(Theme theme, List<FlashCard> flashCards) {
        this.theme = theme;

        //TODO : generate array flashCard by random
        this.flashCards = flashCards;
    }


    protected Game(Parcel in) {
        numberQuestion = in.readInt();
        flashCards = in.createTypedArrayList(FlashCard.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(numberQuestion);
        dest.writeTypedList(flashCards);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    public Theme getTheme() {
        return theme;
    }

    public List<FlashCard> getFlashCards() {
        return flashCards;
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

        return new ArrayList<>();
    }


    public List<String> getAnswers() {
        return this.flashCards.get(this.isQuestion).getAnswers();
    }

    public String getCorrectAnswer() {
        return this.flashCards.get(this.isQuestion).getCorrectAnswer();
    }


    public boolean checkAnswer(String userAnswer) {
        return getCorrectAnswer().equals(userAnswer);
    }

    public List<String> randomiseAnswers() {
        List<String> array = this.flashCards.get(this.isQuestion).getAnswers();
        Collections.shuffle(array);
        return array;
    }
}
