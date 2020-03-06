package com.wenbing.mvpdemo.module.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.beans.Tree;
import com.wenbing.mvpdemo.module.adapter.base.BaseRVAdapter;
import com.wenbing.mvpdemo.module.adapter.base.CommonViewHolder;
import com.wenbing.mvpdemo.utils.Utils;
import com.wenbing.mvpdemo.widget.FlowLayout;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * @author: wenbing
 * @date: 2020/3/5 15:43
 */
public class TreeAdapter extends BaseRVAdapter<Tree> {

    public TreeAdapter(Context context, List<Tree> beans) {
        super(context, beans);
    }

    @Override
    public int getItemLayoutID(int viewType) {
        return R.layout.item_tree;
    }

    private Queue<TextView> mFlexItemTVCaches = new LinkedList<>();
    @Override
    protected void onBindDataToView(CommonViewHolder holder, Tree bean, int position) {
        holder.setText(R.id.tv_title,bean.getName());

        FlowLayout flowLayout = holder.$(R.id.flowLayout);
        flowLayout.removeAllViews();
        for (int i = 0; i < bean.getChildren().size(); i++) {
            TextView child = createOrGetCacheFlexItemTextView(bean.getChildren().get(i));
            child.setText(bean.getChildren().get(i).getName());
            flowLayout.addView(child);
        }
    }

    @Override
    public void onViewRecycled(@NonNull CommonViewHolder holder) {
        super.onViewRecycled(holder);
        FlowLayout fbl = holder.$(R.id.flowLayout);
        for (int i = 0; i < fbl.getChildCount(); i++) {
            mFlexItemTVCaches.offer((TextView) fbl.getChildAt(i));
        }
    }

    private TextView createOrGetCacheFlexItemTextView(Tree.ChildrenBean bean) {
        TextView tv = mFlexItemTVCaches.poll();
        if (tv != null) {
            return tv;
        }
        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 15, 15, 20);
        tv = new TextView(mContext);
        tv.setLayoutParams(layoutParams);
        tv.setBackgroundResource(R.drawable.flow_tag_bg);
        Random myRandom = new Random();
        int ranColor = 0xf0000000 | myRandom.nextInt(0x00f00000);
        tv.setTextColor(ranColor);
        tv.setPadding(Utils.pixelsToDip(mContext, 5), Utils.pixelsToDip(mContext, 5),
                Utils.pixelsToDip(mContext, 5), Utils.pixelsToDip(mContext, 5));
        tv.setTextSize(14);
        tv.setMaxLines(1);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }
}
