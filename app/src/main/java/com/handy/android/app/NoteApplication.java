package com.handy.android.app;

import com.handy.note.base.BaseApplication;
import com.handy.note.core.AppCore;

/**
 * @author: handy
 * @date: 2020-05-25
 * @description:
 */
public class NoteApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        AppCore.init(this);
    }

}
