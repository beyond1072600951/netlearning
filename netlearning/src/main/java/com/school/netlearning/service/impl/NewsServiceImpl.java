package com.school.netlearning.service.impl;

import com.school.netlearning.pojo.News;
import com.school.netlearning.repository.NewsRepository;
import com.school.netlearning.repository.RoleRepository;
import com.school.netlearning.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<News> findAll() {
        List<News> newsList = newsRepository.findAll();
        return newsList;
    }

    @Override
    public News addNews(News news) {
        return null;
    }

    @Override
    public void deleteNewsById(Integer id) {

    }
}
