package com.handy.note;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.handy.note.base.BaseNoteActivity;
import com.handy.note.widgets.R;
import com.handy.note.widgets.NewViewPager;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author: handy
 * @date: 2020-07-13
 * @description:
 */
public class VerticalPageActivity extends BaseNoteActivity{

    private static final String TAG = "VerticalPageActivity";

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
        List<TestData> data = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            data.add(new TestData(i));
        }
        Random random = new Random(System.currentTimeMillis());
        VerticalPageAdapter adapter = new VerticalPageAdapter(data);
        adapter.setOnItemClickListener(new VerticalPageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int index) {
                //viewPager.setCurrentItem(8);
                if(adapter.insertNextData(new TestData(8 + random.nextInt(7)))){
                    viewPager.turnNextItem();
                }
            }
        });
        viewPager.setAdapter(adapter);
    }

}
