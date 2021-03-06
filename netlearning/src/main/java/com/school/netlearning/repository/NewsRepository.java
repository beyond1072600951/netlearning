package com.school.netlearning.repository;

import com.school.netlearning.pojo.News;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface  NewsRepository extends JpaRepository<News, Integer> {

    /**
     *
     */

     List<News> findAll();

    List<News> findByNameContaining(String name);

    List<News> findByUserId(Integer userId);


}
