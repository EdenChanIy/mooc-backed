package com.sysu.mooc_backed.controller;

import com.sysu.mooc_backed.common.utils.Result;
import com.sysu.mooc_backed.common.utils.StringUtils;
import com.sysu.mooc_backed.entity.Collections;
import com.sysu.mooc_backed.entity.Course;
import com.sysu.mooc_backed.entity.User;
import com.sysu.mooc_backed.entity.UserAndCourse;
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

    //2.5获取课程基本信息接口
    @RequestMapping("/course/findCourse")
    public Result findCourseByCidAndUid(String cid, String uid){
        try{
            if(StringUtils.isEmpty(cid)) return Result.error("缺少课程cid");


            int cidInt = Integer.parseInt(cid);
            Course c = courseService.findCourseById(cidInt);
            if(null==c) return Result.error("课程不存在");
            User u = userService.findUserById(c.getLector());

            Map<String, Object> result = new HashMap<>();
            result.put("id", c.getId());
            result.put("img", c.getImg());
            result.put("name", c.getName());
            result.put("subtitle", c.getSubtitle());
            result.put("category1", c.getCategory1());
            result.put("category2", c.getCategory2());
            result.put("needToKnow", c.getNeedToKnow());
            result.put("learningCount", c.getLearningCount());
            result.put("rating", c.getRating());

            Map<String, Object> lector = new HashMap<>();
            if(null!=u){
                lector.put("id", u.getId());
                lector.put("name", u.getName());
                lector.put("icon", u.getIcon());
                lector.put("job", u.getJob());
                result.put("lector", lector);
            }

            if(StringUtils.isEmpty(uid)){
                result.put("favorite", false);
            }else {
                int uidInt = Integer.parseInt(uid);
                UserAndCourse uc;
                uc =courseService.findRelByUidAndCid(uidInt, cidInt);
                if(null==uc){
                    result.put("favorite", false);
                }else {
                    result.put("favorite", true);
                    Map<String, Object> leavePosition = new HashMap<>();
                    leavePosition.put("cid", uc.getCourseId());
                    leavePosition.put("chapter", uc.getChapter());
                    leavePosition.put("period", uc.getPeriod());
                    leavePosition.put("time", uc.getTime());

                    result.put("leavePosition", leavePosition);
                }
            }

            return Result.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("网络异常");
        }
    }
}
