package com.ksug.speedmatch.processor;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.ksug.speedmatch.constant.ApplicationConstant;
import com.ksug.speedmatch.entity.QuestionEntity;
import com.ksug.speedmatch.utility.TextUtil;

public class QuestionGenerator {

    public static QuestionEntity getQuestion(Context mContext, Drawable colorTopPrevious, Drawable colorBottomLeftPrevious, Drawable colorBottomRightPrevious) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setColorTopPrevious(colorTopPrevious);
        questionEntity.setColorBottomLeftPrevious(colorBottomLeftPrevious);
        questionEntity.setColorBottomRightPrevious(colorBottomRightPrevious);
        Drawable tempColorTop = colorTopPrevious;
        Drawable tempColorBottomLeft = colorBottomLeftPrevious;
        Drawable tempColorBottomRight = colorBottomRightPrevious;

        if (TextUtil.getRandomInt(11, 99) % 2 == 0) {
            int randomNum = TextUtil.getRandomInt(11, 99);
            if(randomNum % 3 == 0) {
                tempColorTop = mContext.getResources().getDrawable(ApplicationConstant.BG_SAME);
                tempColorBottomLeft = mContext.getResources().getDrawable(ApplicationConstant.BG_SAME);
                tempColorBottomRight = mContext.getResources().getDrawable(ApplicationConstant.BG_DIFFERENT);

            } else  if(randomNum % 3 == 1) {
                tempColorTop = mContext.getResources().getDrawable(ApplicationConstant.BG_SAME);
                tempColorBottomLeft = mContext.getResources().getDrawable(ApplicationConstant.BG_DIFFERENT);
                tempColorBottomRight = mContext.getResources().getDrawable(ApplicationConstant.BG_SAME);

            } else {
                tempColorTop = mContext.getResources().getDrawable(ApplicationConstant.BG_DIFFERENT);
                tempColorBottomLeft = mContext.getResources().getDrawable(ApplicationConstant.BG_SAME);
                tempColorBottomRight = mContext.getResources().getDrawable(ApplicationConstant.BG_SAME);

            }
        }
        questionEntity.setColorTop(tempColorTop);
        questionEntity.setColorBottomLeft(tempColorBottomLeft);
        questionEntity.setColorBottomRight(tempColorBottomRight);
        if(questionEntity.getColorTopPrevious() == tempColorTop && questionEntity.getColorBottomLeftPrevious() == tempColorBottomLeft && questionEntity.getColorBottomRightPrevious() == tempColorBottomRight) {
            questionEntity.setCorrect(true);
        } else {
            questionEntity.setCorrect(false);
        }
        return questionEntity;
    }

}
