package com.xapple.gankio.data.Injection;

import com.xapple.gankio.data.GanksRepository;
import com.xapple.gankio.data.source.local.GankLocalDataSource;
import com.xapple.gankio.data.source.remote.GanksRemoteDataSource;
import com.xapple.gankio.util.schedulers.BaseSchedulerProvider;
import com.xapple.gankio.util.schedulers.SchedulerProvider;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * 项目名称：XAppleGankIO
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：2016/8/26 17:43
 * 修改人：wengyiming
 * 修改时间：2016/8/26 17:43
 * 修改备注：
 */
public class Injection {
    public static GanksRepository provideTasksRepository(@NonNull Context context) {
        return GanksRepository.getInstance(GanksRemoteDataSource.getInstance(),
                GankLocalDataSource.getInstance(context, provideSchedulerProvider()));
    }

    public static BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }

}
