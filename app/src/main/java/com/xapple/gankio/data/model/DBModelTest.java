package com.xapple.gankio.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 项目名称：XAppleGankIO
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：2016/8/24 18:22
 * 修改人：wengyiming
 * 修改时间：2016/8/24 18:22
 * 修改备注：
 */
@Entity
public class DBModelTest {

    @Id
    String name;

    @Generated(hash = 487635367)
    public DBModelTest(String name) {
        this.name = name;
    }

    @Generated(hash = 1387467133)
    public DBModelTest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
