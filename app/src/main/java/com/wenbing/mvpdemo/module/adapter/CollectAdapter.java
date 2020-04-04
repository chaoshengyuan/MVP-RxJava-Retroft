/**
 * Copyright (C), 2015-2020, XXX有限公司
 * Author: zwb
 * Date: 2020/4/4 15:08
 * Description: dd
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.wenbing.mvpdemo.module.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.beans.ArticleBean;
import com.wenbing.mvpdemo.module.adapter.base.BaseRVAdapter;
import com.wenbing.mvpdemo.module.adapter.base.CommonViewHolder;

import java.util.List;

/**
 * @Author: zwb
 * @Date: 2020/4/4 15:08
 */
public class CollectAdapter extends BaseRVAdapter<ArticleBean.DataBean> {

    public CollectAdapter(Context context, List<ArticleBean.DataBean> beans) {
        super(context, beans);
    }
    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.item_collect;
    }

    @Override
    protected void onBindDataToView(CommonViewHolder holder, ArticleBean.DataBean bean, int position) {
        holder.setText(R.id.tv_author, TextUtils.isEmpty(bean.getAuthor()) ? "匿名" : bean.getAuthor());
        holder.setText(R.id.tv_title, bean.getTitle());
        holder.setText(R.id.tv_chapter, bean.getChapterName());
        holder.setImageResource(R.id.iv_state,R.drawable.collected);
    }
}