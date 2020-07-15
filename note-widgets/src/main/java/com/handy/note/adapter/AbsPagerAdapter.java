package com.handy.note.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/**
 * @author: handy
 * @date: 2020-07-15
 * @description:
 */
public abstract class AbsPagerAdapter extends ViewPager2.OnPageChangeCallback{

    public abstract void bindRecyclerView(RecyclerView recyclerView);

    public abstract void enableLoop(boolean enableLoop);

    public abstract int getItemCount();

    public abstract RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    public abstract void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position);

}
