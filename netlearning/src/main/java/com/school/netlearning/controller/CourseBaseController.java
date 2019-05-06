package com.school.netlearning.controller;

import com.school.netlearning.pojo.CourseBase;
import com.school.netlearning.result.Result;
import com.school.netlearning.result.ResultUtil;
import com.school.netlearning.service.CourseBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "courseBase")
public class CourseBaseController {

    @Autowired
    private CourseBaseService courseBaseService;

    @GetMapping(value = "courseBaseList")
    public Result findAll(){
        List<CourseBase> courseList = courseBaseService.findAll();
        Result result = ResultUtil.success(courseList);
        return result;
    }

    @PostMapping(value = "saveCourseBase")
    public Result saveCourseBase(CourseBase courseBase){
        CourseBase saveCourseBase = courseBaseService.saveCourseBase(courseBase);
        Result result = ResultUtil.success();
        return  result;
    }
    @GetMapping(value = "findByNameContaining")
    public Result findByNameContaining(@RequestParam(value = "name") String name){
        List<CourseBase> byNameContaining = courseBaseService.findByNameContaining(name);
        Result result = ResultUtil.success(byNameContaining);
        return result;
    }

}
