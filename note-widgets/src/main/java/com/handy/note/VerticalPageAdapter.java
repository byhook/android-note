package com.handy.note;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.handy.note.adapter.CyclePagerAdapter;
import com.handy.note.helper.PageAction;
import com.handy.note.adapter.NormalPagerAdapter;
import com.handy.note.widgets.R;

import java.util.List;

/**
 * @author: handy
 * @date: 2020-07-13
 * @description:
 */
public class VerticalPageAdapter extends CyclePagerAdapter<TestData> {

    private static final String TAG = "VerticalPageAdapter";

    private OnItemClickListener mOnItemClickListener;

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
    public void onPrevPageLoaded(RecyclerView.ViewHolder holder, TestData data) {
        super.onPrevPageLoaded(holder, data);
    }

    @Override
    public void onNextPageLoaded(RecyclerView.ViewHolder holder, TestData data) {
        super.onNextPageLoaded(holder, data);
    }

    @Override
    public void onCurrentPageLoaded(RecyclerView.ViewHolder holder, TestData data) {
        super.onCurrentPageLoaded(holder, data);
        if (holder != null) {
            RecycleViewHolder itemHolder = (RecycleViewHolder) holder;
            itemHolder.bindData(0, data);
        }
        Log.d(TAG, "VerticalHolder === " + holder + "\r\n data=" + loopQueue.toString());
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
