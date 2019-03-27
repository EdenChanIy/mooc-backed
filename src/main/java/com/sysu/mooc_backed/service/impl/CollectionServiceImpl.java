package com.sysu.mooc_backed.service.impl;

import com.sysu.mooc_backed.dao.CollectionMapper;
import com.sysu.mooc_backed.entity.Collections;
import com.sysu.mooc_backed.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: EdenChanIy
 * @Date: 2019/3/21 0:51
 */
@Service
public class CollectionServiceImpl implements CollectionService {
    private CollectionMapper collectionMapper;

    @Autowired
    public CollectionServiceImpl(CollectionMapper collectionMapper){
        this.collectionMapper = collectionMapper;
    }

    @Override
    public Collections findRecordByUserIdAndCourseId(int userId, int courseId){
        return collectionMapper.findRecordByUserIdAndCourseId(userId, courseId);
    }

    @Override
    public void addCollectionByUserIdAndCourseId(int userId, int courseId){
        collectionMapper.addCollectionByUserIdAndCourseId(userId, courseId);
    }

    @Override
    public void deleteCollectionByUserIdAndCourseId(int userId, int courseId){
        collectionMapper.deleteCollectionByUserIdAndCourseId(userId, courseId);
    }

    @Override
    public List<Integer> findRecordsByUserId(int userId){
        return collectionMapper.findRecordsByUserId(userId);
    }
}
