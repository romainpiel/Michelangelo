package com.romainpiel.michelangelo.sample;

/**
 * Michelangelo
 * romainpiel
 * 23/01/2014
 */
public class Painting {

    private int drawableResId;
    private String title;

    public Painting(int drawableResId, String title) {
        this.drawableResId = drawableResId;
        this.title = title;
    }

    public int getDrawableResId() {
        return drawableResId;
    }

    public String getTitle() {
        return title;
    }
}
