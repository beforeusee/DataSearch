package com.example.a103.datasearch.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a103.datasearch.R;

/**
 * Created by zhengxiaohu on 2017/3/27.
 */

public class CustomTitleBar extends RelativeLayout {
    private Button titleBarLeftBtn;
    private Button titleBarRightBtn;
    private TextView titleBarTitle;
    public CustomTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_title_bar,this,true);
        titleBarLeftBtn= (Button) findViewById(R.id.title_bar_left);
        titleBarRightBtn= (Button) findViewById(R.id.title_bar_right);
        titleBarTitle= (TextView) findViewById(R.id.title_bar_title);

        TypedArray attributes=context.obtainStyledAttributes(attrs,R.styleable.CustomTitleBar);
        if (attributes!=null){
            //处理titlebar背景色
            int titleBarBackGround=attributes.getResourceId(R.styleable.
                    CustomTitleBar_title_background_color, Color.GREEN);
            setBackgroundResource(titleBarBackGround);

            //处理左边按钮
            //左边按钮是否可见
            boolean leftButtonVisible=attributes.getBoolean(R.styleable.
                    CustomTitleBar_left_button_visible,true);
            if (leftButtonVisible){
                titleBarLeftBtn.setVisibility(View.VISIBLE);
            }else{
                titleBarLeftBtn.setVisibility(View.INVISIBLE);
            }

            //左边按钮文字
            String leftButtonText=attributes.getString(R.styleable.CustomTitleBar_left_button_text);
            if (!TextUtils.isEmpty(leftButtonText)){
                titleBarLeftBtn.setText(leftButtonText);
                //设置左边按钮文字颜色
                int leftButtonTextColor=attributes.getColor(R.styleable.CustomTitleBar_left_button_text_color,Color.WHITE);
                titleBarLeftBtn.setTextColor(leftButtonTextColor);
            }else {
                //设置左边图片icon，二选一，要么是图片要么是文字
                int leftButtonDrawable=attributes.getResourceId(R.styleable.CustomTitleBar_left_button_drawable,R.drawable.arrow_left);
                if (leftButtonDrawable!=-1){
                    titleBarLeftBtn.setBackgroundResource(leftButtonDrawable);
                }
            }

            //处理标题
            //先获取标题是否要显示图片icon
            int titleTextDrawable=attributes.getResourceId(R.styleable.CustomTitleBar_title_text_drawable,-1);
            if (titleTextDrawable!=-1){
                titleBarTitle.setBackgroundResource(titleTextDrawable);
            }else {
                //如果不是图片标题，则获取文字标题
                String titleText=attributes.getString(R.styleable.CustomTitleBar_title_text);
                if (!TextUtils.isEmpty(titleText)){
                    titleBarTitle.setText(titleText);
                }

                //获取标题显示颜色
                int titleTextColor=attributes.getColor(R.styleable.CustomTitleBar_left_button_text_color,Color.WHITE);
                titleBarTitle.setTextColor(titleTextColor);
                //获取标题文字大小
                int titleTextSize=attributes.getDimensionPixelSize(R.styleable.CustomTitleBar_title_size,20);
                titleBarTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,titleTextSize);
            }

            //处理右边按钮
            //获取是否要显示右边按钮
            boolean rightButtonVisible=attributes.getBoolean(R.styleable.CustomTitleBar_right_button_visible,true);
            if (rightButtonVisible){
                titleBarRightBtn.setVisibility(View.VISIBLE);
            }else {
                titleBarRightBtn.setVisibility(View.INVISIBLE);
            }
            //设置右边按钮的文字
            String rightButtonText=attributes.getString(R.styleable.CustomTitleBar_right_button_text);
            if (!TextUtils.isEmpty(rightButtonText)){
                titleBarRightBtn.setText(rightButtonText);
                //设置右边按钮文字颜色
                int rightButtonTextColor=attributes.getColor(R.styleable.CustomTitleBar_right_button_text_color,Color.WHITE);
                titleBarRightBtn.setTextColor(rightButtonTextColor);
            }else {
                //设置右边图片icon 二选一，要么是文字，要么只能是图片
                int rightButtonDrawable=attributes.getResourceId(R.styleable.CustomTitleBar_right_button_drawable,-1);
                if (rightButtonDrawable!=-1){
                    titleBarRightBtn.setBackgroundResource(rightButtonDrawable);
                }
            }
            attributes.recycle();
        }
    }

    public void setTitleBarLeftBtnClickListener(OnClickListener onClickListener){
        if (onClickListener!=null){
            titleBarLeftBtn.setOnClickListener(onClickListener);
        }
    }

    public void setTitleBarRightBtnClickListener(OnClickListener onClickListener){
        if (onClickListener!=null){
            titleBarRightBtn.setOnClickListener(onClickListener);
        }
    }

    public Button getTitleBarLeftBtn() {
        return titleBarLeftBtn;
    }

    public Button getTitleBarRightBtn() {
        return titleBarRightBtn;
    }

    public TextView getTitleBarTitle() {
        return titleBarTitle;
    }
}
