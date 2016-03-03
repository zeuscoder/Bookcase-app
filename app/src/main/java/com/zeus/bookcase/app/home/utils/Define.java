package com.zeus.bookcase.app.home.utils;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * 定义的一些常量
 * Created by zeus_coder on 2016/1/17.
 */
public class Define {

    /**
     * imageloader图片处理属性(正常)
     */
    public final static DisplayImageOptions NORMAL_OPTIONS = new DisplayImageOptions.Builder()
            .cacheInMemory(true).bitmapConfig(Bitmap.Config.RGB_565)
            .cacheOnDisc(true).considerExifParams(true)
            .displayer(new FadeInBitmapDisplayer(300)).build();

    /**
     * imageloader图片处理属性(圆角)
     */
    public final static DisplayImageOptions ROOUND_OPTIONS = new DisplayImageOptions.Builder()
            .cacheInMemory(true).bitmapConfig(Bitmap.Config.RGB_565)
            .cacheOnDisc(true).considerExifParams(true)
            .displayer(new RoundedBitmapDisplayer(7)).build();// 图片圆角7度
}
