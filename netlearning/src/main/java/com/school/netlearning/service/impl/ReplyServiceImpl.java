package com.school.netlearning.service.impl;

import com.school.netlearning.pojo.Reply;
import com.school.netlearning.repository.ReplyRepository;
import com.school.netlearning.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService{

    @Autowired
    ReplyRepository replyRepository;

    @Override
    public List<Reply> findByUserId(Integer userId) {
        List<Reply> byUserId = replyRepository.findByUserId(userId);
        return byUserId;
    }

    @Override
    public List<Reply> findAllByPostIdOrderByIdDesc(Integer postId) {
        List<Reply> byPostIdOrderByIdDesc = replyRepository.findAllByPostIdOrderByIdDesc(postId);
        return byPostIdOrderByIdDesc;
    }

    @Override
    public Reply saveReply(Reply reply) {
        Reply saveReply = replyRepository.save(reply);
        return saveReply;
    }

    @Override
    public void deletReply(Integer id) {
        replyRepository.deleteById(id);
    }
}
