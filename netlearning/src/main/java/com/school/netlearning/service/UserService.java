package com.school.netlearning.service;

import com.school.netlearning.pojo.User;

import java.util.List;

public interface UserService {

    User register(User user, Long roleId) throws Exception;

    User findById(Long id) throws Exception;

    List<User> findAll(String authority) throws Exception;

    User updateUser(User user) throws Exception;
}
