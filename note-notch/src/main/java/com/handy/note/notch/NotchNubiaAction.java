package com.handy.note.notch;

import android.content.Context;
import android.os.Build;

/**
 * @anchor: handy
 * @date: 2020-05-18
 * @description:
 */
public class NotchNubiaAction extends NotchDefaultAction {

    public NotchNubiaAction(Context context) {
        super(context);
    }

    @Override
    public boolean isSupportNotch() {
        String model = Build.MODEL;
        if (model.contains("NX606J")) {
            return true;
        }
        return false;
    }

}
