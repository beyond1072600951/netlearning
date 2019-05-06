package com.school.netlearning.controller;

import com.school.netlearning.pojo.News;
import com.school.netlearning.pojo.User;
import com.school.netlearning.result.Result;
import com.school.netlearning.result.ResultUtil;
import com.school.netlearning.service.NewsService;
import com.school.netlearning.service.UserService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @GetMapping(value = "/newsList")
//    public Result findAll(){
//        List<User> newsList = newsService.findAll();
//        Result result = ResultUtil.success(newsList);
//        return result;
//    }
    @GetMapping(value = "/newsList")
    public Result findAll(){
        List<News> newsList = newsService.findAll();
        Result result = ResultUtil.success(newsList);
        return result;
    }

    @PostMapping(value = "/saveNews")
    public Result saveNews(News news){
        News n = newsService.addNews(news);
        Result result = ResultUtil.success();
        return result;
    }

    @GetMapping(value = "/deletNews")
    public Result deletNews(Integer id){
        newsService.deleteNewsById(id);
        Result result = ResultUtil.success();
        return result;
    }

    @GetMapping(value = "/findByNameContaining")
    public Result findByNameContaining(@RequestParam(value = "name") String name){
        List<News> byNameContaining = newsService.findByNameContaining(name);
        Result result = ResultUtil.success(byNameContaining);
        return result;
    }
}
