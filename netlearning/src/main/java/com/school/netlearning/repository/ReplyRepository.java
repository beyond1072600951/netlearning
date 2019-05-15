package com.school.netlearning.repository;

import com.school.netlearning.pojo.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    List<Reply> findByUserId(Integer userId);

    List<Reply> findAllByPostIdOrderByIdDesc(Integer postId);
}
