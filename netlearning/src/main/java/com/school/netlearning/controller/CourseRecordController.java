package com.school.netlearning.controller;

import com.school.netlearning.pojo.CourseRecord;
import com.school.netlearning.pojo.User;
import com.school.netlearning.result.Result;
import com.school.netlearning.result.ResultUtil;
import com.school.netlearning.service.CourseBaseService;
import com.school.netlearning.service.CoursePlanService;
import com.school.netlearning.service.CourseRecordService;
import com.school.netlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/courseRecord")
public class CourseRecordController {

    @Autowired
    private CourseRecordService courseRecordService;

    @Autowired
    private CourseBaseService courseBaseService;

    @Autowired
    private CoursePlanService coursePlanService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/recordList")
    public Result recordList(){
        List<CourseRecord> recordList = courseRecordService.findAll();
        List<Map<String, Object>> recordMapList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i<recordList.size(); i++){
            Map<String, Object> map = new HashMap<String, Object>();
            String stuName = userService.findUserById(recordList.get(i).getUserId()).getName();
            String courseBaseName = courseBaseService.findCourseBaseById(recordList.get(i).getCourseBaseId()).getName();
            String coursePlanName = coursePlanService.findCoursePlanById(recordList.get(i).getCoursePlanId()).getName();
            //Date recordTime = recordList.get(i).getRecordTime();
            map.put("stuName", stuName);
            map.put("courseBaseName", courseBaseName);
            map.put("coursePlanName", coursePlanName);
            map.put("courseRecord", recordList.get(i));
            recordMapList.add(map);
        }
        Result result = ResultUtil.success(recordMapList);
        return result;
    }

    @GetMapping(value = "/findByNameContaining")
    public Result findByNameContaining(@RequestParam(value = "userName") String userName){
        List<User> userList = userService.findByNameContaining(userName);
        List<CourseRecord> courseRecordListByUserId = new ArrayList<CourseRecord>();
        List<Map<String, Object>> recordMapList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i<userList.size(); i++){
            List<CourseRecord> courseRecordByUserId = courseRecordService.findByUserId(userList.get(i).getId());
            for (CourseRecord courseRecord :courseRecordByUserId){
                courseRecordListByUserId.add(courseRecord);
            }
        }
        for (int i = 0; i<courseRecordListByUserId.size(); i++){
            Map<String, Object> map = new HashMap<String, Object>();
            String stuName = userService.findUserById(courseRecordListByUserId.get(i).getUserId()).getName();
            String courseBaseName = courseBaseService.findCourseBaseById(courseRecordListByUserId.get(i).getCourseBaseId()).getName();
            String coursePlanName = coursePlanService.findCoursePlanById(courseRecordListByUserId.get(i).getCoursePlanId()).getName();
            //Date recordTime = courseRecordListByUserId.get(i).getRecordTime();
            map.put("stuName", stuName);
            map.put("courseBaseName", courseBaseName);
            map.put("coursePlanName", coursePlanName);
            map.put("courseRecord", courseRecordListByUserId.get(i));
            recordMapList.add(map);
        }
        Result result = ResultUtil.success(recordMapList);
        return result;
    }
}
