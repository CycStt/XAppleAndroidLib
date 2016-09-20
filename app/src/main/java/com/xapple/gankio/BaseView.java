package com.xapple.gankio;

/**
 * 项目名称：XAppleGankIO
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：2016/8/24 18:01
 * 修改人：wengyiming
 * 修改时间：2016/8/24 18:01
 * 修改备注：
 */
public interface BaseView<T> {
    void setPresenter(T presenter);
    void showRefresh();
    void endRefresh();
    void loadMore();
    void showError(String error);
}
