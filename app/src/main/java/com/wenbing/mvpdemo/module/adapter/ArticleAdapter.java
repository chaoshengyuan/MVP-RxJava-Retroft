package com.wenbing.mvpdemo.module.adapter;


import android.content.Context;
import android.text.TextUtils;

import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.beans.ArticleBean;
import com.wenbing.mvpdemo.module.adapter.base.BaseRVAdapter;
import com.wenbing.mvpdemo.module.adapter.base.CommonViewHolder;

import java.util.List;

/**
 * @author: wenbing
 * @date: 2020/3/8 11:54
 */
public class ArticleAdapter extends BaseRVAdapter<ArticleBean.DataBean> {
    public ArticleAdapter(Context context, List<ArticleBean.DataBean> beans) {
        super(context, beans);
    }

    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.item_article;
    }

    @Override
    protected void onBindDataToView(CommonViewHolder holder, ArticleBean.DataBean bean, int position) {
        holder.setText(R.id.tv_author, TextUtils.isEmpty(bean.getAuthor()) ? bean.getShareUser() : bean.getAuthor());
        holder.setText(R.id.tv_time, bean.getNiceDate());
        holder.setText(R.id.tv_title, bean.getTitle());
        holder.setText(R.id.tv_chapter, bean.getSuperChapterName() + "-" + bean.getChapterName());
        if(bean.isCollect()){
            holder.setImageResource(R.id.iv_state,R.drawable.collected);
        }else{
            holder.setImageResource(R.id.iv_state,R.drawable.un_collected);
        }
    }
}
