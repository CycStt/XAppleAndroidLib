package com.xapple.gankio.data;

import com.xapple.gankio.data.model.GankIO;
import com.xapple.gankio.data.model.Result;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import rx.Observable;

/**
 * 项目名称：XAppleGankIO
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：2016/8/26 17:43
 * 修改人：wengyiming
 * 修改时间：2016/8/26 17:43
 * 修改备注：
 */
public class GanksRepository implements DataSource {
    @Nullable
    private static GanksRepository INSTANCE = null;

    @NonNull
    private final DataSource mTasksRemoteDataSource;

    @NonNull
    private final DataSource mTasksLocalDataSource;

    private Observable<Result<List<GankIO>>> mObservable;

    // Prevent direct instantiation.
    private GanksRepository(@NonNull DataSource tasksRemoteDataSource,
                            @NonNull DataSource tasksLocalDataSource) {
        mTasksRemoteDataSource = tasksRemoteDataSource;
        mTasksLocalDataSource = tasksLocalDataSource;
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param tasksRemoteDataSource the backend data source
     * @param tasksLocalDataSource  the device storage data source
     * @return the {@link GanksRepository} instance
     */
    public static GanksRepository getInstance(@NonNull DataSource tasksRemoteDataSource,
                                              @NonNull DataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new GanksRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(DataSource, DataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public Observable<Result<List<GankIO>>> getGanks(String type, int count, int page) {
        return getAndSaveRemoteGanks(type, count, page);
    }

    private Observable<Result<List<GankIO>>> getAndSaveRemoteGanks(String type, int count, int page) {
//        if (mObservable == null) {
        mObservable = mTasksRemoteDataSource
                .getGanks(type, count, page);
//        }
        return mObservable;
    }

}
