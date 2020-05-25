package com.handy.note.core;

import android.widget.ImageView;

import com.handy.note.glide.GlideImageImpl;
import com.handy.note.image.ImageOptions;

/**
 * @author: handy
 * @date: 2020-05-25
 * @description:
 */
public abstract class ImageCore {

    private static ImageCore sInstance;

    public static ImageCore getInstance() {
        if (sInstance == null) {
            synchronized (ImageCore.class) {
                if (sInstance == null) {
                    sInstance = new GlideImageImpl();
                }
            }
        }
        return sInstance;
    }

    public abstract void loadImageSync(ImageView imageView, ImageOptions imageOptions, String url);

}
