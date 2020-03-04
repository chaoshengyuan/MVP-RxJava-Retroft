package com.wenbing.mvpdemo.module;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.base.BaseActivity;
import com.wenbing.mvpdemo.beans.Banner;

import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {

    TextView textView;
    Button btnSuccess;

    @Override
    protected MainPresenter createrPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int initLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        textView = findViewById(R.id.textView);
        btnSuccess = findViewById(R.id.btn_success);
    }

    @Override
    protected void initViewListener() {
        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.requestData();
            }
        });
    }

    @Override
    public void showData(List<Banner> banners) {
        textView.setText(new Gson().toJson(banners));
    }
}
