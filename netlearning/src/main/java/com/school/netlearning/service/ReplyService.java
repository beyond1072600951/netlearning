package com.school.netlearning.service;

import com.school.netlearning.pojo.Reply;

import java.util.List;

public interface ReplyService {

    public List<Reply> findByUserId(Integer userId);  //通过userId搜索该用户发布的所有回复

    public List<Reply> findAllByPostIdOrderByIdDesc(Integer postId);  //通过postId搜索该条帖子的所有回复

    public Reply saveReply(Reply reply);   //添加

    public void deletReply(Integer id);  //删除
}
