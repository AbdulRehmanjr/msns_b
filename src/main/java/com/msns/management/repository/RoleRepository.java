package com.msns.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msns.management.model.Role;

public interface RoleRepository extends JpaRepository<Role,Integer>{
    
    Role findByRoleName(String name);
}
