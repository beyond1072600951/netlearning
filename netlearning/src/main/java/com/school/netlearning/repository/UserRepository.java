package com.school.netlearning.repository;

import com.school.netlearning.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据登录名查询用户
     *
     * @param loginName 登录账号
     * @return
     */
    User findByLoginName(String loginName);

    /**
     * 查询所有学生
     *
     * @return
     */
    List<User> findByState(Byte state);

//    @Modifying
//    @Query("UPDATE User SET userName = :userName, sex = :sex, age = :age WHERE id = :id")
//    @Query("UPDATE user u SET u.user_name = :userName, u.sex = :sex, u.age = :age WHERE u.id = :id")
//    void updateUser(User user);
}
