package com.school.netlearning.controller;

import com.school.netlearning.Util.CurrentUserUtil;
import com.school.netlearning.pojo.News;
import com.school.netlearning.pojo.User;
import com.school.netlearning.result.Result;
import com.school.netlearning.result.ResultUtil;
import com.school.netlearning.service.NewsService;
import com.school.netlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    public Result findAll() {
        List<News> newsList = newsService.findAll();
        List<Map<String, Object>> newsMapList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < newsList.size(); i++) {
            User user = userService.findUserById(newsList.get(i).getUserId());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", user.getName());
            map.put("news", newsList.get(i));
            newsMapList.add(map);
        }
        Result result = ResultUtil.success(newsMapList);
        return result;
    }

    @PostMapping(value = "/saveNews")
    public Result saveNews(HttpServletRequest request, News news) {
        Integer currUserId = CurrentUserUtil.getUserId(request);
        news.setUserId(currUserId);
        news.setReleaseTime(new Date());
        News n = newsService.addNews(news);
        Result result = ResultUtil.success();
        return result;
    }


    @GetMapping(value = "/deletNews")
    public Result deletNews(Integer id) {
        newsService.deleteNewsById(id);
        Result result = ResultUtil.success();
        return result;
    }

    @GetMapping(value = "/findByNameContaining")
    public Result findByNameContaining(@RequestParam(value = "name") String name) {
        List<News> byNameContaining = newsService.findByNameContaining(name);
        List<Map<String, Object>> newsMapList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < byNameContaining.size(); i++) {
            User user = userService.findUserById(byNameContaining.get(i).getUserId());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", user.getName());
            map.put("news", byNameContaining.get(i));
            newsMapList.add(map);
        }
        Result result = ResultUtil.success(newsMapList);
        return result;
    }
}
