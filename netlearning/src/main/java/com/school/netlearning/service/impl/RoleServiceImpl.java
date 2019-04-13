package com.school.netlearning.service.impl;

import com.school.netlearning.repository.RoleRepository;
import com.school.netlearning.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
}
