package com.handy.note;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.handy.note.widgets.NewPagerAdapter;
import com.handy.note.widgets.R;

import java.util.List;

/**
 * @author: handy
 * @date: 2020-07-13
 * @description:
 */
public class VerticalPageAdapter extends NewPagerAdapter {

    private static final String TAG = "VerticalPageAdapter";

    private List<String> data;
    private OnItemClickListener mOnItemClickListener;

    public VerticalPageAdapter(List<String> data){
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG,"onCreateViewHolder " + viewType);
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_page_item_layer,parent,false);
        return new RecycleViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder,position);
        Log.d(TAG,"onBindViewHolder " + position);
        RecycleViewHolder itemHolder = (RecycleViewHolder) holder;
        itemHolder.bindData(position);
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
    public void onCurrentPageLoaded(RecyclerView.ViewHolder holder, int position) {
        super.onCurrentPageLoaded(holder, position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        private int index;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.btn_show);
            textView.setOnClickListener(this);
        }

        public void bindData(int index){
            this.index = index;
            textView.setText("index=" + index  + " data=" + data.get(index));
        }

        @Override
        public void onClick(View v) {
            if(mOnItemClickListener!=null){
                mOnItemClickListener.onItemClick(index);
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

        void onItemClick(int index);

    }

}
