package com.handy.note.adapter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.handy.note.LoopQueue;
import com.handy.note.helper.PageAction;

import java.util.List;

/**
 * @author: handy
 * @date: 2020-07-14
 * @description:
 */
public abstract class CyclePagerAdapter<T> extends AbsFragmentPagerAdapter {

    private static final String TAG = "CyclePagerAdapter";

    private RecyclerView recyclerView;

    private boolean enableLoop;

    private int currentPosition = -Integer.MAX_VALUE;

    private FragmentViewHolder prevHolder;
    private FragmentViewHolder nextHolder;
    private FragmentViewHolder currentHolder;

    protected LoopQueue<T> loopQueue;

    private Integer loadAction;

    public CyclePagerAdapter(List<T> data) {
        this.loopQueue = new LoopQueue<>(data);
    }

    public void bindRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void enableLoop(boolean enableLoop) {
        this.enableLoop = enableLoop;
    }

    /**
     * 插队逻辑
     *
     * @param data
     * @return
     */
    public boolean insertNextData(T data) {
        return loopQueue.insertNextData(data);
    }

    public boolean appendData(List<T> data) {
        return loopQueue.appendData(data);
    }

    public abstract int getItemCount();

    public int getItemViewType(int position) {
        return 0;
    }

    public void onPrevPageLoaded(FragmentViewHolder holder, T data) {
        Log.d(TAG, "onPrevPageLoaded=" + holder + " position=" + data);
    }

    public void onCurrentPageLoaded(FragmentViewHolder holder, T data) {
        Log.d(TAG, "onCurrentPageLoaded=" + holder + " position=" + data);
    }

    public void onNextPageLoaded(FragmentViewHolder holder, T data) {
        Log.d(TAG, "onNextPageLoaded=" + holder + " position=" + data);
    }

    private FragmentViewHolder getTargetViewHolder(int position) {
        FragmentViewHolder holder = (FragmentViewHolder) recyclerView.findViewHolderForAdapterPosition(position);
        if (holder == null) {
            holder = (FragmentViewHolder) recyclerView.getRecycledViewPool().getRecycledView(getItemViewType(position));
//            Log.d(TAG, "getTargetViewHolder cache holder=" + holder);
        } else {
//            Log.d(TAG, "getTargetViewHolder adapter holder=" + holder);
        }
        return holder;
    }

    protected int getRealPosition(int position) {
        //计算映射真实的下标
        int startIndex = position - Integer.MAX_VALUE / 2;
        if (enableLoop) {
            return startIndex;
        } else {
            int realItemCount = getItemCount();
            int leftIndex = startIndex % realItemCount;
            return leftIndex >= 0 ? leftIndex : leftIndex + realItemCount;
        }
    }

    private T getTargetData(int action) {
        T currentData = null;
        if (action == PageAction.ACTION_INIT_OR_SET) {
            currentData = loopQueue.getCurrentData();
        } else {
            if (action == PageAction.ACTION_REVERSE) {
                currentData = loopQueue.movePrevData();
            } else {
                currentData = loopQueue.moveNextData();
            }
        }
        return currentData;
    }

    /**
     * 第一次的时候
     * onPageSelected会比onBindViewHolder先调用
     * 所以第一次的时候holder为空
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        if (currentPosition != -Integer.MAX_VALUE) {
            currentHolder = getTargetViewHolder(position);
            if (currentHolder != null && currentHolder.getAdapterPosition() >= 0) {
                prevHolder = getTargetViewHolder(position - 1);
                nextHolder = getTargetViewHolder(position + 1);

                if(prevHolder!=null){
                    onPrevPageLoaded(prevHolder, loopQueue.getPrevData());
                }

                if(nextHolder != null){
                    onNextPageLoaded(nextHolder, loopQueue.getNextData());
                }

                int action = position < currentPosition ? PageAction.ACTION_REVERSE : PageAction.ACTION_FORWARD;
                onCurrentPageLoaded(currentHolder, getTargetData(action));
            } else {
                prevHolder = null;
                nextHolder = null;
                currentHolder = null;
            }
            currentPosition = position;
            Log.d(TAG, "onPageSelected" +
                    "\r\nposition=" + position +
                    "\r\nprevHolder=" + prevHolder +
                    "\r\nnextHolder=" + nextHolder +
                    "\r\ncurrentHolder=" + currentHolder);
        }
    }

    public int getRelationPostion(){
        return currentPosition;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d(TAG, "onPageScrolled position=" + position + " currentHolder=" + getTargetViewHolder(position + 1));
        if(currentPosition == -Integer.MAX_VALUE){
            currentPosition = position;
            currentHolder = getTargetViewHolder(position);
            if (currentHolder != null && currentHolder.getAdapterPosition() >= 0) {
                prevHolder = getTargetViewHolder(position - 1);
                nextHolder = getTargetViewHolder(position + 1);

                onPrevPageLoaded(prevHolder, loopQueue.getPrevData());
                onNextPageLoaded(nextHolder, loopQueue.getNextData());

                int action = position < currentPosition ? PageAction.ACTION_REVERSE : PageAction.ACTION_FORWARD;
                onCurrentPageLoaded(currentHolder, getTargetData(action));
            } else {
                prevHolder = null;
                nextHolder = null;
                currentHolder = null;
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d(TAG, "onPageScrollStateChanged state=" + state + " size=" + recyclerView.getRecycledViewPool().getRecycledViewCount(0));

    }

    private void LoadPageDelay(){
        handler.removeCallbacksAndMessages(null);
        handler.sendEmptyMessageDelayed(100,200);
    }

    private Handler handler = new Handler(Looper.getMainLooper()){

        @Override
        public void handleMessage(@NonNull Message msg) {



        }

    };

}
