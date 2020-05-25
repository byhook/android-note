package com.handy.note.core;

import android.app.Application;

/**
 * @author: handy
 * @date: 2020-05-25
 * @description:
 */
public class AppCore {

    private static Application sApplication;

    public static void init(Application application) {
        sApplication = application;
    }

    public static Application getContext() {
        if (sApplication == null) {
            throw new IllegalStateException("you must init first");
        }
        return sApplication;
    }

}
