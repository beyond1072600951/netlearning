package com.school.netlearning.service.impl;

import com.school.netlearning.mapper.UserMapper;
import com.school.netlearning.pojo.User;
import com.school.netlearning.repository.RoleRepository;
import com.school.netlearning.repository.UserRepository;
import com.school.netlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override

    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public User addUser(User user) {
        user.setPassWord(bCryptPasswordEncoder.encode(user.getPassWord()));
        User save = userRepository.save(user);
        return save;
    }

    @Override
    public User updataIsPost(Integer id, String ispost) {
        Optional<User> byId = userRepository.findById(id);
        User user = byId.get();
        user.setIspost(ispost);
        User save = userRepository.save(user);
        return save;
    }

    @Override
    public User updataIsReply(Integer id, String isreply) {
        Optional<User> byId = userRepository.findById(id);
        User user = byId.get();
        user.setIsreply(isreply);
        User save = userRepository.save(user);
        return save;
    }

    @Override
    public void deletUserById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findByNameContaining(String userName) {
        return userRepository.findByNameContaining(userName);
    }

    @Override
    public User findUserById(Integer id) {
        return userRepository.findUserById(id);
    }

    @Override
    public List<User> testFind() {
        List<User> userList = userMapper.testFind();
        return userList;
    }
}
