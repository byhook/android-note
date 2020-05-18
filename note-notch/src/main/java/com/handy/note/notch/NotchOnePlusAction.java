package com.handy.note.notch;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * @anchor: handy
 * @date: 2020-05-18
 * @description:
 */
public class NotchOnePlusAction extends NotchDefaultAction {

    public NotchOnePlusAction(Context context) {
        super(context);
    }

    @Override
    public boolean isSupportNotch() {
        String model = Build.MODEL;
        if (model.contains("ONEPLUS A6000") || model.contains("ONEPLUS A6010")
                || model.contains("GM1900")) {
            return true;
        }
        return false;
    }

}
