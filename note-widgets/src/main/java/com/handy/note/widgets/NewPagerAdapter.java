package com.handy.note.widgets;

import android.util.Log;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/**
 * @author: handy
 * @date: 2020-07-14
 * @description:
 */
public abstract class NewPagerAdapter extends ViewPager2.OnPageChangeCallback {

    private static final String TAG = "NewPagerAdapter";

    private RecyclerView recyclerView;

    public void bindRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public abstract int getItemCount();

    public int getItemViewType(int position) {
        return 0;
    }

    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public void onPrevPageLoaded(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onPrevPageLoaded=" + holder + " position=" + position);
    }

    public void onCurrentPageLoaded(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onCurrentPageLoaded=" + holder + " position=" + position);
    }

    public void onNextPageLoaded(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onNextPageLoaded=" + holder + " position=" + position);
    }

    private RecyclerView.ViewHolder getTargetViewHolder(int position) {
        RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(position);
        if (holder == null) {
            holder = recyclerView.getRecycledViewPool().getRecycledView(getItemViewType(position));
//            Log.d(TAG, "getTargetViewHolder cache holder=" + holder);
        } else {
//            Log.d(TAG, "getTargetViewHolder adapter holder=" + holder);
        }
        return holder;
    }

    private int getRealPosition(int position) {
        //计算映射真实的下标
        int startIndex = position - Integer.MAX_VALUE / 2;
        int realItemCount = getItemCount();
        int leftIndex = startIndex % realItemCount;
        return leftIndex >= 0 ? leftIndex : leftIndex + realItemCount;
    }

    @Override
    public void onPageSelected(int position) {
        RecyclerView.ViewHolder prevHolder = getTargetViewHolder(position - 1);
        RecyclerView.ViewHolder nextHolder = getTargetViewHolder(position + 1);
        RecyclerView.ViewHolder currentHolder = getTargetViewHolder(position);

        onPrevPageLoaded(prevHolder, getRealPosition(position - 1));

        onNextPageLoaded(nextHolder, getRealPosition(position + 1));

        onCurrentPageLoaded(currentHolder, getRealPosition(position));

        Log.d(TAG, "getTargetViewHolder" +
                "\r\nprevHolder=" + prevHolder +
                "\r\nnextHolder=" + nextHolder +
                "\r\ncurrentHolder=" + currentHolder);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        Log.d(TAG, "onPageScrolled position=" + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
//        Log.d(TAG, "onPageScrollStateChanged state=" + state);
    }

}
