package com.handy.note.notch;

import android.content.Context;
import android.os.Build;

/**
 * @anchor: handy
 * @date: 2020-05-18
 * @description:
 */
public class NotchLenovoAction extends NotchDefaultAction {

    public NotchLenovoAction(Context context) {
        super(context);
    }

    @Override
    public boolean isSupportNotch() {
        int resourceId = context.getResources().getIdentifier("config_screen_has_notch", "bool", "android");
        if (resourceId > 0) {
            return context.getResources().getBoolean(resourceId);
        }
        return false;
    }

}
