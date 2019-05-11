package com.school.netlearning.controller;

import com.alibaba.fastjson.JSON;
import com.school.netlearning.pojo.CoursePlan;
import com.school.netlearning.result.Result;
import com.school.netlearning.result.ResultUtil;
import com.school.netlearning.service.CoursePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "coursePlan")
public class CoursePlanController {
    @Autowired
    CoursePlanService coursePlanService;
//
//    @GetMapping(value = "findByCourseId")
//    public Result findByCourseId(Integer courseId){
//        List<CoursePlan> byCourseIdList = coursePlanService.findByCourseId(courseId);
//        Result result = ResultUtil.success(byCourseIdList);
//        return  result;
//    }

    /**
     * 把一个课程的所有章节有序放到一个List中
     * @param courseId
     * @return
     */
    @GetMapping(value = "findAllChapterOrderByOrderby")
    public Result findAllChapterOrderByOrderby(Integer courseId){
        List<CoursePlan> byCourseIdAndParentIdOrderByOrderby = coursePlanService.findByCourseIdAndParentIdOrderByOrderby(courseId,null);
        List<CoursePlan> findAllChapterOrderByOrderby = new ArrayList<CoursePlan>();
        for(CoursePlan chapter : byCourseIdAndParentIdOrderByOrderby){
            findAllChapterOrderByOrderby.add(chapter);
            List<CoursePlan> findAllByParentIdOrderByOrderby = coursePlanService.findAllByParentIdOrderByOrderby(chapter.getId());
            for(CoursePlan secton :findAllByParentIdOrderByOrderby){
                findAllChapterOrderByOrderby.add(secton);
            }
        }
        Result result = ResultUtil.success(findAllChapterOrderByOrderby);
        return result;
    }

    @GetMapping(value = "addCoursePlan")
    public Result addCoursePlan(CoursePlan coursePlan){
        CoursePlan addCoursePlan = coursePlanService.addCoursePlan(coursePlan);
        Result result = ResultUtil.success(addCoursePlan);
        return result;
    }

    @GetMapping(value = "deletChapter")
    public Result deletChapter(Integer id){
        coursePlanService.deletCoursePlan(id);
        Result result = ResultUtil.success();
        return result;
    }
}
