package com.handy.note.notch;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;

/**
 * @anchor: handy
 * @date: 2020-05-18
 * @description:
 */
public class NotchSamsungAction extends NotchDefaultAction {

    public NotchSamsungAction(Context context) {
        super(context);
    }

    @Override
    public boolean isSupportNotch() {
        try {
            final Resources res = context.getResources();
            final int resId = res.getIdentifier("config_mainBuiltInDisplayCutout", "string", "android");
            final String spec = resId > 0 ? res.getString(resId) : null;
            return spec != null && !TextUtils.isEmpty(spec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
