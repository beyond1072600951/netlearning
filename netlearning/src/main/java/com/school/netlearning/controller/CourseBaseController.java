package com.school.netlearning.controller;

import com.school.netlearning.Util.CurrentUserUtil;
import com.school.netlearning.pojo.CourseBase;
import com.school.netlearning.pojo.CoursePlan;
import com.school.netlearning.pojo.User;
import com.school.netlearning.result.Result;
import com.school.netlearning.result.ResultUtil;
import com.school.netlearning.service.CourseBaseService;
import com.school.netlearning.service.CoursePlanService;
import com.school.netlearning.service.FileService;
import com.school.netlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(value = "courseBase")
public class CourseBaseController {

    @Autowired
    private CourseBaseService courseBaseService;

    @Autowired
    private UserService userService;

    @Autowired
    private CoursePlanService coursePlanService;

    @Autowired
    private FileService fileService;

    @GetMapping(value = "courseBaseList")
    public Result findAll() throws Exception{
        List<CourseBase> courseList = courseBaseService.findAll();
        List<Map<String, Object>> courseBaseMapList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < courseList.size(); i++) {
            User user = userService.findUserById(courseList.get(i).getUserId());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", user.getName());
            map.put("courseBase", courseList.get(i));
            courseBaseMapList.add(map);
            if (null != courseList.get(i)) {
                courseList.get(i).setPicpath(fileService.getUrl(courseList.get(i).getPicpath()).toString());
            }
        }
        Result result = ResultUtil.success(courseBaseMapList);
        return result;
    }
    @GetMapping(value = "getUrl")
    public String getPicUrl(String path){
        //if (null != courseBase.getPicpath()){
        String str=null;
        try {
            str =fileService.getUrl(path).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    @PostMapping(value = "saveCourseBase")
    public Result saveCourseBase(HttpServletRequest request, CourseBase courseBase) {
        Integer currUserId = CurrentUserUtil.getUserId(request);
        courseBase.setUserId(currUserId);
        CourseBase saveCourseBase = courseBaseService.saveCourseBase(courseBase);
        Result result = ResultUtil.success();
        return result;
    }

    @GetMapping(value = "findByNameContaining")
    public Result findByNameContaining(@RequestParam(value = "name") String name) {
        List<CourseBase> byNameContaining = courseBaseService.findByNameContaining(name);
        List<Map<String, Object>> courseBaseMapList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < byNameContaining.size(); i++) {
            User user = userService.findUserById(byNameContaining.get(i).getUserId());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", user.getName());
            map.put("courseBase", byNameContaining.get(i));
            courseBaseMapList.add(map);
            if (null != byNameContaining.get(i)) {
                try {
                    byNameContaining.get(i).setPicpath(fileService.getUrl(byNameContaining.get(i).getPicpath()).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Result result = ResultUtil.success(courseBaseMapList);
        return result;
    }

    @GetMapping(value = "deletCourseBase")
    public Result deletCourseBase(Integer id) {
        courseBaseService.deleteCourseBaseById(id);
        List<CoursePlan> byCourseIdAndParentIdOrderByOrderby = coursePlanService.findByCourseIdAndParentIdOrderByOrderby(id, null);
        List<CoursePlan> findAllChapterOrderByOrderby = new ArrayList<CoursePlan>();
        for (CoursePlan chapter : byCourseIdAndParentIdOrderByOrderby) {
            findAllChapterOrderByOrderby.add(chapter);
            List<CoursePlan> findAllByParentIdOrderByOrderby = coursePlanService.findAllByParentIdOrderByOrderby(chapter.getId());
            for (CoursePlan secton : findAllByParentIdOrderByOrderby) {
                findAllChapterOrderByOrderby.add(secton);
            }
        }
        for (int i = 0; i < findAllChapterOrderByOrderby.size(); i++) {
            coursePlanService.deletCoursePlan(findAllChapterOrderByOrderby.get(i).getId());
        }
        Result result = ResultUtil.success();
        return result;
    }

}
