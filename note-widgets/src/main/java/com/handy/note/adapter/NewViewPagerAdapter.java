package com.handy.note.adapter;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author: handy
 * @date: 2020-07-13
 * @description:
 */
public class NewViewPagerAdapter extends RecyclerView.Adapter implements IPagerAdapter {

    private static final String TAG = "NewViewPagerAdapter";

    private CyclePagerAdapter pagerAdapter;

    private OnPageCenterListener mOnPageCenterListener;

    private int shadowIndex = Integer.MAX_VALUE / 2;

    private boolean changed;

    private boolean enableLoop;

    public NewViewPagerAdapter(CyclePagerAdapter adapter, boolean changed) {
        this.pagerAdapter = adapter;
        this.changed = changed;
    }

    public void enableLoop(boolean enableLoop) {
        this.enableLoop = enableLoop;
    }

    public boolean isEnableLoop() {
        return enableLoop;
    }

    public CyclePagerAdapter getAdapter() {
        return pagerAdapter;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
//        Log.d(TAG, "onAttachedToRecyclerView ");
        pagerAdapter.enableLoop(enableLoop);
        pagerAdapter.bindRecyclerView(recyclerView);
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
        Log.d(TAG, "onXCreateViewHolder " + viewType);
        return pagerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public final void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder ==== " + holder);
        if (pagerAdapter != null) {
            int realItemCount = pagerAdapter.getItemCount();
            int tempIndex = position - shadowIndex;
            if (changed) {
                //逆向滑动为负数 正向滑动为正数
                pagerAdapter.onBindViewHolder(holder, position);
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
        Log.d(TAG, "onViewRecycled " + holder);
    }

    @Override
    public int getItemCount() {
        return enableLoop ? Integer.MAX_VALUE : getRealItemCount();
    }

    public int getRealItemCount() {
        return pagerAdapter.getItemCount();
    }

    public void setPageCenterListener(OnPageCenterListener onPageCenterListener) {
        this.mOnPageCenterListener = onPageCenterListener;
    }

    public interface OnPageCenterListener {

        void onPageCenter();

    }

}
