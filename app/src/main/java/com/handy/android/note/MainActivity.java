package com.handy.android.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.handy.note.VerticalPageActivity;
import com.handy.note.ui.ImmersiveNoteActivity;
import com.handy.note.ui.NotchNoteActivity;
import com.handy.note.ui.NoteGlideActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onNotchClick(View view) {
        NotchNoteActivity.intentStart(this);
    }

    public void onImmersiveClick(View view) {
        ImmersiveNoteActivity.intentStart(this);
    }

    public void onImageClick(View view) {
        NoteGlideActivity.intentStart(this);
    }

    public void onVerticalPagerClick(View view) {
        VerticalPageActivity.intentStart(this);
    }
}
