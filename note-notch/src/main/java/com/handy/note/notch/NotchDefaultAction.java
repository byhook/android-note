package com.handy.note.notch;

import android.content.Context;
import android.graphics.Point;

/**
 * @anchor: handy
 * @date: 2020-05-18
 * @description:
 */
public class NotchDefaultAction implements NotchAction {

    protected Context context;

    public NotchDefaultAction(Context context){
        this.context = context;
    }

    @Override
    public boolean isSupportNotch() {
        return false;
    }

    @Override
    public int getNotchWidth() {
        return 0;
    }

    @Override
    public int getNotchHeight() {
        return 0;
    }

    @Override
    public Point getPhysicalResolution() {
        return null;
    }

}
