package com.school.netlearning.repository;

import com.school.netlearning.pojo.CourseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRecordRepository extends JpaRepository<CourseRecord, Integer> {

    List<CourseRecord> findAll();

    List<CourseRecord> findByUserId(Integer userId);
}
