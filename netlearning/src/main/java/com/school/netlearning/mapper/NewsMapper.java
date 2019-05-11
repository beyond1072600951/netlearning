package com.school.netlearning.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface NewsMapper {

    Map<String,Object> findAll();
}
