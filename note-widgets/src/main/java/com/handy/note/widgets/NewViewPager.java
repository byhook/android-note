package com.handy.note.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

/**
 * @author: handy
 * @date: 2020-07-13
 * @description:
 */
public class NewViewPager extends FrameLayout {

    private static final String TAG = "NewViewPager";

    public static final int MIDDLE_POINT = Integer.MAX_VALUE / 2;

    private ViewPager2 viewPager;

    public NewViewPager(@NonNull Context context) {
        this(context, null);
    }

    public NewViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        viewPager = new ViewPager2(context);
        viewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        addView(viewPager, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        viewPager.registerOnPageChangeCallback(onPageChangeCallback);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        viewPager.unregisterOnPageChangeCallback(onPageChangeCallback);
    }

    public void setCurrentItem(int item) {
        viewPager.setCurrentItem(item);
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        viewPager.setCurrentItem(item, smoothScroll);
    }

    public void turnNextItem() {
        int currentItem =  viewPager.getCurrentItem();
        viewPager.setCurrentItem(currentItem + 1);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        viewPager.setAdapter(adapter);
    }

    public void registerOnPageChangeCallback(@NonNull ViewPager2.OnPageChangeCallback callback) {
        viewPager.registerOnPageChangeCallback(callback);
    }

    public void unregisterOnPageChangeCallback(@NonNull ViewPager2.OnPageChangeCallback callback) {
        viewPager.unregisterOnPageChangeCallback(callback);
    }

    private ViewPager2.OnPageChangeCallback onPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            int index = position - MIDDLE_POINT;
            Log.d(TAG,"onPageSelected " + index);
        }
    };

    public interface OnViewPageLoadListener {

        void onPageLoad();

    }

}
