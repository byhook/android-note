package com.handy.note.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.handy.note.base.BaseNoteActivity;
import com.handy.note.core.ImageCore;
import com.handy.note.glide.R;

/**
 * @author: handy
 * @date: 2020-05-25
 * @description:
 */
public class NoteGlideActivity extends BaseNoteActivity {

    public static void intentStart(@NonNull Context context) {
        Intent intent = new Intent(context, NoteGlideActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_glide_layer);
        setupViews();
    }

    private void setupViews() {
        final String IMAGE_URL = "https://logo.baidu.com/images/logo.png";
        ImageView imageView = findViewById(R.id.main_image_view);
        ImageCore.getInstance().loadImageSync(imageView, null, IMAGE_URL);
    }

}
