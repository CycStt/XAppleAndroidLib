package com.xapple.gankio.home;

import com.xapple.gankio.BasePresenter;
import com.xapple.gankio.BaseView;
import com.xapple.gankio.data.model.GankIO;

import java.util.List;

/**
 * 项目名称：XAppleGankIO
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：2016/8/29 10:16
 * 修改人：wengyiming
 * 修改时间：2016/8/29 10:16
 * 修改备注：
 */
public class HomeContract {
    interface View extends BaseView<Presenter> {
        void showList(List<GankIO> all);
    }

    interface Presenter extends BasePresenter {
        void getGankList(String type,int count,int page);
    }

}
