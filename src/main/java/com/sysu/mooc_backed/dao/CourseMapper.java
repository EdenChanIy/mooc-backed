package com.sysu.mooc_backed.dao;

import com.sysu.mooc_backed.entity.Course;
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


}
