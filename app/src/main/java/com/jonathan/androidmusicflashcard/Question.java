package com.jonathan.androidmusicflashcard;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Question implements Parcelable {

    private int song;
    private List<String> answers;
    private String correctAnswer;

    public Question(int song, List<String> answers, String correctAnswer) {
        this.song = song;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    protected Question(Parcel in) {
        song = in.readInt();
        answers = in.createStringArrayList();
        correctAnswer = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(song);
        dest.writeStringList(answers);
        dest.writeString(correctAnswer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public int getSong() {
        return song;
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
                "song=" + song +
                ", answers=" + answers +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}
