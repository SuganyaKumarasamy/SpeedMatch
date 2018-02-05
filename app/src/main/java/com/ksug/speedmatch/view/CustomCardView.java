package com.ksug.speedmatch.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ksug.speedmatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomCardView extends LinearLayout {

    @BindView(R.id.top_image)
    ImageView mTop;

    @BindView(R.id.bottom_left_image)
    ImageView mBottomLeft;

    @BindView(R.id.bottom_right_image)
    ImageView mBottomRight;

    public CustomCardView(Context context) {
        this(context, null);
    }

    public CustomCardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomCardView, 0, 0);
        Drawable top = a.getDrawable(R.styleable.CustomCardView_top);
        Drawable bottomLeft = a.getDrawable(R.styleable.CustomCardView_bottomLeft);
        Drawable bottomRight = a.getDrawable(R.styleable.CustomCardView_bottomRight);
        a.recycle();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.view_custom_card, this, true);
        }

        setCustomBackgroundColor(top, bottomLeft, bottomRight);
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        ButterKnife.bind(this, child.getRootView());
    }

    public void setCustomBackgroundColor(Drawable color, Drawable color1, Drawable color2) {
        mTop.setBackground(color);
        mBottomLeft.setBackground(color1);
        mBottomRight.setBackground(color2);
    }
}