package com.school.netlearning.service.impl;

import com.school.netlearning.pojo.News;
import com.school.netlearning.pojo.User;
import com.school.netlearning.repository.NewsRepository;
import com.school.netlearning.repository.RoleRepository;
import com.school.netlearning.repository.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public News addNews(News news) {
        News save = newsRepository.save(news);
        return save;
    }

    @Override
    public void deleteNewsById(Integer id) {
        newsRepository.deleteById(id);
    }

    @Override
    public List<News> findByNameContaining(String name) {
        List<News> list = newsRepository.findByNameContaining(name);
        return list;
    }
}
