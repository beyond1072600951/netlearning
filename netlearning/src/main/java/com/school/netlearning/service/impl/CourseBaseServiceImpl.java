package com.school.netlearning.service.impl;

import com.school.netlearning.pojo.CourseBase;
import com.school.netlearning.repository.CourseBaseRepository;
import com.school.netlearning.service.CourseBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseBaseServiceImpl implements CourseBaseService {

    @Autowired
    private CourseBaseRepository courseBaseRepository;


    @Override
    public List<CourseBase> findAll() {
        List<CourseBase> courseBaseList = courseBaseRepository.findAll();
        return courseBaseList;
    }

    @Override
    public CourseBase saveCourseBase(CourseBase courseBase) {
        CourseBase saveCourseBase = courseBaseRepository.save(courseBase);
        return saveCourseBase;
    }

    @Override
    public void deleteCourseBaseById(Integer id) {
        courseBaseRepository.deleteById(id);
    }

    @Override
    public List<CourseBase> findByNameContaining(String name) {
        List<CourseBase> courseBaseByNameContaining = courseBaseRepository.findByNameContaining(name);
        return courseBaseByNameContaining;
    }
}
