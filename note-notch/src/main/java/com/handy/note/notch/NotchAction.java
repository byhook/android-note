package com.handy.note.notch;

import android.graphics.Point;

/**
 * @anchor: handy
 * @date: 2020-05-18
 * @description:
 */
public interface NotchAction {

    /**
     * 是否支持刘海
     *
     * @return
     */
    boolean isSupportNotch();

    /**
     * 刘海宽度
     *
     * @return
     */
    int getNotchWidth();

    /**
     * 刘海高度
     *
     * @return
     */
    int getNotchHeight();

    /**
     * 获取设备物理分辨率
     *
     * @return
     */
    Point getPhysicalResolution();

}
