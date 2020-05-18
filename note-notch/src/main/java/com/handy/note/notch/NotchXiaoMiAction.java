package com.handy.note.notch;

import android.content.Context;

import java.lang.reflect.Method;

/**
 * @anchor: handy
 * @date: 2020-05-18
 * @description:
 */
public class NotchXiaoMiAction extends NotchDefaultAction {

    public NotchXiaoMiAction(Context context) {
        super(context);
    }

    @Override
    public boolean isSupportNotch() {
        try {
            Class<?> mClassType = Class.forName("android.os.SystemProperties");
            Method mGetIntMethod = mClassType.getDeclaredMethod("getInt",
                    String.class, int.class);
            Integer v = (Integer) mGetIntMethod.invoke(mClassType,"ro.miui.notch", 0);
            return v.intValue() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
