package com.claytantor.codepuzzles.model;

/**
 * Created by claytongraham on 6/17/15.
 */
public class WordCount {
    private int count;
    private String word;

    public WordCount(int count, String word) {
        this.count = count;
        this.word = word;
    }

    public WordCount(){}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void increment() {
        this.count+=1;
    };

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
