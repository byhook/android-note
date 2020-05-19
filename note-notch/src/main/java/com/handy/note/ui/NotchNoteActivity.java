package com.handy.note.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.handy.note.base.BaseNoteActivity;
import com.handy.note.notch.R;

/**
 * @author: handy
 * @date: 2020-05-19
 * @description:
 */
public class NotchNoteActivity extends BaseNoteActivity {

    public static void intentStart(@NonNull Context context){
        Intent intent = new Intent(context, NotchNoteActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notch_layer);
        openDisplayCutoutMode();
        setupWindow();
    }

    /**
     * 处理Android9.0挖孔屏问题
     */
    public void openDisplayCutoutMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Window window = getWindow();
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            window.setAttributes(lp);
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void setupWindow(){
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


}
