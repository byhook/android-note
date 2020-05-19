package com.handy.note.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.handy.note.base.BaseNoteActivity;
import com.handy.note.immersive.ImmersiveCore;
import com.handy.note.immersive.R;

/**
 * @author: handy
 * @date: 2020-05-19
 * @description:
 */
public class ImmersiveNoteActivity extends BaseNoteActivity {

    public static void intentStart(@NonNull Context context){
        Intent intent = new Intent(context, ImmersiveNoteActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersiveCore.transparentStatusBar(this);
        setContentView(R.layout.activity_immersive_layer);
    }

}
