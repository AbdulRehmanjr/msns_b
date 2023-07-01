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
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepository roleRepo;

    /**
     * The function creates a new role and saves it in the role repository,
     * returning the saved role or
     * null if there was an error.
     * 
     * @param role The "role" parameter is an object of type Role that represents
     *             the role to be
     *             created.
     * @return The method is returning an instance of the Role class.
     */
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

    /**
     * The function getByRoleName is not implemented and throws an
     * UnsupportedOperationException.
     * 
     * @param roleName The roleName parameter is a String that represents the name
     *                 of the role you want
     *                 to retrieve.
     * @return The method is throwing an UnsupportedOperationException, so nothing
     *         is being returned.
     */
    @Override
    public Role getByRoleName(String roleName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByRoleName'");
    }

    /**
     * The function getAllRoles() returns a list of all roles from the role
     * repository.
     * 
     * @return The method is returning a List of Role objects.
     */
    @Override
    public List<Role> getAllRoles() {

        return this.roleRepo.findAll();
    }

}
