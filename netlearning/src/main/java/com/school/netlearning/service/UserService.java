package com.school.netlearning.service;

import com.school.netlearning.pojo.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();  //查询所有用户

    public User addUser(User user);  //添加用户

    public User updataIsPost(Integer id,String ispost);

    public User updataIsReply(Integer id,String isreply);

    public void deletUserById(Integer id);  //删除用户

    public User findUserById(Integer id);

    public User findByName(String name);

    public  List<User> findByNameContaining(String userName);

    public List<User> findAllByUserName(String userName);

}
