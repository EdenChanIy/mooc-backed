package com.sysu.mooc_backed.entity;

import java.util.Date;

/**
 * @Author: EdenChanIy
 * @Date: 2019/3/21 0:40
 * 用户收藏课程信息
 */
public class Collections {
    private int id;
    private int userId;
    private int courseId;
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
