package com.sysu.mooc_backed.service.impl;

import com.sysu.mooc_backed.dao.CourseMapper;
import com.sysu.mooc_backed.entity.*;
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
    public List<Integer> findCategory1List(){
        return courseMapper.findCategory1List();
    }

    @Override
    public List<Course> findListByCategory1(int category1){
        return courseMapper.findListByCategory1(category1);
    }

    @Override
    public List<Course> findListByCategory2(int category2, int offset, int limit){
        return courseMapper.findListByCategory2(category2, offset, limit);
    }

    @Override
    public int countFindListByCategory2(int category2){
        return courseMapper.countFindListByCategory2(category2);
    }

    @Override
    public List<Course> findRecommendListByInterests(List<Integer> interests){
        return courseMapper.findRecommendListByInterests(interests);
    }

    @Override
    public Course findCourseById(int id){
        return courseMapper.findCourseById(id);
    }

    @Override
    public UserAndCourse findRelByUidAndCid(int userId, int courseId){
        return courseMapper.findRelByUidAndCid(userId, courseId);
    }

    @Override
    public List<UserAndCourse> findRelByUid3(int userId){
        return courseMapper.findRelByUid3(userId);
    }

    @Override
    public List<Chapter> findChaptersByCid(int cid){
        return courseMapper.findChaptersByCid(cid);
    }

    @Override
    public List<Period> findPeriodsByCid(int cid){
        return courseMapper.findPeriodsByCid(cid);
    }

    @Override
    public List<Node> findNodesByPid(int pid){
        return courseMapper.findNodesByPid(pid);
    }

    @Override
    public UserAndPeriod findRelByUidAndPid(int userId, int periodId){
        return courseMapper.findRelByUidAndPid(userId, periodId);
    }
}
