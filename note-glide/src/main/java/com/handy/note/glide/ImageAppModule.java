package com.handy.note.glide;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.handy.note.core.AppCore;

import java.io.File;
import java.io.InputStream;


/**
 * @author: handy
 * @date: 2020-05-25
 * @description:
 */
@GlideModule
public class ImageAppModule extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
        //降低图片质量
        builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_RGB_565));
        //设置磁盘缓存大小
        File cacheDir = new File(AppCore.getContext().getExternalFilesDir(null),"imageCache");
        builder.setDiskCache(new DiskLruCacheFactory(cacheDir.getAbsolutePath(), 100 * 1024 * 1024));
        builder.setLogLevel(Log.ERROR);
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide,
                                   @NonNull Registry registry) {
        
    }

}
