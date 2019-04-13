package com.school.netlearning.repository;

import com.school.netlearning.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findFirstByIdAndState(Long id, Byte state);
}
