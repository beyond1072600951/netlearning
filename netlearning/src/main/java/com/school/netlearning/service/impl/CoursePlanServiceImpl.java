package com.school.netlearning.service.impl;

import com.school.netlearning.pojo.CoursePlan;
import com.school.netlearning.repository.CoursePlanRepository;
import com.school.netlearning.service.CoursePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursePlanServiceImpl implements CoursePlanService {

    @Autowired
    CoursePlanRepository coursePlanRepository;

//    @Override
//    public List<CoursePlan> findByCourseId(Integer courseId) {
//        List<CoursePlan> coursePlanList = coursePlanRepository.findByCourseId(courseId);
//        return coursePlanList;
//    }

    @Override
    public List<CoursePlan> findByCourseIdAndParentIdOrderByOrderby(Integer courseId,Integer parentId) {
        List<CoursePlan> byCourseId = coursePlanRepository.findByCourseIdAndParentIdOrderByOrderby(courseId,null);
        return byCourseId;
    }

    @Override
    public List<CoursePlan> findAllByParentIdOrderByOrderby(Integer parentId) {
        List<CoursePlan> findAllByParentIdOrderByOrderby = coursePlanRepository.findAllByParentIdOrderByOrderby(parentId);
        return findAllByParentIdOrderByOrderby;
    }

    @Override
    public CoursePlan addCoursePlan(CoursePlan coursePlan) {
        CoursePlan saveCoursePlan = coursePlanRepository.save(coursePlan);
        return saveCoursePlan;
    }

    @Override
    public void deletCoursePlan(Integer id) {
        coursePlanRepository.deleteById(id);
    }
}
