package com.handy.note.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.handy.note.base.BaseNoteActivity;

/**
 * @anchor: handy
 * @date: 2020-05-24
 * @description:
 */
public class NoteLifecycleActivity extends BaseNoteActivity {

    public static void intentStart(@NonNull Context context){
        Intent intent = new Intent(context, NoteLifecycleActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
