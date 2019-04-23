package com.school.netlearning.service;

import com.school.netlearning.pojo.News;

import java.util.List;

public interface NewsService {

    public List<News> findAll();  //查询所有新闻通知

    public News addNews(News news);  //添加新闻通知

    public void deleteNewsById(Integer id);   //删除新闻通知

//    public  List<News> findByNameContaining(String name);
}
