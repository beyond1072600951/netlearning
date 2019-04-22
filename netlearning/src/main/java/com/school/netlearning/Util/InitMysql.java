package com.school.netlearning.Util;

import com.school.netlearning.pojo.Role;
import com.school.netlearning.pojo.User;
import com.school.netlearning.repository.RoleRepository;
import com.school.netlearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Component
public class InitMysql implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<User> userList = userRepository.findAll();
        List<Role> roleListList = roleRepository.findAll();
        if (0 == userList.size() && 0 == roleListList.size()) {
            Role role1 = new Role();
            role1.setId(1);
            role1.setAuthority("ROLE_ADMIN");
            role1.setName("管理员");
            role1.setState((byte) 0);
            role1.setCreateDate(new Date());
            role1.setUpdateDate(new Date());
//            Role role2 = new Role();
//            role2.setId(2);
//            role2.setAuthority("ROLE_CLASSSTEACHER");
//            role2.setName("班主任");
//            role2.setState((byte) 1);
//            role2.setCreateDate(new Date());
//            role2.setUpdateDate(new Date());
//            Role role3 = new Role();
//            role3.setId(3);
//            role3.setAuthority("ROLE_COURSETEACHER");
//            role3.setName("任课老师");
//            role3.setState((byte) 0);
//            role3.setCreateDate(new Date());
//            role3.setUpdateDate(new Date());
            Role role4 = new Role();
            role4.setId(4);
            role4.setAuthority("ROLE_STUDENT");
            role4.setName("学生");
            role4.setState((byte) 0);
            role4.setCreateDate(new Date());
            role4.setUpdateDate(new Date());
            ArrayList<Role> roles = new ArrayList<>();
            roles.add(role1);
//            roles.add(role2);
//            roles.add(role3);
            roles.add(role4);
            roleRepository.saveAll(roles);

            User user = new User();
            user.setId(1);
            user.setUserName("admin");
            user.setPassWord(bCryptPasswordEncoder.encode("admin"));
            user.setState((Integer) 0);
            user.setUserName("管理员");
            ArrayList<Role> arrayList = new ArrayList<>();
            arrayList.add(role1);
            user.setRoleList(arrayList);
            user.setCreateDate(new Date());
            userRepository.save(user);
        }
    }
}
