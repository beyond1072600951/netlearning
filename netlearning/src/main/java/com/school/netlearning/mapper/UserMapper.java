package com.school.netlearning.mapper;


import com.school.netlearning.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> testFind();
}