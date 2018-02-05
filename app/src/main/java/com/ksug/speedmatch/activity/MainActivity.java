package com.ksug.speedmatch.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ksug.speedmatch.R;
import com.ksug.speedmatch.constant.ApplicationConstant;
import com.ksug.speedmatch.utility.DialogUtil;
import com.ksug.speedmatch.utility.PreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_high_score)
    TextView mHighScore;

    @BindView(R.id.tv_max_cards)
    TextView mMaximumCards;

    @BindView(R.id.tv_last_game_text)
    TextView mLastGameText;

    @BindView(R.id.cta_start_game)
    Button mStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onResume() {
        super.onResume();
        ButterKnife.bind(this);
        setupView();
    }

    private void setupView() {
        mHighScore.setText(String.valueOf(PreferenceUtil.getHighScore(this)));
        mMaximumCards.setText(String.valueOf(PreferenceUtil.getMaximumCards(this)));

        if (getIntent() != null && getIntent().getExtras() != null) {
            String message= getIntent().getExtras()
                    .getString(ApplicationConstant.EXTRA_GAME_SCORE, null);
            if (message != null && message.length() > 0) {
                mLastGameText.setText(message);
                mLastGameText.setVisibility(View.VISIBLE);
                mStartGame.setText(getString(R.string.cta_restart_game));
            }
        } else {
            mLastGameText.setVisibility(View.INVISIBLE);
            mStartGame.setText(getString(R.string.cta_play_game));
        }
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.cta_info)
    public void showInfoDialog(View view) {
        DialogUtil.showAboutMessage(this, mFirebaseAnalytics);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.cta_start_game)
    public void startGame(View view) {
        startActivity(new Intent(this, GameActivity.class));
        finish();
    }
}
