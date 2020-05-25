package com.handy.note.glide;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.handy.note.core.AppCore;
import com.handy.note.core.ImageCore;
import com.handy.note.image.ImageOptions;

/**
 * @author: handy
 * @date: 2020-05-25
 * @description:
 */
public class GlideImageImpl extends ImageCore {

    @Override
    public void loadImageSync(ImageView imageView, ImageOptions imageOptions, String url) {
        Glide.with(AppCore.getContext()).asDrawable().load(url).into(imageView);
    }

}
