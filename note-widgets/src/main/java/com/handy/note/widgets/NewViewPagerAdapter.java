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

    public NewViewPagerAdapter(RecyclerView.Adapter adapter) {
        pagerAdapter = adapter;
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
            int index = 0;
            if (tempIndex < 0) {
                //逆向滑动
                int leftIndex = tempIndex % realItemCount;
                index = realItemCount + leftIndex;
                Log.d(TAG, "onBindViewHolder《= tempIndex= " + tempIndex + " index=" + index + " realItemCount=" + realItemCount);
            } else if (tempIndex > 0) {
                //顺向滑动
                int leftIndex = tempIndex % realItemCount;
                index = leftIndex;
                Log.d(TAG, "onBindViewHolder =》tempIndex= " + tempIndex + " index=" + index + " realItemCount=" + realItemCount);
            }
            Log.d(TAG, "onBindViewHolder realItemCount " + realItemCount);
            pagerAdapter.onBindViewHolder(holder, index);
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
