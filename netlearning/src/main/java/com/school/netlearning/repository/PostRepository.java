package com.school.netlearning.repository;

import com.school.netlearning.pojo.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    //List<Post> findAll();
    List<Post> findAllByOrderByIdDesc();  //通过id降序排列

    List<Post> findByTitleContainingOrderByIdDesc(String title);

    List<Post> findByUserId(Integer userId);

}
