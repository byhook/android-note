package com.handy.note.base;

import android.app.Application;

import com.handy.note.core.AppCore;

/**
 * @author: handy
 * @date: 2020-05-29
 * @description:
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppCore.init(this);
    }
}
