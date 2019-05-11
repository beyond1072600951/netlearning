package com.school.netlearning.controller;

import com.school.netlearning.Util.CurrentUserUtil;
import com.school.netlearning.pojo.User;
import com.school.netlearning.result.Result;
import com.school.netlearning.result.ResultUtil;
import com.school.netlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户列表操作
 *
 * @author KILLTHUNDER
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(value = "/userList")
    public Result findAll() {
        List<User> userList = userService.findAll();
        Result result = ResultUtil.success(userList);
        return result;
    }

    @PostMapping(value = "/saveUser")
    public Result saveUser(User user) {
        User u = userService.addUser(user);
        Result result = ResultUtil.success();
        return result;
    }

    @PostMapping(value = "/updataIsPost")
    public Result updataIsPost(Integer id, String ispost) {
        userService.updataIsPost(id, ispost);
        Result result = ResultUtil.success();
        return result;
    }

    @PostMapping(value = "/updataIsReply")
    public Result updataIsReply(Integer id, String isreply) {
        userService.updataIsReply(id, isreply);
        Result result = ResultUtil.success();
        return result;
    }

    @GetMapping(value = "/deletUser")
    public Result deletUser(Integer id) {
        userService.deletUserById(id);
        Result result = ResultUtil.success();
        return result;
    }

    @GetMapping(value = "/findByNameContaining")
    public Result findByNameContaining(@RequestParam(value = "userName") String userName) {
        List<User> byNameContaining = userService.findByNameContaining(userName);
        Result result = ResultUtil.success(byNameContaining);
        return result;
    }

    @GetMapping(value = "findUserId")
    public Result findUserId(String name){
        User byName = userService.findByName(name);
        Result result = ResultUtil.success(byName);
        return result;
    }
    /**
     * 根据ID查询用户详情，学生只能查看自己的信息
     *
     * @param request
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/findById")
    public Result findById(HttpServletRequest request, @RequestParam(value = "i", required = false) Integer id) throws Exception {
        if (CurrentUserUtil.isRole(request, "STUDENT") || null == id) {
            id = CurrentUserUtil.getUserId(request);
        }
        User user = userService.findUserById(id);
        return ResultUtil.success(user);
    }

//    /**
//     * 测试mybatis
//     *
//     * @return
//     * @throws Exception
//     */
//    @GetMapping(value = "/testFind")
//    public Result testFind() throws Exception {
//        List<User> userList = userService.testFind();
//        return ResultUtil.success(userList);
//    }
}
