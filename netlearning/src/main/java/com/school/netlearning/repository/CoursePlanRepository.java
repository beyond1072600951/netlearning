package com.school.netlearning.repository;

import com.school.netlearning.pojo.CoursePlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursePlanRepository extends JpaRepository<CoursePlan, Integer> {
    /**
     * 通过课程id（courseId）查出这个课程的所有章节
     * @param courseId
     * @return
     */
//    List<CoursePlan> findByCourseId(Integer courseId);
    List<CoursePlan> findByCourseIdAndParentIdOrderByOrderby(Integer courseId,Integer parentId);
    /**
     *通过父节点id（parentId）查询有序
     */
    List<CoursePlan> findAllByParentIdOrderByOrderby(Integer parentId);


}
