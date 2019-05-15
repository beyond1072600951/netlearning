package com.school.netlearning.service;

import com.school.netlearning.pojo.News;
import com.school.netlearning.pojo.User;

import java.util.List;
import java.util.Map;

public interface NewsService {

//    public List<User> findAll();  //查询所有新闻通知
    public List<News> findAll();

    public News addNews(News news);  //添加新闻通知

    public void deleteNewsById(Integer id);   //删除新闻通知

    public  List<News> findByNameContaining(String name);  //搜索

    public List<News> findByUserId(Integer userId);  //通过userId搜索新闻list
}
