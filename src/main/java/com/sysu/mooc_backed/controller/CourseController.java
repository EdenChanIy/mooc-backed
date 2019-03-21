package com.sysu.mooc_backed.controller;

import com.sysu.mooc_backed.common.utils.Result;
import com.sysu.mooc_backed.common.utils.StringUtils;
import com.sysu.mooc_backed.entity.Collections;
import com.sysu.mooc_backed.service.CollectionService;
import com.sysu.mooc_backed.service.CourseService;
import com.sysu.mooc_backed.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CourseController {

    private final CourseService courseService;
    private final UserService userService;
    private final CollectionService collectionService;

    @Autowired
    public CourseController(CourseService courseService,
                                UserService userService,
                                    CollectionService collectionService){
        this.courseService = courseService;
        this.userService = userService;
        this.collectionService = collectionService;
    }

    //2.1 获取课程分类及一级分类推荐课程
    @RequestMapping("/course/findListRecommend")
    public Result findListByCategory1(){
        try{
            List<Integer> category1List = courseService.findCategory1List();

            List<Map> result = new ArrayList<>();

            for(int c: category1List){
                Map<String, Object> category2Map = new HashMap<>();
                category2Map.put("category1", c);
                category2Map.put("category2", courseService.findListByCategory1(c));
                result.add(category2Map);
            }

            return Result.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("网络异常");
        }
    }

    //2.2 根据分类分页获取课程
    @RequestMapping("/course/findListByCategory2")
    public Result findListByCategory2(String category2, String page, String limit){
        try{
            if(StringUtils.isEmpty(category2)){
                return Result.error("缺少分类id");
            }
            int limitInt = StringUtils.isEmpty(limit)?10:Integer.parseInt(limit);
            int offset = StringUtils.isEmpty(page)?0:(Integer.parseInt(page)-1)*limitInt;
            int category2Int = Integer.parseInt(category2);

            Map<String, Object> result = new HashMap<>();
            result.put("courses", courseService.findListByCategory2(category2Int, offset, limitInt));
            result.put("courseNum", courseService.countFindListByCategory2(category2Int));

            return Result.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("网络异常");
        }
    }

    //2.3 根据兴趣获取推荐课程
    @RequestMapping("/course/findRecommendListByUserId")
    public Result findRecommendListByUserId(String userId){
        try{
            if(StringUtils.isEmpty(userId)) return Result.error("缺少用户id");
            int userIdInt = Integer.parseInt(userId);

            if(null==userService.findUserById(userIdInt)) return Result.error("用户id不存在");

            List<Integer> interests = userService.findInterestListByUserId(userIdInt);
            return Result.success(courseService.findRecommendListByInterests(interests));
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("网络异常");
        }
    }

    //2.4 更新用户对某课程的收藏状态
    @RequestMapping(value = "/course/updateCollectionStatus", method = RequestMethod.POST)
    public Result updateCollectionStatus(String uid, String cid, String favorite){
        try{
            if(StringUtils.isEmpty(uid)) return Result.error("缺少用户uid");
            if(StringUtils.isEmpty(cid)) return Result.error("缺少课程cid");
            if(StringUtils.isEmpty(favorite)) return Result.error("缺少收藏状态favorite");

            int uidInt = Integer.parseInt(uid);
            int cidInt = Integer.parseInt(cid);
            boolean fb = Boolean.parseBoolean(favorite);

            Collections collections = collectionService.findRecordByUserIdAndCourseId(uidInt, cidInt);
            if(fb){
                if(null== collections){
                    collectionService.addCollectionByUserIdAndCourseId(uidInt, cidInt);
                    return Result.success("收藏成功");
                }else{
                    return Result.error("已收藏，勿重复收藏", 304);
                }
            }else{
                if(null == collections){
                    return Result.error("未收藏，无法取消", 304);
                }else{
                    collectionService.deleteCollectionByUserIdAndCourseId(uidInt, cidInt);
                    return Result.success("取消收藏成功");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("网络异常");
        }
    }
}
