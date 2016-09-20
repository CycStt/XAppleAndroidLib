package com.xapple.gankio;

import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.squareup.leakcanary.LeakCanary;
import com.xapple.gankio.util.db.DbCore;

import android.app.Application;
import android.util.Log;

import timber.log.Timber;

/**
 * 项目名称：XAppleGankIO
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：2016/8/24 17:42
 * 修改人：wengyiming
 * 修改时间：2016/8/24 17:42
 * 修改备注：https://github.com/googlesamples/android-architecture/tree/dev-todo-mvp-rxjava/
 */
public class APP extends Application {
    private static APP instance;


    public static APP getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;//app全局上下文
        DbCore.init(this);//初始化数据库
        LeakCanary.install(this);
        BlockCanary.install(this, new BlockCanaryContext()).start();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    /** A tree which logs important information for crash reporting. */
    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }

}
