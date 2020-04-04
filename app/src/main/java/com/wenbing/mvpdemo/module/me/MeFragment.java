package com.wenbing.mvpdemo.module.me;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.base.BaseFragment;
import com.wenbing.mvpdemo.beans.CoinBean;
import com.wenbing.mvpdemo.beans.LoginBean;
import com.wenbing.mvpdemo.event.LoginEvent;
import com.wenbing.mvpdemo.module.collect.CollectListActivity;
import com.wenbing.mvpdemo.module.login.LoginActivity;
import com.wenbing.mvpdemo.utils.UserUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author: wenbing
 * @date: 2020/3/5 10:47
 */
public class MeFragment extends BaseFragment<MePresenter> implements IMeView, View.OnClickListener {
    private  TextView tvAccount,tvRanking,tvCoinCount;

    @Override
    protected int initLayoutID() {
        return R.layout.fragment_me;
    }

    @Override
    protected MePresenter createrPresenter() {
        return new MePresenter();
    }

    @Override
    protected void initViewsAndListener() {

        View layoutCoin = $(R.id.layout_coin);
        View layoutCollected = $(R.id.layout_collected);
        View layoutSetting = $(R.id.layout_setting);

        tvAccount = $(R.id.tv_account);
        tvRanking = $(R.id.tv_ranking);
        tvCoinCount = $(R.id.tv_coinCount);

        tvAccount.setOnClickListener(this);
        layoutCoin.setOnClickListener(this);
        layoutCollected.setOnClickListener(this);
        layoutSetting.setOnClickListener(this);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void initData() {
        if(UserUtils.getInstance().isLogin()){
            mPresenter.requestData();
        }
        setViewData();
    }

    private void setViewData() {
        if (UserUtils.getInstance().isLogin()) {
            LoginBean bean = UserUtils.getInstance().getLoginBean();
            tvAccount.setText(bean.getUsername());
        } else {
            tvAccount.setText("去登陆");
        }
    }

    @Override
    public void showData(CoinBean coinBean) {
        if(coinBean == null){
            tvAccount.setText("请登录");
            tvRanking.setText("排名：--");
            tvCoinCount.setText("积分：--");
        }else{
            tvAccount.setText(coinBean.getUsername());
            tvRanking.setText("排名："+coinBean.getRank());
            tvCoinCount.setText("积分："+coinBean.getCoinCount());
        }
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_account:
                startActivity(new Intent(mContext, LoginActivity.class));
                break;
            case R.id.layout_coin:
                break;
            case R.id.layout_collected:
                CollectListActivity.start(mContext);
                break;
            case R.id.layout_setting:

                break;
            default:
                break;
        }
    }

    /**
     * {@link LoginActivity#loginSusscee}
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event){
        if(!isDetached()){
            initData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
