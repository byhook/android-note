package com.handy.note;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.handy.note.adapter.CyclePagerAdapter;
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

    @Override
    public void onPrevPageLoaded(FragmentViewHolder holder, TestData data) {
        super.onPrevPageLoaded(holder, data);
        FragmentActivity fragmentActivity = (FragmentActivity) holder.itemView.getContext();
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("f"+holder.getItemId());
        Log.d(TAG, "onPrevPageLoaded " + data +
                "\r\n" + fragment
        );
    }

    @Override
    public void onNextPageLoaded(FragmentViewHolder holder, TestData data) {
        super.onNextPageLoaded(holder, data);
        FragmentActivity fragmentActivity = (FragmentActivity) holder.itemView.getContext();
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("f"+holder.getItemId());
        Log.d(TAG, "onNextPageLoaded " + data +
                "\r\n" + fragment
        );
    }

    @Override
    public void onCurrentPageLoaded(FragmentViewHolder holder, TestData data) {
        super.onCurrentPageLoaded(holder, data);
        if (holder != null) {
            FragmentActivity fragmentActivity = (FragmentActivity) holder.itemView.getContext();
            FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
            LivePageFragment fragment = (LivePageFragment) fragmentManager.findFragmentByTag("f"+holder.getItemId());
            fragment.updateFragment(data);
            /*if(holder.itemView.getContext() instanceof FragmentActivity){
                FragmentActivity hostActivity = (FragmentActivity) holder.itemView.getContext();
                hostActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(holder.itemView.getId(), LivePageFragment.newInstance())
                        .commit();
            }*/
            Log.d(TAG, "VerticalHolder === " + holder +
                    "\r\nposition=" + holder.getAdapterPosition() +
                    "\r\n data=" + loopQueue.toString() +
                    "\r\nfragment=" + fragment);
        }

    }

    @Override
    public int getItemCount() {
        return loopQueue.size();
    }

    /*private class RecycleViewHolder extends FragmentViewHolder implements View.OnClickListener {

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
            textView.setText("roomId=" + data.roomId);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(index);
            }
        }
    }*/

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

        void onItemClick(int index);

    }

}
