package com.handy.note.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.handy.note.adapter.AbsPagerAdapter;
import com.handy.note.adapter.NewViewPagerAdapter;

/**
 * @author: handy
 * @date: 2020-07-13
 * @description:
 */
public class NewViewPager extends FrameLayout implements NewViewPagerAdapter.OnPageCenterListener {

    private static final String TAG = "NewViewPager";

    public static final int MIDDLE_POINT = Integer.MAX_VALUE / 2;

    private ViewPager2 viewPager;

    private NewViewPagerAdapter newViewPagerAdapter;

    private boolean enableLoop = true;

    public NewViewPager(@NonNull Context context) {
        this(context, null);
    }

    public NewViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        viewPager = new ViewPager2(context);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        addView(viewPager, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    public void setCurrentItem(int item) {
        setCurrentItem(item,false);
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        if (newViewPagerAdapter != null) {
            if (newViewPagerAdapter.isEnableLoop()) {
                int realItem = MIDDLE_POINT + item;// % newViewPagerAdapter.getRealItemCount();
                viewPager.setCurrentItem(realItem, smoothScroll);
            } else {
                viewPager.setCurrentItem(item, smoothScroll);
            }
        }
    }

    public void turnNextItem() {
        turnNextItem(false);
    }

    public void turnNextItem(boolean smoothScroll) {
        int currentItem = viewPager.getCurrentItem() - MIDDLE_POINT;
        Log.d(TAG,"turnNextItem currentItem=" + currentItem);
        setCurrentItem(currentItem + 1,smoothScroll);
    }

    public void setAdapter(AbsPagerAdapter adapter) {
        if (adapter != null) {
            if (newViewPagerAdapter == null || newViewPagerAdapter.getAdapter() != adapter) {
                newViewPagerAdapter = new NewViewPagerAdapter(adapter, true);
                newViewPagerAdapter.enableLoop(enableLoop);
                newViewPagerAdapter.setPageCenterListener(this);
            }
            viewPager.registerOnPageChangeCallback(adapter);
            viewPager.setAdapter(newViewPagerAdapter);
        }
//        viewPager.setUserInputEnabled(false);
    }

    public void registerOnPageChangeCallback(@NonNull ViewPager2.OnPageChangeCallback callback) {
        viewPager.registerOnPageChangeCallback(callback);
    }

    public void unregisterOnPageChangeCallback(@NonNull ViewPager2.OnPageChangeCallback callback) {
        viewPager.unregisterOnPageChangeCallback(callback);
    }

    @Override
    public void onPageCenter() {
        setCurrentItem(0);
    }

}
