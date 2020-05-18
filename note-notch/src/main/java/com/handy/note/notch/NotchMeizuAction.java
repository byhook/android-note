package com.handy.note.notch;

import android.content.Context;
import android.os.Build;

import java.lang.reflect.Field;

/**
 * @anchor: handy
 * @date: 2020-05-18
 * @description:
 */
public class NotchMeizuAction extends NotchDefaultAction {

    public NotchMeizuAction(Context context) {
        super(context);
    }

    @Override
    public boolean isSupportNotch() {
        boolean fringeDevice = false;
        try {
            Class<?> clazz = Class.forName("flyme.config.FlymeFeature");
            Field field = clazz.getDeclaredField("IS_FRINGE_DEVICE");
            fringeDevice = (Boolean) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fringeDevice;
    }

}
