package com.school.netlearning.repository;

import com.school.netlearning.pojo.News;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface  NewsRepository extends JpaRepository<News, Integer> {

    /**
     *
     */
    //@Query(nativeQuery = true, value = "SELECT n.name,n.content,n.release_time,u.name FROM news n JOIN user u ON n.user_id = u.id")
     List<News> findAll();

    List<News> findByNameContaining(String name);

    
}
