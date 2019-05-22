package com.school.netlearning.service;

import com.school.netlearning.pojo.CourseBase;

import java.util.List;

public interface CourseBaseService {

    public List<CourseBase> findAll();

    public CourseBase saveCourseBase(CourseBase courseBase);//添加

    public void deleteCourseBaseById(Integer id);//删除

    public List<CourseBase> findByNameContaining(String name);//搜索

    public CourseBase findCourseBaseById(Integer id);
}
