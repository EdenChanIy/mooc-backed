package com.sysu.mooc_backed.entity;

import java.sql.Time;

/**
 * @Author: EdenChanIy
 * @Date: 2019/3/22 0:09
 * 节点信息
 */
public class Node {
    private int id;
    private String name;
    private int pid; //归属课时id
    private Time time; //节点对应时间戳

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
