package com.jonathan.androidmusicflashcard;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class FlashCard implements Parcelable {

    private String songName;
    private List<String> answers;
    private String correctAnswer;

    public FlashCard(String songName, List<String> answers, String correctAnswer) {
        this.songName = songName;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    protected FlashCard(Parcel in) {
        songName = in.readString();
        answers = in.createStringArrayList();
        correctAnswer = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(songName);
        dest.writeStringList(answers);
        dest.writeString(correctAnswer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FlashCard> CREATOR = new Creator<FlashCard>() {
        @Override
        public FlashCard createFromParcel(Parcel in) {
            return new FlashCard(in);
        }

        @Override
        public FlashCard[] newArray(int size) {
            return new FlashCard[size];
        }
    };

    public String getSongName() {
        return songName;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> randomAnswers() {
        return new ArrayList<>();

    }

    @Override
    public String toString() {
        return "Question{" +
                "song=" + songName +
                ", answers=" + answers +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}
