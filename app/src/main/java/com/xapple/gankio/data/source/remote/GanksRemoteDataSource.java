package com.xapple.gankio.data.source.remote;

import com.xapple.gankio.data.DataSource;
import com.xapple.gankio.data.model.GankIO;
import com.xapple.gankio.data.model.Result;
import com.xapple.gankio.util.api.Api;
import com.xapple.gankio.util.api.Config;
import com.xapple.gankio.util.api.RetrofitClient;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：XAppleGankIO
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：2016/8/26 17:31
 * 修改人：wengyiming
 * 修改时间：2016/8/26 17:31
 * 修改备注：
 */
public class GanksRemoteDataSource implements DataSource {
    private static GanksRemoteDataSource INSTANCE;

    private static final int SERVICE_LATENCY_IN_MILLIS = 5000;

    private final static Map<String, GankIO> TASKS_SERVICE_DATA;

    static {
        TASKS_SERVICE_DATA = new LinkedHashMap<>(2);
    }

    public static GanksRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GanksRemoteDataSource();
        }
        return INSTANCE;
    }


    @Override
    public Observable<Result<List<GankIO>>> getGanks(String type,int count,int page) {
            Observable<Result<List<GankIO>>> observable = RetrofitClient.createApi(Api.class, Config.BASE_URL).queryList(type, count, page)
                  .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

}
