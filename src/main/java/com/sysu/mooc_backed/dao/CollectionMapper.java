package com.sysu.mooc_backed.dao;

import com.sysu.mooc_backed.entity.Collections;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author: EdenChanIy
 * @Date: 2019/3/21 0:43
 */
@Repository
public interface CollectionMapper {
    //根据用户id和课程id获取收藏记录
    @Select(
            "SELECT * FROM collection WHERE user_id = #{0} AND course_id = #{1}"
    )
    Collections findRecordByUserIdAndCourseId(int userId, int courseId);

    //根据用户id和课程id增加收藏记录
    @Insert(
            "INSERT INTO collection(user_id, course_id) VALUES(#{0}, #{1})"
    )
    void addCollectionByUserIdAndCourseId(int userId, int courseId);

    //根据用户id和课程id删除收藏记录
    @Delete(
            "DELETE FROM collection WHERE user_id=#{0} AND course_id=#{1}"
    )
    void deleteCollectionByUserIdAndCourseId(int userId, int courseId);
}
