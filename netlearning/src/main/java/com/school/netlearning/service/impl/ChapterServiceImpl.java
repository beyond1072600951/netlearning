package com.school.netlearning.service.impl;

import com.school.netlearning.pojo.Chapter;
import com.school.netlearning.repository.ChapterRepository;
import com.school.netlearning.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    @Override
    public List<Chapter> findByLevelOrderById(Integer level) {
        List<Chapter> byLevelOrderById = chapterRepository.findByLevelOrderById(level);
        return byLevelOrderById;
    }
}
