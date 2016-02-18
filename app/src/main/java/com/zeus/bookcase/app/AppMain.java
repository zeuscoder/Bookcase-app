package com.zeus.bookcase.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zeus_coder on 2016/2/1.
 */
public class AppMain extends Application {

    private List<Activity> activityList = new LinkedList<>(); //用于存放Activity的列表
    private static AppMain instance;  // 单例

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    /**
     * 获取单例
     * @return
     */
    public synchronized static AppMain getInstance() {
        if (null == instance) {
            instance = new AppMain();
        }
        return instance;
    }

    /**
     * 将Activity添加至列表
     * @param activity
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 将Activity移除列表
     * @param activity
     */
    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 退出应用
     */
    public void exit() {
        try {
            for (Activity activity : activityList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    /**
     * 低内存时自动回收垃圾
     */
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    /**
     * 初始化ImageLoader
     */
    public void initImageLoader(Context context) {
        //ImageLoader 图片组件初始化
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(3) // 线程池大小，默认为3
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .writeDebugLogs()  // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        //Skeleton.initialize(createComponent());
        initImageLoader(getApplicationContext());
    }
}
