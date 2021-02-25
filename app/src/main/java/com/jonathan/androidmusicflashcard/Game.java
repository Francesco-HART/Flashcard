package com.jonathan.androidmusicflashcard;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game implements Parcelable {

    enum Theme {
        HipHop,
        Classic,
        ELECTRO
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public void setQuestionIndex(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    private int numberQuestion = 3;
    private int questionIndex = 0;
    private Theme theme;
    private List<FlashCard> flashCards;


    public Game(Theme theme, List<FlashCard> flashCards) {
        this.theme = theme;

        //TODO : generate array flashCard by random
        Collections.shuffle(flashCards);
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
        return this.flashCards.get(this.questionIndex).getAnswers();
    }

    public String getCorrectAnswer() {
        return this.flashCards.get(this.questionIndex).getCorrectAnswer();
    }

    public boolean isLastIndex()
    {
        if (questionIndex == numberQuestion - 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getSong(Context context)
    {
        return this.flashCards.get(this.questionIndex).getSongID(context);
    }

    public int getCurrentIndex()
    {
        return questionIndex;
    }

    public int getCurrentIndexDisplay() { return questionIndex + 1; }

    public int getLastIndex() { return numberQuestion; }

    public int increaseQuestionIndex() {
        if (questionIndex < numberQuestion)
            this.questionIndex = questionIndex + 1;
        return this.questionIndex;
    }

    public boolean checkAnswer(String userAnswer) {
        return getCorrectAnswer().equals(userAnswer);
    }

    public List<String> randomiseAnswers() {
        List<String> array = this.flashCards.get(this.questionIndex).getAnswers();
        Collections.shuffle(array);
        return array;
    }
}
