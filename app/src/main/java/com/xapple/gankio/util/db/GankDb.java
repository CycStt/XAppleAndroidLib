package com.xapple.gankio.util.db;

import com.xapple.gankio.data.model.GankIO;
import com.xapple.gankio.util.db.BaseDao;

import org.greenrobot.greendao.AbstractDao;

/**
 * 项目名称：XAppleGankIO
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：2016/8/26 17:51
 * 修改人：wengyiming
 * 修改时间：2016/8/26 17:51
 * 修改备注：
 */
public class GankDb extends BaseDao<GankIO, String> {
    public GankDb(AbstractDao dao) {
        super(dao);
    }

}
