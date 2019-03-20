package com.sysu.mooc_backed.controller;

import com.sysu.mooc_backed.common.utils.Result;
import com.sysu.mooc_backed.common.utils.StringUtils;
import com.sysu.mooc_backed.entity.Course;
import com.sysu.mooc_backed.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    //2.1 获取课程分类及一级分类推荐课程
    @RequestMapping("/course/findListRecommend")
    public Result findListByCategory1(){
        try{
            List<Integer> category1List = new ArrayList<>();
            category1List = courseService.findCategory1List();

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
}
