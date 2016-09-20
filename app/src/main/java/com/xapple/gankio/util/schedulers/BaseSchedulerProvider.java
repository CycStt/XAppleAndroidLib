package com.xapple.gankio.util.schedulers;

import android.support.annotation.NonNull;

import rx.Scheduler;

/**
 * 项目名称：XAppleGankIO
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：2016/8/24 18:02
 * 修改人：wengyiming
 * 修改时间：2016/8/24 18:02
 * 修改备注：
 */
public interface BaseSchedulerProvider {
    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
