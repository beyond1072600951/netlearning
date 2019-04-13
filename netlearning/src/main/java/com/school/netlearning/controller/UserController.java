package com.school.netlearning.controller;

import com.school.netlearning.Util.CurrentUserUtil;
import com.school.netlearning.pojo.User;
import com.school.netlearning.result.Result;
import com.school.netlearning.result.ResultUtil;
import com.school.netlearning.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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

/*    @PostMapping(value = "/reg")
    public Result register(@RequestParam(value = "ln") String loginName, @RequestParam(value = "pd") String passWord, @RequestParam(value = "un") String userName) throws Exception {
        User user = new User();
        user.setLoginName(loginName);
        user.setUserName(userName);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        //密码加密
        user.setPassWord(bCryptPasswordEncoder.encode(passWord));
        User register = userService.register(user);
        return ResultUtil.success(register);
    }*/

    /**
     * 根据ID查询用户详情，学生只能查看自己的信息
     *
     * @param request
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/findById")
    public Result findById(HttpServletRequest request, @RequestParam(value = "i", required = false) Long id) throws Exception {
        if (CurrentUserUtil.isRole(request, "STUDENT") || null == id) {
            id = CurrentUserUtil.getUserId(request);
        }
        User user = userService.findById(id);
        return ResultUtil.success(user);
    }

    /**
     * 获取所有的学生
     *
     * @param request
     * @param authority ROLE_COURSETEACHER:任课教师；ROLE_STUDENT：学生
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/findAll")
    public Result findAll(HttpServletRequest request, @RequestParam(value = "aut") String authority) throws Exception {
        List<User> userList = userService.findAll(authority);
        return ResultUtil.success(userList);
    }

    /**
     * 管理员更新或注册用户
     *
     * @param request
     * @param userId
     * @param userName
     * @param sex
     * @param age
     * @param loginName
     * @param passWord
     * @param roleId
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/addOrUp")
    public Result updateUser(HttpServletRequest request, @RequestParam(value = "i", required = false) Long userId, @RequestParam(value = "un") String userName,
                             @RequestParam(value = "s", required = false) Byte sex, @RequestParam(value = "a") Byte age,
                             @RequestParam(value = "ln", required = false) String loginName, @RequestParam(value = "pd", required = false) String passWord,
                             @RequestParam(value = "ro", required = false) Long roleId) throws Exception {
        if (null == userId && null != roleId && null != passWord) {
            User user = new User();
            user.setLoginName(loginName);
            user.setUserName(userName);
            user.setSex(sex);
            user.setAge(age);
            user.setPassWord(bCryptPasswordEncoder.encode(passWord));
            user.setState((byte) 0);
            user.setCreateDate(new Date());
            user.setUpdateDate(new Date());
            user.setLastPasswordResetDate(new Date());
            User u = userService.register(user, roleId);
            return ResultUtil.success(u);
        } else if (null != userId) {
            User user = new User();
            user.setId(userId);
            user.setUserName(userName);
            user.setSex(sex);
            user.setAge(age);
            userService.updateUser(user);
        }
        return ResultUtil.success(null);
    }

    /**
     * 学生或教师修改自己的基本信息
     *
     * @param request
     * @param userName
     * @param password
     * @param sex
     * @param age
     * @param phone
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/upInfo")
    public Result updateMyInfo(HttpServletRequest request, @RequestParam(value = "un") String userName, @RequestParam(value = "pd", required = false) String passWord,
                               @RequestParam(value = "s", required = false) Byte sex, @RequestParam(value = "a", required = false) Byte age,
                               @RequestParam(value = "ph", required = false) String phone) throws Exception {
        /*if (!CurrentUserUtil.isRole(request, "ADMIN") && !CurrentUserUtil.getUserId(request).equals(userId)) {
            return ResultUtil.fail("你没有此次操作权限！");
        }*/
        User user = new User();
        Long userId = CurrentUserUtil.getUserId(request);
        user.setId(userId);
        user.setUserName(userName);
        if (!StringUtils.isEmpty(passWord)) {
            user.setPassWord(bCryptPasswordEncoder.encode(passWord));
        }
        user.setSex(sex);
        user.setAge(age);
        user.setPhone(phone);
        userService.updateUser(user);
        return ResultUtil.success(null);
    }
}
