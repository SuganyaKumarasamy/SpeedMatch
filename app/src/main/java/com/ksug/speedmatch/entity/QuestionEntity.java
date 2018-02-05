package com.ksug.speedmatch.entity;


import android.graphics.drawable.Drawable;

public class QuestionEntity {

    private Drawable colorTopPrevious;
    private Drawable colorBottomLeftPrevious;
    private Drawable colorBottomRightPrevious;
    private Drawable colorTop;
    private Drawable colorBottomLeft;
    private Drawable colorBottomRight;
    private boolean isCorrect;

    public Drawable getColorTopPrevious() {
        return colorTopPrevious;
    }

    public void setColorTopPrevious(Drawable colorTopPrevious) {
        this.colorTopPrevious = colorTopPrevious;
    }

    public Drawable getColorBottomLeftPrevious() {
        return colorBottomLeftPrevious;
    }

    public void setColorBottomLeftPrevious(Drawable colorBottomLeftPrevious) {
        this.colorBottomLeftPrevious = colorBottomLeftPrevious;
    }

    public Drawable getColorBottomRightPrevious() {
        return colorBottomRightPrevious;
    }

    public void setColorBottomRightPrevious(Drawable colorBottomRightPrevious) {
        this.colorBottomRightPrevious = colorBottomRightPrevious;
    }
    public Drawable getColorTop() {
        return colorTop;
    }

    public void setColorTop(Drawable colorTop) {
        this.colorTop = colorTop;
    }

    public Drawable getColorBottomLeft() {
        return colorBottomLeft;
    }

    public void setColorBottomLeft(Drawable colorBottomLeft) {
        this.colorBottomLeft = colorBottomLeft;
    }

    public Drawable getColorBottomRight() {
        return colorBottomRight;
    }

    public void setColorBottomRight(Drawable colorBottomRight) {
        this.colorBottomRight = colorBottomRight;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    
}
