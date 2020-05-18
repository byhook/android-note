package com.handy.note.notch;

import android.content.Context;

import java.lang.reflect.Method;

/**
 * @anchor: handy
 * @date: 2020-05-18
 * @description:
 */
public class NotchVivoAction extends NotchDefaultAction {

    public NotchVivoAction(Context context) {
        super(context);
    }

    /**
     * 0x00000020表示是否有凹槽
     * 0x00000008表示是否有圆角
     *
     * @return
     */
    @Override
    public boolean isSupportNotch() {
        try {
            Class<?> mClass = Class.forName("android.util.FtFeature");
            Method[] methods = mClass.getDeclaredMethods();
            Method method = null;
            for (Method temp : methods) {
                if (temp.getName().equalsIgnoreCase("isFeatureSupport")) {
                    method = temp;
                    break;
                }
            }
            return (Boolean) method.invoke(null, 0x00000020);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
