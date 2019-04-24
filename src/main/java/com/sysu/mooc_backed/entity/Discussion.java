package com.sysu.mooc_backed.entity;

import java.sql.Date;
import java.sql.Time;

/**
 * @Author: EdenChanIy
 * @Date: 2019/4/18 16:52
 * 讨论
 */
public class Discussion {
    private int id;
    private String title;
    private int replyCount; //讨论回复数
    private int likeCount; //讨论获赞数
    private int view; //浏览数
    private int authorId; //讨论发起者id
    private int pid; //该讨论对应课时id
    private Time time; //讨论对应时间节点
    private Date createTime; //讨论创建时间
    private Date updateTime; //讨论最后更新时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
