package com.school.netlearning.service.impl;

import com.school.netlearning.pojo.Post;
import com.school.netlearning.pojo.Reply;
import com.school.netlearning.repository.PostRepository;
import com.school.netlearning.repository.ReplyRepository;
import com.school.netlearning.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Override
    public List<Post> findAllByOrderByIdDesc() {
        List<Post> orderByIdDesc = postRepository.findAllByOrderByIdDesc();
        return orderByIdDesc;
    }

    @Override
    public List<Post> findByTitleContainingOrderByIdDesc(String title) {
        List<Post> byTitleContaining = postRepository.findByTitleContainingOrderByIdDesc(title);
        return byTitleContaining;
    }

    @Override
    public Post savePost(Post post) {
        Post save = postRepository.save(post);
        return save;
    }

    @Override
    public void deletPostById(Integer id) {
        postRepository.deleteById(id);
        List<Reply> allByPostIdOrderByIdDesc = replyRepository.findAllByPostIdOrderByIdDesc(id);
        for (int i = 0; i<allByPostIdOrderByIdDesc.size(); i++){
            replyRepository.deleteById(allByPostIdOrderByIdDesc.get(i).getId());
        }
    }


    @Override
    public List<Post> findByUserId(Integer userId) {
        List<Post> byUserIdList = postRepository.findByUserId(userId);
        return byUserIdList;
    }
}
