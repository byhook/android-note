package com.handy.note.notch;

import android.content.Context;
import android.content.pm.PackageManager;

import java.lang.reflect.Method;

/**
 * @anchor: handy
 * @date: 2020-05-18
 * @description:
 */
public class NotchOppoAction extends NotchDefaultAction {

    public NotchOppoAction(Context context) {
        super(context);
    }

    @Override
    public boolean isSupportNotch() {
        PackageManager packageManager = context.getPackageManager();
        return packageManager != null && packageManager.hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

}
