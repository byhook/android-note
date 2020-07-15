package com.handy.note;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

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
    public void onPrevPageLoaded(RecyclerView.ViewHolder holder, TestData data) {
        super.onPrevPageLoaded(holder, data);
        Log.d(TAG, "onPrevPageLoaded " + data);
    }

    @Override
    public void onNextPageLoaded(RecyclerView.ViewHolder holder, TestData data) {
        super.onNextPageLoaded(holder, data);
        Log.d(TAG, "onNextPageLoaded " + data);
    }

    @Override
    public void onCurrentPageLoaded(RecyclerView.ViewHolder holder, TestData data) {
        super.onCurrentPageLoaded(holder, data);
        if (holder != null) {
            if(holder.itemView.getContext() instanceof FragmentActivity){
                FragmentActivity hostActivity = (FragmentActivity) holder.itemView.getContext();
                hostActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(holder.itemView.getId(), LivePageFragment.newInstance())
                        .commit();
            }

            /*RecycleViewHolder itemHolder = (RecycleViewHolder) holder;
            itemHolder.bindData(0, data);*/
        }
        Log.d(TAG, "VerticalHolder === " + holder + "\r\n data=" + loopQueue.toString());
    }

    @Override
    public int getItemCount() {
        return loopQueue.size();
    }

    private class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        private ViewPager viewPager;
        private int index;
        private TestData data;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            viewPager = itemView.findViewById(R.id.view_pager);
            textView = itemView.findViewById(R.id.btn_show);
            textView.setOnClickListener(this);
        }

        public void bindData(int index, TestData data) {
            this.index = index;
            this.data = data;

            /*viewPager.setAdapter(new FragmentPagerAdapter(((FragmentActivity)itemView.getContext()).getSupportFragmentManager()) {
                @Override
                public int getCount() {
                    return 3;
                }

                @NonNull
                @Override
                public Fragment getItem(int position) {
                    return LivePageFragment.newInstance();
                }

                /*@NonNull
                @Override
                public Object instantiateItem(@NonNull ViewGroup container, int position) {
                    View rootView = LayoutInflater.from(container.getContext()).inflate(R.layout.new_page,container,false);
                    container.addView(rootView);
                    return rootView;
                }

                @Override
                public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                    View rootView = (View) object;
                    container.removeView(rootView);
                }
            });*/

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
