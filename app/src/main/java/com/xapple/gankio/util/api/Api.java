package com.xapple.gankio.util.api;

import com.xapple.gankio.data.model.GankIO;
import com.xapple.gankio.data.model.Result;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 项目名称：XAppleGankIO
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：2016/8/26 13:41
 * 修改人：wengyiming
 * 修改时间：2016/8/26 13:41
 * 修改备注：
 */
public interface Api {

    /**
     * http://gank.io/api/data/Android/10/1
     * http://gank.io/api/data/福利/10/1
     * http://gank.io/api/data/iOS/20/2
     * http://gank.io/api/data/all/20/2
     */
    @GET(Config.QUERY_LIST)
    Observable<Result<List<GankIO>>> queryList(@Path("type") String type,
                                               @Path("count") int count,
                                               @Path("page") int page);


    /**
     * https://android-libs.wilddogio.com/libs/list.json
     https://android-libs.wilddogio.com/libs/animation.json
     https://android-libs.wilddogio.com/libs/icon.json
     https://android-libs.wilddogio.com/libs/image.json
     https://android-libs.wilddogio.com/libs/chart.json
     https://android-libs.wilddogio.com/libs/project.json
     https://android-libs.wilddogio.com/libs/layout.json
     https://android-libs.wilddogio.com/libs/framework.json
     https://android-libs.wilddogio.com/libs/dialog.json
     https://android-libs.wilddogio.com/libs/button.json
     https://android-libs.wilddogio.com/libs/label.json
     https://android-libs.wilddogio.com/libs/effect.json
     https://android-libs.wilddogio.com/libs/network.json
     https://android-libs.wilddogio.com/libs/custom.json
     https://android-libs.wilddogio.com/libs/utils.json
     https://android-libs.wilddogio.com/libs/progressbar.json
     */

    @GET(Config.QUERY_LIST)
    Observable<Result<List<GankIO>>> queryXAppleList(@Path("type") String type);
}
