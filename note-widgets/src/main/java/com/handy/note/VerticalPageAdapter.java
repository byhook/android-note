package com.handy.note;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.handy.note.helper.PageAction;
import com.handy.note.widgets.NewPagerAdapter;
import com.handy.note.widgets.R;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: handy
 * @date: 2020-07-13
 * @description:
 */
public class VerticalPageAdapter extends NewPagerAdapter {

    private static final String TAG = "VerticalPageAdapter";

    private LoopQueue<TestData> loopData;
    private OnItemClickListener mOnItemClickListener;

    private String currentData;

    public VerticalPageAdapter(List<TestData> data) {
        this.loopData = new LoopQueue<>(data);
    }

    public void insertNextData(TestData testData){
        loopData.insertNextData(testData);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder " + viewType);
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_page_item_layer, parent, false);
        return new RecycleViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

    }

    @Override
    public void onPrevPageLoaded(RecyclerView.ViewHolder holder, int position) {
        super.onPrevPageLoaded(holder, position);
    }

    @Override
    public void onNextPageLoaded(RecyclerView.ViewHolder holder, int position) {
        super.onNextPageLoaded(holder, position);
    }

    @Override
    public void onCurrentPageLoaded(RecyclerView.ViewHolder holder, int position, @PageAction int action) {
        super.onCurrentPageLoaded(holder, position, action);
        TestData currentData;
        if (action == PageAction.ACTION_INIT_OR_SET) {
            currentData = loopData.getData(position);
        } else {
            if (action == PageAction.ACTION_REVERSE) {
                currentData = loopData.getPrevData();
            } else {
                currentData = loopData.getNextData();
            }
        }
        Log.d(TAG, "onBindViewHolder " + position + " reverse=" + action);
        if (holder != null) {
            RecycleViewHolder itemHolder = (RecycleViewHolder) holder;
            itemHolder.bindData(loopData.indexOf(currentData), currentData);
        }
    }

    @Override
    public int getItemCount() {
        return loopData.size();
    }

    private class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        private int index;
        private TestData data;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.btn_show);
            textView.setOnClickListener(this);
        }

        public void bindData(int index, TestData data) {
            this.index = index;
            this.data = data;
            textView.setText("index=" + index + " data=" + data.index);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(index);
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

        void onItemClick(int index);

    }

}
