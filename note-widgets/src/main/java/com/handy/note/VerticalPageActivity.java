package com.handy.note;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.handy.note.base.BaseNoteActivity;
import com.handy.note.widgets.NewViewPagerAdapter;
import com.handy.note.widgets.R;
import com.handy.note.widgets.NewViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: handy
 * @date: 2020-07-13
 * @description:
 */
public class VerticalPageActivity extends BaseNoteActivity implements NewViewPagerAdapter.OnPageCenterListener {

    private static final String TAG = "VerticalPageActivity";
    //    private NewViewPager viewPager;
    private NewViewPager viewPager;

    public static void intentStart(@NonNull Context context) {
        Intent intent = new Intent(context, VerticalPageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_page_layer);
        viewPager = findViewById(R.id.main_view_pager);
        setupViews();
    }

    private void setupViews() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("" + i);
        }
        VerticalPageAdapter verticalPageAdapter = new VerticalPageAdapter(data);
        NewViewPagerAdapter adapter = new NewViewPagerAdapter(verticalPageAdapter,false);
        adapter.setPageCenterListener(this);
        verticalPageAdapter.setOnItemClickListener(new VerticalPageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int index) {
//                data.add(index + 1,"Insert: " + index);
//                adapter.notifyDataSetChanged();
//                viewPager.turnNextItem();
            }
        });
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2, false);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled " + position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    public void onPageCenter() {
        Log.d(TAG, "center");
        //viewPager.setCurrentItem(Integer.MAX_VALUE/2,false);
    }

}
