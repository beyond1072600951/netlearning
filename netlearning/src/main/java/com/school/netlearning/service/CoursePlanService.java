package com.school.netlearning.service;

import com.school.netlearning.pojo.CoursePlan;

import java.util.List;

public interface CoursePlanService {

//    public List<CoursePlan> findByCourseId(Integer courseId);

    /**
     * 通过课程id（courseId）查出这个课程的所有章节
     * @param courseId
     * @return
     */
    public List<CoursePlan> findByCourseIdAndParentIdOrderByOrderby(Integer courseId,Integer parentId);

    /**
     *通过父节点id（parentId）查询有序
     */
    public List<CoursePlan> findAllByParentIdOrderByOrderby(Integer parentId);

    public CoursePlan addCoursePlan(CoursePlan coursePlan);

    public void deletCoursePlan(Integer id);

    public CoursePlan findCoursePlanById(Integer id);

    public void updateCoursePlan(String name, String description, Integer status, Integer id);
}
