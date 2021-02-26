package com.jonathan.androidmusicflashcard;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Collections;
import java.util.List;

public class FlashCardGame implements Parcelable {

    enum Theme {
        HipHop,
        Classic,
        Electro
    }

    private int questionsAmount = 5;
    private int questionIndex = 0;
    private Theme theme;
    private List<FlashCard> flashCards;
    private int score;

    public FlashCardGame(Theme theme, List<FlashCard> flashCards) {
        this.theme = theme;
        Collections.shuffle(flashCards);
        this.flashCards = flashCards;
        this.questionsAmount = flashCards.size();
    }

    protected FlashCardGame(Parcel in) {
        questionsAmount = in.readInt();
        flashCards = in.createTypedArrayList(FlashCard.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(questionsAmount);
        dest.writeTypedList(flashCards);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FlashCardGame> CREATOR = new Creator<FlashCardGame>() {
        @Override
        public FlashCardGame createFromParcel(Parcel in) {
            return new FlashCardGame(in);
        }

        @Override
        public FlashCardGame[] newArray(int size) {
            return new FlashCardGame[size];
        }
    };

    public Theme getTheme() {
        return this.theme;
    }

    public List<FlashCard> getFlashCards() {
        return flashCards;
    }

    public String getCorrectAnswer() {
        return this.flashCards.get(this.questionIndex).getCorrectAnswer();
    }

    public int getScore() {
        return this.score;
    }

    public int getQuestionsAmount() {
        return questionsAmount;
    }

    public int getCurrentIndexDisplay() {
        return questionIndex + 1;
    }

    public int getLastIndex() {
        return questionsAmount;
    }

    public float getScorePercentage() {
        return (float) score / questionsAmount * 100;
    }

    public int getSong(Context context) {
        return this.flashCards.get(this.questionIndex).getSongID(context);
    }

    public static String[] getThemesNames() {
        String[] themesList = new String[Theme.values().length];
        for (int i = 0; i < Theme.values().length; i++) {
            themesList[i] = Theme.values()[i].name();
        }

        return themesList;
    }

    public boolean isLastIndex() {
        if (questionIndex == questionsAmount - 1) {
            return true;
        } else {
            return false;
        }
    }

    public void addScore() {
        this.score = this.score + 1;
    }

    public int increaseQuestionIndex() {
        if (questionIndex < questionsAmount)
            this.questionIndex = questionIndex + 1;
        return this.questionIndex;
    }

    public boolean checkAnswer(String userAnswer) {
        return getCorrectAnswer().equals(userAnswer);
    }

    public List<String> randomizeAnswers() {
        List<String> randomizedAnswers = this.flashCards.get(this.questionIndex).getAnswers();
        Collections.shuffle(randomizedAnswers);
        return randomizedAnswers;
    }
}
