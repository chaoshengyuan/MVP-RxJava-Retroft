package com.wenbing.mvpdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.wenbing.mvpdemo.R;

/**
 * @author: wenbing
 * @date: 2020/3/16 16:28
 */
public class InputView extends FrameLayout implements View.OnFocusChangeListener, TextWatcher, View.OnClickListener {
    private EditText mEditText;
    private View mButtomLine;

    private ImageView mLeftIcon;
    private ImageView mDelIcon;
    private ImageView mEyeIcon;

    private int mViewHeightFocus;
    protected int mViewColorFocus;
    private int mViewHeightNormal;
    protected int mViewColorNormal;


    public InputView(Context context) {
        this(context, null);
    }

    public InputView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(attrs);
    }

    private void initViews(AttributeSet attrs) {

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.InputView);
        Drawable leftDrawable = ta.getDrawable(R.styleable.InputView_leftIcon);
        Drawable delDrawable = ta.getDrawable(R.styleable.InputView_delIcon);
        Drawable eyeDrawable = ta.getDrawable(R.styleable.InputView_eyeIcon);
        String hint = ta.getString(R.styleable.InputView_hint);
        boolean isPassword = ta.getBoolean(R.styleable.InputView_isPassword,false);
        ta.recycle();
        //左边的图标
        mLeftIcon = new ImageView(getContext());
        int icIconSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getContext().getResources().getDisplayMetrics());
        int icIconMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getContext().getResources().getDisplayMetrics());
        LayoutParams ivIconLeftParams = new LayoutParams(icIconSize, icIconSize);
//        ivIconLeftParams.leftMargin = icIconMargin;
        ivIconLeftParams.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
        mLeftIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        if(leftDrawable!=null){
            mLeftIcon.setImageDrawable(leftDrawable);
        }
        addView(mLeftIcon, ivIconLeftParams);

        //删除图标
        mDelIcon = new ImageView(getContext());
        LayoutParams ivIconRightParams1 = new LayoutParams(icIconSize, icIconSize);
        ivIconRightParams1.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
        mDelIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        if(delDrawable!=null){
            mDelIcon.setImageDrawable(delDrawable);
        }
        addView(mDelIcon, ivIconRightParams1);

        //眼睛图标
        mEyeIcon = new ImageView(getContext());
        LayoutParams ivIconRightParams2 = new LayoutParams(icIconSize, icIconSize);
        ivIconRightParams2.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
        ivIconRightParams2.rightMargin = icIconSize + icIconMargin;
        mEyeIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        if(eyeDrawable!=null){
            mEyeIcon.setImageDrawable(eyeDrawable);
        }
        addView(mEyeIcon, ivIconRightParams2);

        mDelIcon.setVisibility(GONE);
        mEyeIcon.setVisibility(GONE);
        mDelIcon.setOnClickListener(this);
        mEyeIcon.setOnClickListener(this);

        mEditText = new EditText(getContext());
        LayoutParams eParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        int etMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getContext().getResources().getDisplayMetrics());
        eParams.leftMargin = icIconSize  + icIconMargin + etMargin;
        eParams.rightMargin = icIconSize  + icIconSize  + icIconMargin + etMargin;
        eParams.topMargin = etMargin;
        mEditText.setLayoutParams(eParams);
        mEditText.setBackgroundColor(Color.TRANSPARENT);
        mEditText.setTextColor(ContextCompat.getColor(getContext(), R.color.text_color));
        mEditText.setHintTextColor(ContextCompat.getColor(getContext(), R.color.text_grey_color));
        mEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX,getContext().getResources().getDimension(R.dimen.common_size));
        mEditText.setSingleLine();
        if(isPassword){
            mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }

        if(!TextUtils.isEmpty(hint)){
            mEditText.setHint(hint);
        }
        mEditText.addTextChangedListener(this);
        mEditText.setOnFocusChangeListener(this);
        addView(mEditText);

        mViewColorNormal = ContextCompat.getColor(getContext(), R.color.line_bg);
        mViewHeightNormal = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getContext().getResources().getDisplayMetrics());
        mViewColorFocus = ContextCompat.getColor(getContext(), R.color.blue);
        mViewHeightFocus = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getContext().getResources().getDisplayMetrics());

        mButtomLine = new View(getContext());
        LayoutParams vParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mViewHeightNormal);
        vParams.gravity = Gravity.BOTTOM;
        mButtomLine.setLayoutParams(vParams);
        mButtomLine.setBackgroundColor(mViewColorNormal);

        addView(mButtomLine);
    }


    public Editable getText(){
        return mEditText.getText();
    }

    public void setText(String text){
        mEditText.setText(text);
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
    private boolean isEmpty = true;

    private boolean isHidePwdMode = true;
    @Override
    public void afterTextChanged(Editable s) {
        isEmpty = s.toString().length() == 0;
        if (isEmpty) {
            mDelIcon.setVisibility(GONE);
            mEyeIcon.setVisibility(GONE);
        } else {
            mDelIcon.setVisibility(VISIBLE);
            mEyeIcon.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        mDelIcon.setVisibility(GONE);
        mEyeIcon.setVisibility(GONE);
        int color = mViewColorNormal;
        int height = mViewHeightNormal;
//        改变底部的线
        if(hasFocus){
            if (!isEmpty) {
                mDelIcon.setVisibility(VISIBLE);
                mEyeIcon.setVisibility(VISIBLE);
            }
            color = mViewColorFocus;
            height = mViewHeightFocus;
        }
        mButtomLine.setBackgroundColor(color);
        mButtomLine.getLayoutParams().height = height;
        mButtomLine.requestLayout();
        //改变左边颜色的图片
        mLeftIcon.setColorFilter(color);
    }

    @Override
    public void onClick(View v) {
        if (v == mDelIcon) {
            mEditText.setText("");
        } else if (v == mEyeIcon) {
            if (isHidePwdMode) {
                //隐藏密码
                isHidePwdMode = false;
                mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                mEyeIcon.setImageResource(R.drawable.ic_eye);
            } else {
                //显示密码
                isHidePwdMode = true;
                mEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                mEyeIcon.setImageResource(R.drawable.ic_eye_open);
            }
            mEditText.setSelection(mEditText.getText().toString().length());
        }
    }
}
