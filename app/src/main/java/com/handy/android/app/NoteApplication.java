package com.handy.android.app;

import android.app.Application;

import com.handy.note.core.AppCore;

/**
 * @author: handy
 * @date: 2020-05-25
 * @description:
 */
public class NoteApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppCore.init(this);
    }

}
