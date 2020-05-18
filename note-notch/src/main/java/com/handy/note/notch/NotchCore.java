package com.handy.note.notch;

import android.content.Context;
import android.os.Build;

/**
 * @anchor: handy
 * @date: 2020-05-18
 * @description:
 */
public class NotchCore {

    private static NotchCore sInstance;

    private NotchAction notchAction;

    public static NotchCore getInstance() {
        if (sInstance == null) {
            synchronized (NotchCore.class) {
                if (sInstance == null) {
                    sInstance = new NotchCore();
                }
            }
        }
        return sInstance;
    }

    public void initDevice(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String manufacturer = Build.MANUFACTURER.toLowerCase();
            if (manufacturer.contains(NotchDeviceType.TYPE_VIVO)) {
                notchAction = new NotchVivoAction(context);
            } else if (manufacturer.contains(NotchDeviceType.TYPE_OPPO)) {
                notchAction = new NotchOppoAction(context);
            } else if (manufacturer.contains(NotchDeviceType.TYPE_ONEPLUS)) {
                notchAction = new NotchOnePlusAction(context);
            } else if (manufacturer.contains(NotchDeviceType.TYPE_HUAWEI)) {
                notchAction = new NotchHuaweiAction(context);
            } else if (manufacturer.contains(NotchDeviceType.TYPE_XIAOMI)) {
                notchAction = new NotchXiaoMiAction(context);
            } else if (manufacturer.contains(NotchDeviceType.TYPE_LENOVO)
                    || manufacturer.contains(NotchDeviceType.TYPE_MOTO)) {
                notchAction = new NotchLenovoAction(context);
            } else if (manufacturer.contains(NotchDeviceType.TYPE_MEIZU)) {
                notchAction = new NotchMeizuAction(context);
            } else if (manufacturer.contains(NotchDeviceType.TYPE_NUBIA)) {
                notchAction = new NotchNubiaAction(context);
            } else if (manufacturer.contains(NotchDeviceType.TYPE_SAMSUNG)) {
                notchAction = new NotchSamsungAction(context);
            }
        }
        if(notchAction == null){
            notchAction = new NotchDefaultAction(context);
        }
    }

}
