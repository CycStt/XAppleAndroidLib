package com.xapple.gankio.util.api;

/**
 * 项目名称：XAppleGankIO
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：2016/8/26 14:03
 * 修改人：wengyiming
 * 修改时间：2016/8/26 14:03
 * 修改备注：
 */
public interface Config {

    String BASE_URL = "http://gank.io/api/";

    String BASE_XAPPLE_URL = "https://android-libs.wilddogio.com/";

//    分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
//
//    数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
//    请求个数： 数字，大于0
//    第几页：数字，大于0
//    例：
//    http://gank.io/api/data/Android/10/1
//    http://gank.io/api/data/福利/10/1
//    http://gank.io/api/data/iOS/20/2
//    http://gank.io/api/data/all/20/2

    String QUERY_LIST =  "data/{type}/{count}/{page}";

    String GET_LIST = "libs/{type}";


}
