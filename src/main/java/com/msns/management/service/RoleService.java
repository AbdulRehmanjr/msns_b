package com.msns.management.service;

import java.util.List;

import com.msns.management.model.Role;

public interface RoleService {
    
    Role createRole(Role role);

    Role getByRoleName(String roleName);

    List<Role> getAllRoles();

}
