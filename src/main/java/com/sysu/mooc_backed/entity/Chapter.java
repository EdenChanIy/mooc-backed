package com.sysu.mooc_backed.entity;

import java.util.Date;

/**
 * @Author: EdenChanIy
 * @Date: 2019/3/22 0:03
 * 章节
 */
public class Chapter {
    private int id;
    private int no; //章节序号
    private String name;
    private String intro; //章节介绍
    private int cid; //归属哪一个课程
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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
