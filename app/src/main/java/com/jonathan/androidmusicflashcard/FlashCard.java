package com.jonathan.androidmusicflashcard;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FlashCard implements Parcelable {

    private String fileName;
    private List<String> answers;
    private String correctAnswer;
    private FlashCardGame.Theme theme;

    public FlashCard(String fileName, List<String> answers, String correctAnswer, FlashCardGame.Theme theme) {
        this.fileName = fileName;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.theme = theme;
    }

    public FlashCardGame.Theme getTheme() {
        return theme;
    }

    protected FlashCard(Parcel in) {
        fileName = in.readString();
        answers = in.createStringArrayList();
        correctAnswer = in.readString();
        theme = FlashCardGame.Theme.values()[in.readInt()];
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fileName);
        dest.writeStringList(answers);
        dest.writeString(correctAnswer);
        dest.writeInt(theme.ordinal());
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

    public String getFileName() {
        return fileName;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public int getSongID(Context context)
    {
        return context.getResources().getIdentifier("raw/" + this.fileName, null, context.getPackageName());
    }

    @Override
    public String toString() {
        return "Question{" +
                "song=" + fileName +
                ", answers=" + answers +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}
