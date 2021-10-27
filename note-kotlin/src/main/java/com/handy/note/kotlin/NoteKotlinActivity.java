package com.handy.note.kotlin;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.handy.note.base.BaseNoteActivity;

/**
 * date: 2021-10-27
 * description:
 */
public class NoteKotlinActivity extends BaseNoteActivity {

    public static void intentStart(@NonNull Context context) {
        Intent intent = new Intent(context, NoteKotlinActivity.class);
        context.startActivity(intent);
    }

}
