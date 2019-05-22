package com.school.netlearning.service;

import com.school.netlearning.pojo.Chapter;

import java.util.List;

public interface ChapterService {

    public List<Chapter> findByLevelOrderById(Integer level);
}
