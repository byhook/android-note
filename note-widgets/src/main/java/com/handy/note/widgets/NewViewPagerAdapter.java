package com.handy.note.widgets;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author: handy
 * @date: 2020-07-13
 * @description:
 */
public class NewViewPagerAdapter extends RecyclerView.Adapter {

    private static final String TAG = "NewViewPagerAdapter";

    private RecyclerView.Adapter pagerAdapter;

    private OnPageCenterListener mOnPageCenterListener;

    private int shadowIndex = Integer.MAX_VALUE / 2;

    private boolean changed;

    public NewViewPagerAdapter(RecyclerView.Adapter adapter, boolean changed) {
        this.pagerAdapter = adapter;
        this.changed = changed;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
//        Log.d(TAG, "onAttachedToRecyclerView ");
        if (mOnPageCenterListener != null) {
            mOnPageCenterListener.onPageCenter();
        }
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
//        Log.d(TAG, "onDetachedFromRecyclerView ");
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
//        Log.d(TAG, "onViewAttachedToWindow ");
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
//        Log.d(TAG, "onViewDetachedFromWindow ");
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.d(TAG, "onCreateViewHolder " + viewType);
        return pagerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public final void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (pagerAdapter != null) {
            int realItemCount = pagerAdapter.getItemCount();
            int tempIndex = position - shadowIndex;
            if (changed) {
                //逆向滑动为负数 正向滑动为正数
                pagerAdapter.onBindViewHolder(holder, tempIndex);
                Log.d(TAG, "onBindViewHolder realItemCount " + tempIndex);
            } else {
                int leftIndex = tempIndex % realItemCount;
                int realIndex = leftIndex >= 0 ? leftIndex : leftIndex + realItemCount;
                Log.d(TAG, "onBindViewHolder realItemCount " + tempIndex);
                pagerAdapter.onBindViewHolder(holder, realIndex);
            }

        } else {
            Log.d(TAG, "onBindViewHolder " + position);
        }
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
//        Log.d(TAG, "onViewRecycled " + holder);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public void setPageCenterListener(OnPageCenterListener onPageCenterListener) {
        this.mOnPageCenterListener = onPageCenterListener;
    }

    public interface OnPageCenterListener {

        void onPageCenter();

    }

}
