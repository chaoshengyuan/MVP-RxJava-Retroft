package com.wenbing.mvpdemo.module.adapter;

import android.content.Context;

import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.beans.Article;
import com.wenbing.mvpdemo.module.adapter.base.BaseRVAdapter;
import com.wenbing.mvpdemo.module.adapter.base.CommonViewHolder;

import java.util.List;

/**
 * @author: wenbing
 * @date: 2020/3/5 13:05
 */
public class HomeAdapter extends BaseRVAdapter<Article.DataDetailBean> {

    public HomeAdapter(Context context, List<Article.DataDetailBean> beans) {
        super(context, beans);
    }

    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.item_home;
    }

    @Override
    protected void onBindDataToView(CommonViewHolder holder, Article.DataDetailBean bean, int position) {
        holder.setText(R.id.tv_chapter, bean.chapterName);
        holder.setText(R.id.tv_time,  bean.niceDate);
        holder.setText(R.id.tv_title, bean.title);
        holder.setText(R.id.tv_author, bean.author);
    }
}
