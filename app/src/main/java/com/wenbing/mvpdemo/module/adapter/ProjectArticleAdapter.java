package com.wenbing.mvpdemo.module.adapter;


import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.beans.ProjectArticle;
import com.wenbing.mvpdemo.module.adapter.base.BaseRVAdapter;
import com.wenbing.mvpdemo.module.adapter.base.CommonViewHolder;

import java.util.List;

/**
 * @author: wenbing
 * @date: 2020/3/8 11:54
 */
public class ProjectArticleAdapter extends BaseRVAdapter<ProjectArticle.DatasBean> {
    public ProjectArticleAdapter(Context context, List<ProjectArticle.DatasBean> beans) {
        super(context, beans);
    }

    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.item_project_article;
    }

    @Override
    protected void onBindDataToView(CommonViewHolder holder, ProjectArticle.DatasBean bean, int position) {
        holder.setText(R.id.tv_author, TextUtils.isEmpty(bean.getAuthor()) ? bean.getShareUser() : bean.getAuthor());
        holder.setText(R.id.tv_time, bean.getNiceDate());
        holder.setText(R.id.tv_title, bean.getTitle());
        holder.setText(R.id.tv_content, bean.getDesc());
        holder.setText(R.id.tv_chapter, bean.getSuperChapterName() + "-" + bean.getChapterName());
        ImageView iv = holder.$(R.id.iv_icon);
//        RequestOptions options = new RequestOptions()
//                .placeholder(R.drawable.img_default)//图片加载出来前，显示的图片
//                .fallback( R.drawable.img_blank) //url为空的时候,显示的图片
//                .error(drawable.img_load_failure);//图片加载失败后，显示的图片
//        .apply(options)
        Glide.with(mContext).load(bean.getEnvelopePic()).into(iv);
//        holder.setImageBitmap(R.id.iv_icon,)
    }
}
