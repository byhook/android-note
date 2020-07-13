package com.handy.note;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.handy.note.base.BaseNoteActivity;

/**
 * @author: handy
 * @date: 2020-07-13
 * @description:
 */
public class VerticalPageActivity extends BaseNoteActivity {

    public static void intentStart(@NonNull Context context) {
        Intent intent = new Intent(context, VerticalPageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
