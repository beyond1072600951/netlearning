package com.school.netlearning.controller;

import com.alibaba.fastjson.JSON;
import com.school.netlearning.pojo.CoursePlan;
import com.school.netlearning.result.Result;
import com.school.netlearning.result.ResultUtil;
import com.school.netlearning.service.CoursePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

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
            for(CoursePlan section :findAllByParentIdOrderByOrderby){
                findAllChapterOrderByOrderby.add(section);
            }
        }
        Result result = ResultUtil.success(findAllChapterOrderByOrderby);
        return result;
    }

//    @GetMapping(value = "findAllByParentIdOrderByOrderby")
//    public Result findAllByParentIdOrderByOrderby(Integer parentId){
//        List<CoursePlan> allByParentIdOrderByOrderby = coursePlanService.findAllByParentIdOrderByOrderby(parentId);
//        Result result = ResultUtil.success(allByParentIdOrderByOrderby);
//        return result;
//    }

    @PostMapping(value = "addCoursePlan")
    public Result addCoursePlan(CoursePlan coursePlan){
        CoursePlan addCoursePlan = coursePlanService.addCoursePlan(coursePlan);
        Result result = ResultUtil.success(addCoursePlan);
        return result;
    }

    @GetMapping(value = "selectById")
    public Result selectById(Integer id){
        CoursePlan coursePlanById = coursePlanService.findCoursePlanById(id);
        Result result = ResultUtil.success(coursePlanById);
        return result;
    }

    @PostMapping(value = "updateCoursePlan")
    public Result updateCoursePlan(@RequestParam("id") Integer id, @RequestParam("name") String name, @RequestParam("description") String description,
                                   @RequestParam("status") Integer status){
        coursePlanService.updateCoursePlan(name, description, status, id);
        Result result = ResultUtil.success();
        return result;
    }

    @GetMapping(value = "deletChapter")
    public Result deletChapter(Integer id){
        coursePlanService.deletCoursePlan(id);
        Result result = ResultUtil.success();
        return result;
    }


}
