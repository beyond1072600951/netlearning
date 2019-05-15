package com.school.netlearning.service;

import com.school.netlearning.pojo.Post;

import java.util.List;

public interface PostService {

    //public List<Post> findAll();    //查询所有帖子
    public List<Post> findAllByOrderByIdDesc();  //通过id降序排列

    public List<Post> findByTitleContainingOrderByIdDesc(String title);  //根据主题搜索帖子

    public Post savePost(Post post);  //添加

    public void deletPostById(Integer id);//删除

    public List<Post> findByUserId(Integer userId);  //查询一个用户发表的所有帖子
}
