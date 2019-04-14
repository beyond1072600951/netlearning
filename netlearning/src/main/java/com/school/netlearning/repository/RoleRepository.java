package com.school.netlearning.repository;

import com.school.netlearning.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findFirstByIdAndState(Integer id, Byte state);
}
