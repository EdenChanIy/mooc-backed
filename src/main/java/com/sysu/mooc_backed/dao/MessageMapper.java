package com.sysu.mooc_backed.dao;

import com.sysu.mooc_backed.entity.Message;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: EdenChanIy
 * @Date: 2019/6/18 15:09
 */
@Repository
public interface MessageMapper {

    @Select("Select * from message")
    List<Message> findList();


}
