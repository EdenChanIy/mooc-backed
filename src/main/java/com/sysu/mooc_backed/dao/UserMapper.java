package com.sysu.mooc_backed.dao;

import com.sysu.mooc_backed.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    @Select("SELECT * FROM user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "job", column = "job"),
            @Result(property = "createTime", column = "create_time")
    })
    List<User> findList();

    @Select("SELECT * FROM user WHERE name=#{name}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "password", column = "password")
    })
    User findUserByName(String name);

    @Insert("INSERT INTO user(name, password) VALUES(#{name}, #{password})")
    void add(User user);

    @Update("UPDATE user SET name=#{name}, password=#{password} WHERE id=#{id}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id=#{id}")
    void delete(int id);
}
