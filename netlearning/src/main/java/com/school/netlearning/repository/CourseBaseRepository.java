package com.school.netlearning.repository;

import com.school.netlearning.pojo.CourseBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseBaseRepository extends JpaRepository<CourseBase, Integer> {

    List<CourseBase> findAll(); //搜索所有课程

    List<CourseBase> findByNameContaining(String name);//根据课程名字搜索
}
