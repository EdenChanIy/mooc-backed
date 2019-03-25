package com.sysu.mooc_backed.entity;

import java.sql.Time;
import java.util.Date;

/**
 * @Author: EdenChanIy
 * @Date: 2019/3/25 23:20
 * 用户和课时的关系
 */
public class UserAndPeriod {
    private int id;
    private int userId;
    private int courseId;
    private Time leaveTime;
    private Date createTime;
    private Date updateTime;

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

    public Time getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Time leaveTime) {
        this.leaveTime = leaveTime;
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
