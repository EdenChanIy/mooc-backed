package com.sysu.mooc_backed.dao;

import com.sysu.mooc_backed.entity.Course;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper {

    @Select(
            "SELECT * FROM course WHERE category2 = #{0} " +
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

    @Select("SELECT COUNT(*) FROM course WHERE category2 = #{category2}")
    int countFindListByCategory2(int category2);
}
