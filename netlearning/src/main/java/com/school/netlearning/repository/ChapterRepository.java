package com.school.netlearning.repository;

import com.school.netlearning.pojo.Chapter;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter, Integer> {

    List<Chapter> findByLevelOrderById(Integer level);
}
