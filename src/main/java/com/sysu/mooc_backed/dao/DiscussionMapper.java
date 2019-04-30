package com.sysu.mooc_backed.dao;

import com.sysu.mooc_backed.entity.Discussion;
import com.sysu.mooc_backed.entity.DiscussionItem;
import org.apache.ibatis.annotations.Param;
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
            @Result(property = "cid", column = "cid"),
            @Result(property = "pid", column = "pid"),
            @Result(property = "time", column = "time"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    Discussion findDiscussionById(int id);

    //根据课时id分页获取该课时下的讨论列表
    @Select("SELECT * FROM discussion WHERE pid = #{0} ORDER BY update_time DESC LIMIT #{1},#{2}")
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

    //根据用户id和课程id获取用户创建的讨论列表
    @Select("<script> SELECT * FROM discussion WHERE author_id = #{uid} <if test='cid!=0'> AND cid = #{cid} </if></script>")
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
    List<Discussion> findListByUidAndCidWithA(@Param("uid") int uid, @Param("cid") int cid);

    //根据用户id和课程id获取用户关注的讨论列表
    @Select("<script> SELECT * FROM discussion " +
            "WHERE id IN (SELECT discussion_id FROM user_discussion_rel WHERE user_id = #{uid} AND is_followed = 1) " +
            "<if test='cid!=0'> AND cid = #{cid} </if></script>")
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
    List<Discussion> findListByUidAndCidWithF(@Param("uid") int uid, @Param("cid") int cid);

    //根据讨论id获取讨论主题内容和回复列表
    @Select("SELECT * FROM discussion_item WHERE discussion_id = #{id} ORDER BY create_time")
    @Results({
            @Result(property = "discussionId", column = "discussion_id"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "createTime", column = "create_time")
    })
    List<DiscussionItem> findItemListById(int id);

    //根据parentId获取被回复讨论内容
    @Select("SELECT * FROM user_discussion_rel WHERE id = #{pid}")
    @Results({
            @Result(property = "discussionId", column = "discussion_id"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "createTime", column = "create_time")
    })
    DiscussionItem findItemByPid(int pid);
}
