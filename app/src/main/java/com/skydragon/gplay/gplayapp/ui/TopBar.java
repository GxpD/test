package com.skydragon.gplay.gplayapp.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.skydragon.gplay.gplayapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TopBar extends RelativeLayout implements View.OnClickListener {


	@Bind(R.id.iv_top_bar_left)
	ImageView mLeftIv;

	@Bind(R.id.tv_top_bar_title)
	TextView mTitleTv;

	@Bind(R.id.iv_top_bar_right)
	Button mRightIv;

	private Context mContext;
	private int leftSrc;
	private String centerText;
	private boolean isShowLeft;
	private boolean isShowRight;

	private float centerTextSize;
	private int centerTextColor;

	private View mView;

	private OnTopBarClickListener listener;


	public TopBar(Context context) {
		this(context, null);
	}

	public TopBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TopBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		this.mContext = context;

		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
		leftSrc = ta.getResourceId(R.styleable.TopBar_leftSrc, R.mipmap.ic_launcher);
		centerText = ta.getString(R.styleable.TopBar_centerText);
		isShowLeft = ta.getBoolean(R.styleable.TopBar_isShowLeft, false);
		isShowRight = ta.getBoolean(R.styleable.TopBar_isShowRight, false);
//		centerTextSize = ta.getDimension(R.styleable.TopBar_centerTextSize, 0);
		centerTextColor = ta.getColor(R.styleable.TopBar_centerTextColor, getResources().getColor(android.R.color.white));

		ta.recycle();

		initContentView();
		initView();
		initListener();
	}

	private void initContentView() {
		mView = View.inflate(mContext, R.layout.game_top_bar, this);
		ButterKnife.bind(this);
	}

	private void initView() {

//		mTitleTv.setTextSize(centerTextSize);
		mTitleTv.setTextColor(centerTextColor);
		mTitleTv.setText(centerText);

		if (isShowLeft) {
			mLeftIv.setVisibility(View.VISIBLE);
			mLeftIv.setImageResource(leftSrc);
		} else {
			mLeftIv.setVisibility(View.INVISIBLE);
		}

		if (isShowRight) {
			mRightIv.setVisibility(View.VISIBLE);
//			mRightIv.setImageResource(rightSrc);
            mRightIv.setText(R.string.TopBar_Right);
		} else {
			mRightIv.setVisibility(View.INVISIBLE);
		}
	}

	private void initListener() {
		mLeftIv.setOnClickListener(this);
		mRightIv.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_top_bar_left:
			if (listener != null) {
				listener.onTopBarLeftClick(v);
			}
			break;

		case R.id.iv_top_bar_right:
			if (listener != null) {
				listener.onTopBarRightClick(v);
			}
			break;
		}
	}

	public void setOnTopBarClickListener(OnTopBarClickListener listener) {
		this.listener = listener;
	}

	public interface OnTopBarClickListener {
		void onTopBarRightClick(View v);
		void onTopBarLeftClick(View v);

	}
}
