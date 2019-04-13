package com.school.netlearning.service.impl;

import com.school.netlearning.pojo.Role;
import com.school.netlearning.pojo.User;
import com.school.netlearning.repository.RoleRepository;
import com.school.netlearning.repository.UserRepository;
import com.school.netlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User register(User user, Long roleId) {
        Role role = roleRepository.findFirstByIdAndState(roleId, (byte) 0);
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        user.setRoleList(roleList);
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) throws Exception {
        Optional<User> optional = userRepository.findById(id);
        return optional.get();
    }

    @Override
    public List<User> findAll(String authority) throws Exception {
        List<User> userList = userRepository.findByState((byte) 0);
        return userList.stream().filter(user -> {
            List<Role> roleList = user.getRoleList();
            for (Role role : roleList) {
                return authority.equals(role.getAuthority());
            }
            return false;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public User updateUser(User user) throws Exception {
        Optional<User> optional = userRepository.findById(user.getId());
        User u = optional.get();
        if (null != user.getUserName()) {
            u.setUserName(user.getUserName());
        }
        if (null != user.getPassWord()) {
            u.setPassWord(user.getPassWord());
        }
        u.setSex(user.getSex());
        u.setAge(user.getAge());
        u.setPhone(user.getPhone());
        u.setUpdateDate(new Date());
        userRepository.save(u);
        return null;
    }
}
