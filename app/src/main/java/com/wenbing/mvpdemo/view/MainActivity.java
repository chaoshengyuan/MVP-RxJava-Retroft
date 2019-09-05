package com.wenbing.mvpdemo.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.bean.LoginResponse;
import com.wenbing.mvpdemo.mvp.view.BaseMvpActivity;
import com.wenbing.mvpdemo.mvp.factory.CreatePresenter;

@CreatePresenter(TestPresenter.class)
public class MainActivity extends BaseMvpActivity<TestView, TestPresenter> implements TestView{

    TextView textView;
    Button btnSuccess;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        btnSuccess = findViewById(R.id.btn_success);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);

        if (savedInstanceState != null) {
            System.out.println(savedInstanceState.get("testxxx"));
        }
        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMvpPresenter().requestData(200);
            }
        });
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("testxxx","testxxx_save");
    }

    @Override
    public void showLoading() {
        textView.setText("loading");
//        if (!mProgressDialog.isShowing()) {
//            mProgressDialog.show();
//        }
    }

    @Override
    public void setData(LoginResponse loginResponse) {

    }



    @Override
    public void hideLoading() {
//        if (mProgressDialog.isShowing()) {
//            mProgressDialog.dismiss();
//        }
    }

    @Override
    public void showError(String msg) {
        textView.setText(msg);
    }

    @Override
    public void setBtnEnabled(boolean enabled) {
        btnSuccess.setEnabled(enabled);
    }


}
