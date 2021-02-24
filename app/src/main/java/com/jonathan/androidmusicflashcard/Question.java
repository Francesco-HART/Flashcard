package com.jonathan.androidmusicflashcard;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Question implements Parcelable {

    private String songName;
    private List<String> answers;
    private String correctAnswer;

    public Question(String songName, List<String> answers, String correctAnswer) {
        this.songName = songName;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    protected Question(Parcel in) {
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
