package com.school.netlearning.service;

import com.school.netlearning.pojo.CourseRecord;

import java.util.List;

public interface CourseRecordService {

    public List<CourseRecord> findAll();

    public List<CourseRecord> findByUserId(Integer userId);
}
