package com.sysu.mooc_backed.service.impl;

import com.sysu.mooc_backed.dao.CourseMapper;
import com.sysu.mooc_backed.entity.Course;
import com.sysu.mooc_backed.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseMapper courseMapper;

    @Autowired
    public CourseServiceImpl(CourseMapper courseMapper){
        this.courseMapper = courseMapper;
    }

    @Override
    public List<Course> findListByCategory2(int category2, int offset, int limit){
        return courseMapper.findListByCategory2(category2, offset, limit);
    }

    @Override
    public int countFindListByCategory2(int category2){
        return courseMapper.countFindListByCategory2(category2);
    }
}
