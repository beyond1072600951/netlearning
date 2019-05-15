package com.school.netlearning.controller;

import com.school.netlearning.Util.CurrentUserUtil;
import com.school.netlearning.pojo.Reply;
import com.school.netlearning.result.Result;
import com.school.netlearning.result.ResultUtil;
import com.school.netlearning.service.PostService;
import com.school.netlearning.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "reply")
public class ReplyController {

    @Autowired
    ReplyService replyService;

    @Autowired
    PostService postService;

    @GetMapping(value = "replyList")
    public Result replyList(Integer postId){
        List<Reply> byPostIdOrderByIdDesc = replyService.findAllByPostIdOrderByIdDesc(postId);
        Result result  = ResultUtil.success(byPostIdOrderByIdDesc);
        return result;
    }

    @PostMapping(value = "saveReply")
    public Result saveReply(HttpServletRequest request, Reply reply){
        Integer currUserId = CurrentUserUtil.getUserId(request);
        reply.setUserId(currUserId);
        reply.setTime(new Date());
        Reply saveReply = replyService.saveReply(reply);
        Result result = ResultUtil.success();
        return result;
    }

    @GetMapping(value = "deletReply")
    public Result deletReply(Integer id){
        replyService.deletReply(id);
        Result result = ResultUtil.success();
        return result;
    }
}
