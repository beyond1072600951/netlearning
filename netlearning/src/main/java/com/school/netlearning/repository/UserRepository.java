package com.school.netlearning.repository;

import com.school.netlearning.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 根据登录名查询用户
     *
     * @param userName 登录账号
     * @return
     */
    User findByUserName(String userName);

    /**
     * 查询用户列表
     * @return
     */
    List<User> findAll();

    User findUserById(Integer id);

    List<User> findByNameContaining(String userName);

    User findByName(String name);

    List<User> findAllByUserName(String UserName);


//    @Modifying
//    @Query("UPDATE User SET userName = :userName, sex = :sex, age = :age WHERE id = :id")
//    @Query("UPDATE user u SET u.user_name = :userName, u.sex = :sex, u.age = :age WHERE u.id = :id")
//    void updateUser(User user);
}
