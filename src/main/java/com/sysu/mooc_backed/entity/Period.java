package com.sysu.mooc_backed.entity;

import java.sql.Date;
import java.sql.Time;

/**
 * @Author: EdenChanIy
 * @Date: 2019/3/22 0:07
 * 课时信息
 */
public class Period {
    private int id;
    private int no; //课时序号
    private String name;
    private String url; //视频链接
    private Time duration; //视频时长
    private int cid; //归属章节id
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
