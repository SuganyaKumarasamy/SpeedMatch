package com.ksug.speedmatch.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ksug.speedmatch.R;
import com.ksug.speedmatch.constant.ApplicationConstant;
import com.ksug.speedmatch.entity.QuestionEntity;
import com.ksug.speedmatch.manager.AnalyticsManager;
import com.ksug.speedmatch.processor.QuestionGenerator;
import com.ksug.speedmatch.utility.PreferenceUtil;
import com.ksug.speedmatch.utility.TextUtil;
import com.ksug.speedmatch.view.CustomCardView;

import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends BaseActivity {

    @BindView(R.id.cta_restart)
    ImageView mRestart;

    @BindView(R.id.tv_counter)
    TextView mCounter;

    @BindView(R.id.tv_game_time)
    TextView mGameTime;

    @BindView(R.id.tv_score)
    TextView mScore;

    @BindView(R.id.tv_bonus_score)
    TextView mBonusScore;

    @BindView(R.id.question_container)
    View mQuestionContainer;

    @BindView(R.id.ccv_opt)
    CustomCardView customCardViewOption;


    @BindView(R.id.img_answer_notification)
    ImageView mAnswerNotification;

    @BindView(R.id.tv_bonus_view)
    TextView mBonusView;

    @BindView(R.id.cta_yes)
    Button mAnswerYes;

    @BindView(R.id.cta_no)
    Button mAnswerNo;

    private CountDownTimer countDownTimer;
    private long score;
    private int successiveCorrectAnswers;
    private int correctAnswers;
    private QuestionEntity questionEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
    @Override
    protected void onResume() {
        super.onResume();
        ButterKnife.bind(this);

        showGameStartTimerAndStartGame();
    }

    @SuppressLint("SetTextI18n")
    private void showGameStartTimerAndStartGame() {
        toggleQuestionView(true);
        mCounter.setText("3");
        mGameTime.setText(getString(R.string.formatted_time, "45"));
        mScore.setText(TextUtil.getFormattedScore(0));
        cancelTimer();
        final Handler handler = new Handler();
        final AtomicInteger atomicInteger = new AtomicInteger(ApplicationConstant.COUNT_DOWN_TIMER);
        final Runnable counter = new Runnable() {
            @Override
            public void run() {
                mCounter.setText(Integer.toString(atomicInteger.get()));
                if (atomicInteger.getAndDecrement() >= 1)
                    handler.postDelayed(this, ApplicationConstant.MILLI_SECOND);
                else {
                    setupQuestions();
                }
            }
        };
        handler.postDelayed(counter, ApplicationConstant.MILLI_SECOND);
    }

    private void setupQuestions() {
        toggleQuestionView(false);
        initGame();
        getNewQuestion();
    }

    private void initGame() {
        score = 0;
        successiveCorrectAnswers = 0;
        correctAnswers = 0;
        startGameTimer(ApplicationConstant.GAME_PLAY_TIME);
        questionEntity = new QuestionEntity();
        questionEntity.setColorTop(getResources().getDrawable(R.drawable.different_circle));
        questionEntity.setColorBottomLeft(getResources().getDrawable(R.drawable.normal_circle));
        questionEntity.setColorBottomRight(getResources().getDrawable(R.drawable.normal_circle));
    }

    private void toggleQuestionView(boolean isHidden) {
        if (isHidden) {
            mCounter.setVisibility(View.VISIBLE);
            mRestart.setVisibility(View.GONE);
            //mQuestionContainer.setVisibility(View.GONE);
            mAnswerNotification.setVisibility(View.INVISIBLE);
            mAnswerYes.setEnabled(false);
            mAnswerNo.setEnabled(false);
        } else {
            mCounter.setVisibility(View.GONE);
            mRestart.setVisibility(View.VISIBLE);
            //mquestioncontainer.setVisibility(View.VISIBLE);
            mAnswerNotification.setVisibility(View.INVISIBLE);
            mAnswerYes.setEnabled(true);
            mAnswerNo.setEnabled(true);
        }
    }

    private void getNewQuestion() {
        questionEntity = QuestionGenerator.getQuestion(GameActivity.this, questionEntity.getColorTop(), questionEntity.getColorBottomLeft(), questionEntity.getColorBottomRight());
        customCardViewOption.setCustomBackgroundColor(questionEntity.getColorTop(), questionEntity.getColorBottomLeft(), questionEntity.getColorBottomRight());
    }

    private void gameOver() {
        PreferenceUtil.setHighScore(this, score);
        PreferenceUtil.setMaximumCards(this, correctAnswers);
        AnalyticsManager.trackScore(mFirebaseAnalytics, score);
        AnalyticsManager.trackCards(mFirebaseAnalytics, correctAnswers);
        startMainActivity();
    }


    private void startGameTimer(long timerValue) {
        cancelTimer();
        countDownTimer = new CountDownTimer(timerValue, 1000) {
            public void onTick(long millisUntilFinished) {
                mGameTime.setText(getString(R.string.formatted_time, TextUtil.getFormattedTime(millisUntilFinished/1000)));
            }

            public void onFinish() {
                mGameTime.setText(getString(R.string.formatted_time, "00"));
                gameOver();
            }
        };
        countDownTimer.start();
    }

    private void cancelTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.cta_restart)
    public void restartGame(View view) {
        showGameStartTimerAndStartGame();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.cta_yes)
    public void optionYes(View view) {
        checkAnswer(true);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.cta_no)
    public void optionNo(View view) {
        checkAnswer(false);
    }

    private void checkAnswer(boolean isYesPressed) {
        if (questionEntity.isCorrect()) {
            if (isYesPressed) {
                toggleAnswerNotification(true);
            } else {
                toggleAnswerNotification(false);
            }
        } else {
            if (isYesPressed) {
                toggleAnswerNotification(false);
            } else {
                toggleAnswerNotification(true);
            }
        }
        getNewQuestion();
    }
    private void toggleAnswerNotification(boolean isCorrect) {
        mAnswerNotification.setVisibility(View.VISIBLE);
        if (isCorrect) {
            mAnswerNotification.setImageDrawable(getResources().getDrawable(R.drawable.ic_correct));
            successiveCorrectAnswers++;
            correctAnswers++;
            updateScore();
        } else {
            mAnswerNotification.setImageDrawable(getResources().getDrawable(R.drawable.ic_wrong));
            successiveCorrectAnswers = 0;
        }
        fadeOutAndHideView(mAnswerNotification);
    }

    private void updateScore() {
        score += TextUtil.getRandomInt(79, 99);
        if (successiveCorrectAnswers % ApplicationConstant.BONUS_SPLIT == 0) {
            int countSplit = successiveCorrectAnswers / ApplicationConstant.BONUS_SPLIT;
            long countSplitScore = countSplit * TextUtil.getRandomInt(501, 599);
            score += countSplitScore;
            animateBonusScore(countSplit, countSplitScore);
        }
        mScore.setText(TextUtil.getFormattedScore(score));
    }

    private void fadeOutAndHideView(final View imageView) {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(ApplicationConstant.FADE_OUT_DURATION);

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                imageView.setVisibility(View.INVISIBLE);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        imageView.startAnimation(fadeOut);
    }

    @SuppressLint("SetTextI18n")
    private void animateBonusScore(int countSplit, long countSplitScore) {
        mBonusScore.setVisibility(View.VISIBLE);
        mBonusView.setVisibility(View.VISIBLE);
        mBonusScore.setText("+" + countSplitScore);
        mBonusView.setText("+" + countSplit * ApplicationConstant.BONUS_SPLIT);
        fadeOutAndHideView(mBonusScore);
        fadeOutAndHideView(mBonusView);
    }

    private void startMainActivity() {
        Intent intentMain = new Intent(this, MainActivity.class);
        intentMain.putExtra(ApplicationConstant.EXTRA_GAME_SCORE,
                getString(R.string.game_over_message, score, correctAnswers));
        startActivity(intentMain);
        finish();
    }
}
