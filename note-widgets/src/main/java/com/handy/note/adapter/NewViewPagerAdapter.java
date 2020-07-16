package com.handy.note.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.handy.note.LivePageFragment;

/**
 * @author: handy
 * @date: 2020-07-13
 * @description:
 */
public class NewViewPagerAdapter extends FragmentStateAdapter {

    private static final String TAG = "NewViewPagerAdapter";

    private AbsFragmentPagerAdapter pagerAdapter;

    private OnPageCenterListener mOnPageCenterListener;

    private int shadowIndex = Integer.MAX_VALUE / 2;

    private boolean changed;

    private boolean enableLoop;

    public NewViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, AbsFragmentPagerAdapter adapter, boolean changed) {
        super(fragmentActivity);
        this.pagerAdapter = adapter;
        this.changed = changed;
    }

    public void enableLoop(boolean enableLoop) {
        this.enableLoop = enableLoop;
    }

    public boolean isEnableLoop() {
        return enableLoop;
    }

    public AbsFragmentPagerAdapter getAdapter() {
        return pagerAdapter;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
//        Log.d(TAG, "onAttachedToRecyclerView ");
        recyclerView.setItemViewCacheSize(1);
        pagerAdapter.enableLoop(enableLoop);
        pagerAdapter.bindRecyclerView(recyclerView);
        if (mOnPageCenterListener != null) {
            mOnPageCenterListener.onPageCenter();
        }
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d(TAG,"createFragment position=" + position);
        return LivePageFragment.newInstance();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull FragmentViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Log.d(TAG,"onViewDetachedFromWindow holder=" + holder);
    }

    @Override
    public long getItemId(int position) {
        return position % 7;
    }

    /*@NonNull
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
    }*/

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
