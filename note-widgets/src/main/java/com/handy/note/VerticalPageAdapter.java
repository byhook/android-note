package com.handy.note;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.handy.note.helper.PageAction;
import com.handy.note.widgets.NewPagerAdapter;
import com.handy.note.widgets.R;

import java.util.List;

/**
 * @author: handy
 * @date: 2020-07-13
 * @description:
 */
public class VerticalPageAdapter extends NewPagerAdapter<TestData> {

    private static final String TAG = "VerticalPageAdapter";

    private OnItemClickListener mOnItemClickListener;

    private String currentData;

    public VerticalPageAdapter(List<TestData> data) {
        super(data);
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
            currentData = loopQueue.getCurrentData();
        } else {
            if (action == PageAction.ACTION_REVERSE) {
                currentData = loopQueue.getPrevData();
            } else {
                currentData = loopQueue.getNextData();
            }
        }

        if (holder != null) {
            RecycleViewHolder itemHolder = (RecycleViewHolder) holder;
            itemHolder.bindData(loopQueue.indexOf(currentData), currentData);
        }

        Log.d(TAG, "VerticalHolder === " + position + " reverse=" + action + "\r\n data=" + loopQueue.toString());
    }

    @Override
    public int getItemCount() {
        return loopQueue.size();
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
            textView.setText("roomId=" + data.roomId);
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
