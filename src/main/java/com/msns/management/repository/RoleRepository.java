package com.msns.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msns.management.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    /**
     * The function findByRoleName searches for a role by its name and returns the
     * corresponding Role
     * object.
     * 
     * @param name The name of the role you want to find.
     * @return The method findByRoleName is returning an object of type Role.
     */
    Role findByRoleName(String name);
}
