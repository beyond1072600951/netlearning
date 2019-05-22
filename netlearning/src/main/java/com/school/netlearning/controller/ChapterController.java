package com.school.netlearning.controller;

import com.school.netlearning.pojo.Chapter;
import com.school.netlearning.result.Result;
import com.school.netlearning.result.ResultUtil;
import com.school.netlearning.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "chapterNum")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping(value = "chapterNumList")
    public Result findByLevelOrderById(Integer level){
        List<Chapter> byLevelOrderById = chapterService.findByLevelOrderById(level);
        Result result = ResultUtil.success(byLevelOrderById);
        return result;
    }

}
