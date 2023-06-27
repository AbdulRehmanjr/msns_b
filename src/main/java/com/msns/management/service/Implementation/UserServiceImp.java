package com.msns.management.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.msns.management.model.Role;
import com.msns.management.model.User;
import com.msns.management.repository.RoleRepository;
import com.msns.management.repository.UserRepository;
import com.msns.management.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImp implements UserService {

    final String DEFAULT_USER = "TEACHER";
    final String ADMIN = "ADMIN";

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User saveUser(User user) {

        log.info("inserting new user in database");

        Role role = this.roleRepo.findByRoleName(DEFAULT_USER);

        if (role == null) {
            log.info("No role found with given name {}", DEFAULT_USER);
            return null;
        }

        user.setRole(role);
        user.setUserPassword(encoder.encode(user.getUserPassword()));

        User saved = this.userRepo.save(user);

        return saved;
    }

    @Override
    public User saveAdmin(User user) {
        log.info("inserting new admin in database");

        Role role = this.roleRepo.findByRoleName(ADMIN);

        if (role == null) {
            log.info("No role found with given name {}", ADMIN);
            return null;
        }

        user.setRole(role);
        user.setUserPassword(encoder.encode(user.getUserPassword()));
        
        User saved = null;

        try {
            saved = this.userRepo.save(user);
            return saved;
        } catch (Exception e) {
            log.error("Error cause: {}, Message: {}", e.getCause(), e.getMessage());
            return null;
        }

    }

    @Override
    public User getUserById(int userId) {

        log.info("fetching user with id {}", userId);
        try {
            User user = this.userRepo.findById(userId).get();
            return user;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public User getUserByIdEdit(int userId) {

        log.info("fetching user with id {}", userId);

        User user = null;

        try {
            user = this.userRepo.findById(userId).get();
        } catch (Exception e) {
            log.error("Error {}", e.getMessage());
        }

        return user;
    }

    @Override
    @Deprecated
    public List<User> getAllUsersByUserNameLike(String userNameLike) {

        throw new UnsupportedOperationException("Unimplemented method 'getAllUsersByUserNameLike'");
    }

    @Override
    @Deprecated
    public List<User> getAllUsersByUserName(String userName) {

        throw new UnsupportedOperationException("Unimplemented method 'getAllUsersByUserName'");
    }

    @Override
    public void deleteUser(int id) {
        log.info("DELETE with id :{}", id);
        this.userRepo.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Getting all users");
        return this.userRepo.findAll();
    }

    @Override
    @Deprecated
    public User getUserByEmail(String email) {
        
        User user = null;

        try {
            user = this.userRepo.findByUserEmail(email);
        } catch (Exception e) {
            log.error("Error {}", e.getMessage());
        }

        return user;
    }

    @Override
    @Deprecated
    public User updateUserRole(int userId) {

        throw new UnsupportedOperationException("Unimplemented method");

    }

    @Override
    public User updateUser(User user) {
        log.info("UPDATE : user");

        return this.userRepo.save(user);
    }

    @Override
    @Deprecated
    public void resetPassword(int otp, String email) {
        throw new UnsupportedOperationException("Unimplemented method");
    }

    @Override
    public User updatePassword(User user) {
        log.info("savng new password");

        user.setUserPassword(encoder.encode(user.getUserPassword()));
        return this.userRepo.save(user);
    }

}
