package com.school.netlearning.repository;

import com.school.netlearning.pojo.News;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface  NewsRepository extends JpaRepository<News, Integer> {

    List<News> findAll();


}
