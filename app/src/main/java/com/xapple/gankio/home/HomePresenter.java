package com.xapple.gankio.home;

import com.xapple.gankio.data.GanksRepository;
import com.xapple.gankio.data.model.GankIO;
import com.xapple.gankio.data.model.Result;
import com.xapple.gankio.util.schedulers.BaseSchedulerProvider;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 项目名称：XAppleGankIO
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：2016/8/29 10:20
 * 修改人：wengyiming
 * 修改时间：2016/8/29 10:20
 * 修改备注：
 */
public class HomePresenter implements HomeContract.Presenter {
    @NonNull
    private final GanksRepository mTasksRepository;

    @NonNull
    private final HomeContract.View mTaskDetailView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private CompositeSubscription mSubscriptions;

    public HomePresenter(
            @NonNull GanksRepository tasksRepository,
            @NonNull HomeContract.View taskDetailView,
            @NonNull BaseSchedulerProvider schedulerProvider) {
        mTasksRepository = tasksRepository;
        mTaskDetailView = taskDetailView;
        mSchedulerProvider = schedulerProvider;

        mSubscriptions = new CompositeSubscription();
        mTaskDetailView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        getGankList(null, 0, 0);
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }


    @Override
    public void getGankList(String type, int count, int page) {
        if (null == type || type.isEmpty()) {
            mTaskDetailView.showError("网络好像有问题");
            return;
        }
        if (count == 0 || page == 0) {
            return;
        }
        if(page>1){
            mTaskDetailView.loadMore();
        }else {
            mTaskDetailView.showRefresh();
        }
        Subscription subscription = mTasksRepository
                .getGanks(type, count, page)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new Observer<Result<List<GankIO>>>() {
                    @Override
                    public void onCompleted() {
                        mTaskDetailView.endRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mTaskDetailView.endRefresh();
                        mTaskDetailView.showError(e.toString());
                    }

                    @Override
                    public void onNext(Result<List<GankIO>> r) {
                        if (r == null || r.error || r.results == null || r.results.isEmpty()) {
                            mTaskDetailView.showError("出错了");
                            return;
                        }
                        mTaskDetailView.showList(r.results);
                        mTaskDetailView.endRefresh();
                    }
                });
        mSubscriptions.add(subscription);
    }
}
