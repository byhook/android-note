package com.handy.note.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author: handy
 * @date: 2020-05-14
 * @description:
 */
public abstract class BaseNoteActivity extends AppCompatActivity {

    private ViewModelProvider mViewModelProvider;

    protected <T extends ViewModel> T getViewModel(@NonNull Class<T> modelClass) {
        if (mViewModelProvider == null) {
            mViewModelProvider = new ViewModelProvider(this);
        }
        return mViewModelProvider.get(modelClass);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
