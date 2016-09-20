package com.xapple.gankio.data;

import com.xapple.gankio.data.model.GankIO;
import com.xapple.gankio.data.model.Result;

import java.util.List;

import rx.Observable;

/**
 * 项目名称：XAppleGankIO
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：2016/8/26 17:29
 * 修改人：wengyiming
 * 修改时间：2016/8/26 17:29
 * 修改备注：
 */
public interface DataSource {

    Observable<Result<List<GankIO>>> getGanks(String type, int count, int page);
}
