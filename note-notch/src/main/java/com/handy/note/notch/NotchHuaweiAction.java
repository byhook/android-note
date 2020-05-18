package com.handy.note.notch;

import android.content.Context;

import java.lang.reflect.Method;

/**
 * @anchor: handy
 * @date: 2020-05-18
 * @description:
 */
public class NotchHuaweiAction extends NotchDefaultAction {

    public NotchHuaweiAction(Context context) {
        super(context);
    }

    @Override
    public boolean isSupportNotch() {
        try {
            ClassLoader cl = context.getClassLoader();
            Class<?> HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            return (Boolean) get.invoke(HwNotchSizeUtil);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getNotchWidth() {
        return getNotchSize()[0];
    }

    @Override
    public int getNotchHeight() {
        return getNotchSize()[1];
    }

    private int[] getNotchSize() {
        int[] result = new int[]{0, 0};
        try {
            ClassLoader cl = context.getClassLoader();
            Class<?> HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("getNotchSize");
            result = (int[]) get.invoke(HwNotchSizeUtil);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
