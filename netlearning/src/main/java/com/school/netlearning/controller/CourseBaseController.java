package com.school.netlearning.controller;

import com.school.netlearning.Util.CurrentUserUtil;
import com.school.netlearning.pojo.CourseBase;
import com.school.netlearning.pojo.CoursePlan;
import com.school.netlearning.pojo.User;
import com.school.netlearning.result.Result;
import com.school.netlearning.result.ResultUtil;
import com.school.netlearning.service.CourseBaseService;
import com.school.netlearning.service.CoursePlanService;
import com.school.netlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "courseBase")
public class CourseBaseController {

    @Autowired
    private CourseBaseService courseBaseService;

    @Autowired
    private UserService userService;

    @Autowired
    private CoursePlanService coursePlanService;

    @GetMapping(value = "courseBaseList")
    public Result findAll(){
        List<CourseBase> courseList = courseBaseService.findAll();
        List<Map<String, Object>> courseBaseMapList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < courseList.size(); i++) {
            User user = userService.findUserById(courseList.get(i).getUserId());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", user.getName());
            map.put("courseBase", courseList.get(i));
            courseBaseMapList.add(map);
        }
        Result result = ResultUtil.success(courseBaseMapList);
        return result;
    }

    @PostMapping(value = "saveCourseBase")
    public Result saveCourseBase(HttpServletRequest request, CourseBase courseBase){
        Integer currUserId = CurrentUserUtil.getUserId(request);
        courseBase.setUserId(currUserId);
//        courseBase.setPicpath("http://lixinzhong.top/images/dot.png");
        CourseBase saveCourseBase = courseBaseService.saveCourseBase(courseBase);
        Result result = ResultUtil.success();
        return  result;
    }
    @GetMapping(value = "findByNameContaining")
    public Result findByNameContaining(@RequestParam(value = "name") String name){
        List<CourseBase> byNameContaining = courseBaseService.findByNameContaining(name);
        List<Map<String, Object>> courseBaseMapList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < byNameContaining.size(); i++) {
            User user = userService.findUserById(byNameContaining.get(i).getUserId());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", user.getName());
            map.put("courseBase", byNameContaining.get(i));
            courseBaseMapList.add(map);
        }
        Result result = ResultUtil.success(courseBaseMapList);
        return result;
    }
    @GetMapping(value = "deletCourseBase")
    public Result deletCourseBase(Integer id){
        courseBaseService.deleteCourseBaseById(id);
        List<CoursePlan> byCourseIdAndParentIdOrderByOrderby = coursePlanService.findByCourseIdAndParentIdOrderByOrderby(id,null);
        List<CoursePlan> findAllChapterOrderByOrderby = new ArrayList<CoursePlan>();
        for(CoursePlan chapter : byCourseIdAndParentIdOrderByOrderby){
            findAllChapterOrderByOrderby.add(chapter);
            List<CoursePlan> findAllByParentIdOrderByOrderby = coursePlanService.findAllByParentIdOrderByOrderby(chapter.getId());
            for(CoursePlan secton :findAllByParentIdOrderByOrderby){
                findAllChapterOrderByOrderby.add(secton);
            }
        }
        for (int i = 0; i<findAllChapterOrderByOrderby.size(); i++){
            coursePlanService.deletCoursePlan(findAllChapterOrderByOrderby.get(i).getId());
        }
        Result result = ResultUtil.success();
        return result;
    }

}
