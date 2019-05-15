package com.school.netlearning.controller;

import com.school.netlearning.Util.CurrentUserUtil;
import com.school.netlearning.pojo.Post;
import com.school.netlearning.pojo.User;
import com.school.netlearning.result.Result;
import com.school.netlearning.result.ResultUtil;
import com.school.netlearning.service.PostService;
import com.school.netlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping(value = "post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "postList")
    public Result findAll(){
        List<Post> postList = postService.findAllByOrderByIdDesc();
        List<Map<String, Object>> postMapList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i<postList.size(); i++){
            User user = userService.findUserById(postList.get(i).getUserId());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", user.getName());
            map.put("post", postList.get(i));
            postMapList.add(map);
        }
        Result result = ResultUtil.success(postMapList);
        return result;
    }

    @GetMapping(value = "findByTitleContaining")
    public Result findByTitleContaining(String title){
        List<Post> byTitleContaining = postService.findByTitleContainingOrderByIdDesc(title);
        List<Map<String, Object>> postMapList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i<byTitleContaining.size(); i++){
            User user = userService.findUserById(byTitleContaining.get(i).getUserId());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", user.getName());
            map.put("post", byTitleContaining.get(i));
            postMapList.add(map);
        }
        Result result = ResultUtil.success(postMapList);
        return result;
    }

    @PostMapping("savePost")
    public Result savePost(HttpServletRequest request, Post post){
        Integer currUserId = CurrentUserUtil.getUserId(request);
        post.setUserId(currUserId);
        post.setTime(new Date());
        Post p = postService.savePost(post);
        Result result = ResultUtil.success();
        return result;
    }
    @GetMapping(value = "deletPost")
    public Result deletPost(Integer id){
        postService.deletPostById(id);
        Result result = ResultUtil.success();
        return result;
    }


}
