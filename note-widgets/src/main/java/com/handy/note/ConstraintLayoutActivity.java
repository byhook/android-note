package com.handy.note;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Placeholder;

import com.handy.note.base.BaseNoteActivity;
import com.handy.note.widgets.NewViewPager;
import com.handy.note.widgets.R;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author: handy
 * @date: 2020-07-13
 * @description:
 */
public class ConstraintLayoutActivity extends BaseNoteActivity {

    private static final String TAG = "ConstraintLayoutActivity";

    private ViewGroup mainRootLayer;

    public static void intentStart(@NonNull Context context) {
        Intent intent = new Intent(context, ConstraintLayoutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout_layer);
        mainRootLayer = findViewById(R.id.main_root_layer);
    }

    @SuppressLint("LongLogTag")
    public void onAddPlaceHolderClick(View view) {
        /*Button helloButton = mainRootLayer.findViewById(5567);
        if (helloButton == null) {
            helloButton = new Button(this);
            helloButton.setId(5567);
            helloButton.setText("hello");
            mainRootLayer.addView(helloButton);
        } else {
            Log.d(TAG,"onRemovePlaceHolderClick " + helloButton);
            mainRootLayer.removeView(helloButton);
        }*/
    }

    @SuppressLint("LongLogTag")
    public void onRemovePlaceHolderClick(View view) {
        /*Button helloButton = mainRootLayer.findViewById(R.id.main_place_content);
        Log.d(TAG,"onRemovePlaceHolderClick " + helloButton);
        mainRootLayer.removeView(helloButton);*/

        /*Placeholder placeholder = mainRootLayer.findViewById(R.id.main_content_holder);
        mainRootLayer.removeView(placeholder);*/
    }

}
