package com.ksug.speedmatch.processor;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.ksug.speedmatch.constant.ApplicationConstant;
import com.ksug.speedmatch.entity.QuestionEntity;
import com.ksug.speedmatch.utility.TextUtil;

public class QuestionGenerator {

    private static Drawable tempColorTop = null;
    private static Drawable tempColorBottomLeft = null;
    private static Drawable tempColorBottomRight = null;
    private static String tempQuestionSet = null;

    public static QuestionEntity getQuestion(Context mContext, QuestionEntity previousQuestionEntity) {
        QuestionEntity questionEntity = new QuestionEntity();

        if (previousQuestionEntity != null) {
            questionEntity.setPreviousQuestionSet(previousQuestionEntity.getPresentQuestionSet());
            tempColorTop = previousQuestionEntity.getColorTop();
            tempColorBottomLeft = previousQuestionEntity.getColorBottomLeft();
            tempColorBottomRight = previousQuestionEntity.getColorBottomRight();
            tempQuestionSet = previousQuestionEntity.getPresentQuestionSet();
        }
        questionEntity = generateRandomQuestion(mContext, questionEntity);

        questionEntity.setColorTop(tempColorTop);
        questionEntity.setColorBottomLeft(tempColorBottomLeft);
        questionEntity.setColorBottomRight(tempColorBottomRight);

        if (questionEntity.getPreviousQuestionSet() != null && questionEntity.getPreviousQuestionSet().equals(tempQuestionSet)) {
            questionEntity.setCorrect(true);
        } else {
            questionEntity.setCorrect(false);
        }

        return questionEntity;
    }

    private static QuestionEntity generateRandomQuestion(Context mContext, QuestionEntity currentQuestionEntity) {
        int randomNum = TextUtil.getRandomInt(11, 99);
        if (randomNum % 3 == 0) {
            tempColorTop = mContext.getResources().getDrawable(ApplicationConstant.BG_SAME);
            tempColorBottomLeft = mContext.getResources().getDrawable(ApplicationConstant.BG_SAME);
            tempColorBottomRight = mContext.getResources().getDrawable(ApplicationConstant.BG_DIFFERENT);
            currentQuestionEntity.setPresentQuestionSet(ApplicationConstant.QUESTION_SET_A);

        } else if (randomNum % 3 == 1) {
            tempColorTop = mContext.getResources().getDrawable(ApplicationConstant.BG_SAME);
            tempColorBottomLeft = mContext.getResources().getDrawable(ApplicationConstant.BG_DIFFERENT);
            tempColorBottomRight = mContext.getResources().getDrawable(ApplicationConstant.BG_SAME);
            currentQuestionEntity.setPresentQuestionSet(ApplicationConstant.QUESTION_SET_B);

        } else {
            tempColorTop = mContext.getResources().getDrawable(ApplicationConstant.BG_DIFFERENT);
            tempColorBottomLeft = mContext.getResources().getDrawable(ApplicationConstant.BG_SAME);
            tempColorBottomRight = mContext.getResources().getDrawable(ApplicationConstant.BG_SAME);
            currentQuestionEntity.setPresentQuestionSet(ApplicationConstant.QUESTION_SET_C);

        }
        tempQuestionSet = currentQuestionEntity.getPresentQuestionSet();
        return currentQuestionEntity;
    }

}
