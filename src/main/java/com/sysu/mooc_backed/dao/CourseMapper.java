package com.sysu.mooc_backed.dao;

import com.sysu.mooc_backed.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper {

    //获取一级分类id列表
    @Select(
            "SELECT category1 FROM course GROUP BY category1"
    )
    @Result(property = "category1", column = "category1")
    List<Integer> findCategory1List();

    //根据一级分类id查询对应每个二级分类id的第一个课程
    @Select(
            "SELECT id, name, subtitle, img, learning_count, rating FROM (" +
                    " SELECT * FROM (" +
                        " SELECT * FROM (" +
                            " SELECT * FROM course WHERE category1 = #{category1}) AS base2)" +
                                    " AS base1 GROUP BY category2) AS base" +
                                        " GROUP BY category2")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "subtitle", column = "subtitle"),
            @Result(property = "img", column = "img"),
            @Result(property = "learningCount", column = "learning_count"),
            @Result(property = "rating", column = "rating")
    })
    List<Course> findListByCategory1(int category1);

    //根据二级分类获取课程
    @Select(
            "SELECT id, name, subtitle, img, learning_count, rating FROM course WHERE category2 = #{0} " +
                    "LIMIT #{1},#{2}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "subtitle", column = "subtitle"),
            @Result(property = "img", column = "img"),
            @Result(property = "learningCount", column = "learning_count"),
            @Result(property = "rating", column = "rating")
    })
    List<Course> findListByCategory2(int category2, int offset, int limit);

    //Count根据二级分类获取课程
    @Select("SELECT COUNT(*) FROM course WHERE category2 = #{category2}")
    int countFindListByCategory2(int category2);

    //根据兴趣列表获取最多5个推荐课程
    @Select("<script> SELECT * FROM course WHERE category2 IN " +
                " <foreach item='item' index='index' collection='type' open=" +
                    "'(' separator=',' close=')'> #{item} </foreach> Limit 5 </script>"
    )
    List<Course> findRecommendListByInterests(@Param("type") List<Integer> interests);

    //根据课程id获取课程信息
    @Select("SELECT * FROM course WHERE id = #{id}")
    Course findCourseById(int id);

    //根据课程id和用户id获取用户课程关系信息
    @Select("SELECT * FROM user_course_rel WHERE user_id=#{0} AND course_id=#{1}")
    @Results({
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "courseId", column = "course_id")
    })
    UserAndCourse findRelByUidAndCid(int userId, int courseId);

    //根据课程id和用户id获取近三个月里的课程用户关系信息
    @Select("SELECT * FROM user_course_rel WHERE user_id=#{0} AND DATE_SUB(CURDATE(), INTERVAL 3 month) <= date(update_time)")
    @Results({
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "courseId", column = "course_id")
    })
    List<UserAndCourse> findRelByUid3(int userId);

    //根据课程id获取章节信息
    @Select("SELECT * FROM chapter WHERE cid = #{cid}")
    @Result(property = "createTime", column = "create_time")
    List<Chapter> findChaptersByCid(int cid);

    //根据章节id获取课时信息
    @Select("SELECT * FROM period WHERE cid = #{cid}")
    @Result(property = "createTime", column = "create_time")
    List<Period> findPeriodsByCid(int cid);

    //根据课时id获取节点信息
    @Select("SELECT * FROM node WHERE pid = #{pid}")
    @Result(property = "createTime", column = "create_time")
    List<Node> findNodesByPid(int pid);

    //根据用户id和课时id获取用户课时关系信息
    @Select("SELECT * FROM user_period_rel WHERE user_id=#{0} AND period_id=#{1}")
    @Results({
            @Result(property = "leaveTime", column = "leave_time"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    UserAndPeriod findRelByUidAndPid(int userId, int periodId);
}
