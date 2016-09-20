package com.xapple.gankio.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * 项目名称：XAppleGankIO
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：2016/8/26 16:05
 * 修改人：wengyiming
 * 修改时间：2016/8/26 16:05
 * 修改备注：
 */
@Entity
public class GankIO implements Serializable{


    /**
     * _id : 57bd8f8a421aa91265f4a3d3
     * createdAt : 2016-08-24T20:14:02.837Z
     * desc : 图片遮罩转场动画效果
     * publishedAt : 2016-08-25T11:23:14.243Z
     * source : chrome
     * type : iOS
     * url : https://github.com/LeoMobileDeveloper/ImageMaskTransition
     * used : true
     * who : Joker
     */

    @Id
    private String _id;
    @NotNull
    private String createdAt;
    @NotNull
    private String desc;
    private String publishedAt;
    @NotNull
    private String source;
    @NotNull
    private String type;
    @NotNull
    private String url;

    private boolean used;
    @NotNull
    private String who;

    @Generated(hash = 2025470358)
    public GankIO(String _id, @NotNull String createdAt, @NotNull String desc,
            String publishedAt, @NotNull String source, @NotNull String type,
            @NotNull String url, boolean used, @NotNull String who) {
        this._id = _id;
        this.createdAt = createdAt;
        this.desc = desc;
        this.publishedAt = publishedAt;
        this.source = source;
        this.type = type;
        this.url = url;
        this.used = used;
        this.who = who;
    }

    @Generated(hash = 1666550087)
    public GankIO() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public boolean getUsed() {
        return this.used;
    }
}
