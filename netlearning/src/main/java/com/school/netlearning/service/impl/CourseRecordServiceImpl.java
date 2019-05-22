package com.school.netlearning.service.impl;

import com.school.netlearning.pojo.CourseRecord;
import com.school.netlearning.repository.CourseRecordRepository;
import com.school.netlearning.service.CourseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseRecordServiceImpl implements CourseRecordService {

    @Autowired
    private CourseRecordRepository courseRecordRepository;

    @Override
    public List<CourseRecord> findAll() {
        List<CourseRecord> recordList = courseRecordRepository.findAll();
        return recordList;
    }

    @Override
    public List<CourseRecord> findByUserId(Integer userId) {
        List<CourseRecord> courseByUserId = courseRecordRepository.findByUserId(userId);
        return courseByUserId;
    }
}
