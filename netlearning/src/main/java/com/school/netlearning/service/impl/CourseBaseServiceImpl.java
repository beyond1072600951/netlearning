package com.school.netlearning.service.impl;

import com.school.netlearning.pojo.CourseBase;
import com.school.netlearning.pojo.CoursePlan;
import com.school.netlearning.repository.CourseBaseRepository;
import com.school.netlearning.repository.CoursePlanRepository;
import com.school.netlearning.service.CourseBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseBaseServiceImpl implements CourseBaseService {

    @Autowired
    private CourseBaseRepository courseBaseRepository;

    @Autowired
    private CoursePlanRepository coursePlanRepository;


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
        List<CoursePlan> byCourseId = coursePlanRepository.findByCourseId(id);
        for (int i = 0; i<byCourseId.size(); i++){
            coursePlanRepository.deleteById(byCourseId.get(i).getId());
        }
    }

    @Override
    public List<CourseBase> findByNameContaining(String name) {
        List<CourseBase> courseBaseByNameContaining = courseBaseRepository.findByNameContaining(name);
        return courseBaseByNameContaining;
    }
}
