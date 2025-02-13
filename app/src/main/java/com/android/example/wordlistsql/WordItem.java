package com.android.example.wordlistsql;

public class WordItem {
    private int mId;
    private String mWord;
    public WordItem(){}

    public String getWord() {
        return mWord;
    }

    public void setWord(String word) {
        mWord = word;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

}
