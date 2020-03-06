package com.wenbing.mvpdemo.module.adapter.base;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CommonViewHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> mViews;
    public View itemView;

    public CommonViewHolder(View itemView,final BaseRVAdapter adapter) {
        super(itemView);
        this.mViews = new SparseArray<>();
        this.itemView = itemView;
        //添加Item的点击事件
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickLinsener != null) {
                    mOnItemClickLinsener.onItemClick(adapter, getAdapterPosition());
                }
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickLinsener != null) {
                    mOnItemLongClickLinsener.onItemLongClick(adapter, getAdapterPosition());
                }
                return false;
            }
        });
    }


    public <T extends View> T $(int viewId) {

        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            if (view == null) {
                return null;
            }
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public void setText(int viewId, CharSequence text) {
        TextView tv = $(viewId);
        tv.setText(text);
    }

    /**
     * Will set the image of an ImageView from a resource id.
     *
     * @param viewId     The view id.
     * @param imageResId The image resource id.
     * @return The CommonViewHolder for chaining.
     */
    public CommonViewHolder setImageResource(@IdRes int viewId, @DrawableRes int imageResId) {
        ImageView view = $(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    /**
     * Will set background color of a view.
     *
     * @param viewId The view id.
     * @param color  A color, not a resource id.
     * @return The CommonViewHolder for chaining.
     */
    public CommonViewHolder setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
        View view = $(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * Will set background of a view.
     *
     * @param viewId        The view id.
     * @param backgroundRes A resource to use as a background.
     * @return The CommonViewHolder for chaining.
     */
    public CommonViewHolder setBackgroundRes(@IdRes int viewId, @DrawableRes int backgroundRes) {
        View view = $(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    /**
     * Will set text color of a TextView.
     *
     * @param viewId    The view id.
     * @param textColor The text color (not a resource id).
     * @return The CommonViewHolder for chaining.
     */
    public CommonViewHolder setTextColor(@IdRes int viewId, @ColorInt int textColor) {
        TextView view = $(viewId);
        view.setTextColor(textColor);
        return this;
    }


    /**
     * Will set the image of an ImageView from a drawable.
     *
     * @param viewId   The view id.
     * @param drawable The image drawable.
     * @return The CommonViewHolder for chaining.
     */
    public CommonViewHolder setImageDrawable(@IdRes int viewId, Drawable drawable) {
        ImageView view = $(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * Add an action to set the image of an image view. Can be called multiple times.
     */
    public CommonViewHolder setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        ImageView view = $(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    private OnItemClickLinsener mOnItemClickLinsener;

    public void setOnItemClickLinsener(OnItemClickLinsener linsener) {
        mOnItemClickLinsener = linsener;
    }

    OnItemLongClickLinsener mOnItemLongClickLinsener;

    public void setOnItemLongClickLinsener(OnItemLongClickLinsener linsener) {
        mOnItemLongClickLinsener = linsener;
    }

    /**
     * XRecyclerView的Item的点击事件
     */
    public interface OnItemClickLinsener {
        /**
         * onItemClick
         *
         * @param baseAdapter
         * @param position
         */
        void onItemClick(BaseRVAdapter baseAdapter, int position);
    }

    /**
     * XRecyclerView的Item的点击事件
     */
    public interface OnItemLongClickLinsener {
        /**
         * onItemClick
         *
         * @param baseAdapter
         * @param position
         */
        void onItemLongClick(BaseRVAdapter baseAdapter, int position);
    }
}