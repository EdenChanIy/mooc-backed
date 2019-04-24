package com.sysu.mooc_backed.dao;

import com.sysu.mooc_backed.entity.Discussion;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: EdenChanIy
 * @Date: 2019/4/18 16:58
 */

@Repository
public interface DiscussionMapper {

    //根据id获取讨论信息
    @Select("SELECT * FROM discussion WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "replyCount", column = "reply_count"),
            @Result(property = "likeCount", column = "like_count"),
            @Result(property = "view", column = "view"),
            @Result(property = "authorId", column = "author_id"),
            @Result(property = "pid", column = "pid"),
            @Result(property = "time", column = "time"),
            @Result(property = "createTime", column = "create_time")
    })
    Discussion findDiscussionById(int id);

    //根据课时id分页获取该课时下的讨论列表
    @Select("SELECT * FROM discussion WHERE pid = #{0} LIMIT #{1},#{2}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "replyCount", column = "reply_count"),
            @Result(property = "likeCount", column = "like_count"),
            @Result(property = "view", column = "view"),
            @Result(property = "authorId", column = "author_id"),
            @Result(property = "pid", column = "pid"),
            @Result(property = "time", column = "time"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    List<Discussion> findListByPId(int pid, int offset, int limit);

    //根据课时id获取该课时下的讨论数
    @Select("SELECT COUNT(*) FROM discussion WHERE pid = #{pid}")
    int findListCountByPid(int pid);

//    @Select("SELECT * FROM discussion, user_discussion_rel WHERE ")
}
