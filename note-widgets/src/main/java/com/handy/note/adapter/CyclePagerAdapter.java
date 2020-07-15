package com.handy.note.adapter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.handy.note.LoopQueue;
import com.handy.note.helper.PageAction;

import java.util.List;

/**
 * @author: handy
 * @date: 2020-07-14
 * @description:
 */
public abstract class CyclePagerAdapter<T> extends AbsPagerAdapter {

    private static final String TAG = "CyclePagerAdapter";

    private RecyclerView recyclerView;

    private boolean enableLoop;

    private int currentPosition = -Integer.MAX_VALUE;

    private RecyclerView.ViewHolder prevHolder;
    private RecyclerView.ViewHolder nextHolder;
    private RecyclerView.ViewHolder currentHolder;

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

    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder=" + holder + " position=" + position);
        if (prevHolder == null && (currentPosition - 1) == position) {
            prevHolder = holder;
            onPrevPageLoaded(prevHolder, loopQueue.getPrevData());
        }
        if (nextHolder == null && (currentPosition + 1) == position) {
            nextHolder = holder;
            onNextPageLoaded(nextHolder, loopQueue.getNextData());
        }
        if (currentHolder == null && currentPosition == position) {
            currentHolder = holder;
            if (loadAction == null) {
                loadAction = PageAction.ACTION_INIT_OR_SET;
            } else {
                loadAction = PageAction.ACTION_FORWARD;
            }
            onCurrentPageLoaded(currentHolder, getTargetData(loadAction));
        }
    }

    public void onPrevPageLoaded(RecyclerView.ViewHolder holder, T data) {
        Log.d(TAG, "onPrevPageLoaded=" + holder + " position=" + data);
    }

    public void onCurrentPageLoaded(RecyclerView.ViewHolder holder, T data) {
        Log.d(TAG, "onCurrentPageLoaded=" + holder + " position=" + data);
    }

    public void onNextPageLoaded(RecyclerView.ViewHolder holder, T data) {
        Log.d(TAG, "onNextPageLoaded=" + holder + " position=" + data);
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
        if (currentPosition == -Integer.MAX_VALUE) {
            currentPosition = position;
            Log.d(TAG, "onPageSelected init " + position + " select" + Integer.MAX_VALUE / 2);
        } else {
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
            Log.d(TAG, "onPageSelected" +
                    "\r\nposition=" + position +
                    "\r\nprevHolder=" + prevHolder +
                    "\r\nnextHolder=" + nextHolder +
                    "\r\ncurrentHolder=" + currentHolder);
        }
        currentPosition = position;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        Log.d(TAG, "onPageScrolled position=" + position + " currentHolder=" + currentHolder);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
//        Log.d(TAG, "onPageScrollStateChanged state=" + state + " size=" + recyclerView.getRecycledViewPool().getRecycledViewCount(0));



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
