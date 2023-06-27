package com.msns.management.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msns.management.model.Role;
import com.msns.management.repository.RoleRepository;
import com.msns.management.service.RoleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoleServiceImp implements RoleService{

    @Autowired
    private RoleRepository roleRepo;


    @Override
    public Role createRole(Role role) {

        try {
            Role response = this.roleRepo.save(role);    

            return response;
        } catch (Exception e) {
            log.error("Error while saving role: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Role getByRoleName(String roleName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByRoleName'");
    }

    @Override
    public List<Role> getAllRoles() {
       
        return this.roleRepo.findAll();
    }
    
}
