package com.xapple.gankio.data.source.local;

import com.xapple.gankio.data.DataSource;
import com.xapple.gankio.data.model.GankIO;
import com.xapple.gankio.data.model.Result;
import com.xapple.gankio.util.schedulers.BaseSchedulerProvider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import rx.Observable;

/**
 * 项目名称：XAppleGankIO
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：2016/8/26 17:45
 * 修改人：wengyiming
 * 修改时间：2016/8/26 17:45
 * 修改备注：
 */
public class GankLocalDataSource implements DataSource {
    @Nullable
    private static GankLocalDataSource INSTANCE;


    public static GankLocalDataSource getInstance(
            @NonNull Context context,
            @NonNull BaseSchedulerProvider schedulerProvider) {
        if (INSTANCE == null) {
            INSTANCE = new GankLocalDataSource(context, schedulerProvider);
        }
        return INSTANCE;
    }


    private GankLocalDataSource(@NonNull Context context,
                                 @NonNull BaseSchedulerProvider schedulerProvider) {

    }


    @Override
    public Observable<Result<List<GankIO>>> getGanks(String type, int count, int page) {
        return null;
    }
}
