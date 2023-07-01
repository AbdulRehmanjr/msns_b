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

    /**
     * This function saves a new user in the database with a default role and
     * encoded password.
     * 
     * @param user The user object that needs to be saved in the database.
     * @return The method is returning the saved User object.
     */
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

    /**
     * This function saves a new admin user in the database, encrypts the password,
     * assigns the admin
     * role, and returns the saved user.
     * 
     * @param user The user object contains the details of the admin user that needs
     *             to be saved in the
     *             database.
     * @return The method is returning a User object.
     */
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

    /**
     * The function retrieves a user from the user repository based on the provided
     * user ID.
     * 
     * @param userId The parameter `userId` is an integer representing the unique
     *               identifier of the
     *               user that we want to fetch.
     * @return The method is returning a User object.
     */
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

    /**
     * The function retrieves a user from the database based on their ID and returns
     * it.
     * 
     * @param userId The userId parameter is an integer that represents the unique
     *               identifier of the
     *               user that we want to fetch from the database.
     * @return The method is returning a User object.
     */
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

    /**
     * The function getAllUsersByUserNameLike is deprecated and throws an
     * UnsupportedOperationException.
     * 
     * @param userNameLike The parameter "userNameLike" is a String that represents
     *                     a partial or
     *                     complete username that is used to search for users.
     * @return In this code snippet, an UnsupportedOperationException is being
     *         thrown with the message
     *         "Unimplemented method 'getAllUsersByUserNameLike'". This means that
     *         the method is not
     *         implemented and does not return anything.
     */
    @Override
    @Deprecated
    public List<User> getAllUsersByUserNameLike(String userNameLike) {

        throw new UnsupportedOperationException("Unimplemented method 'getAllUsersByUserNameLike'");
    }

    /**
     * The function getAllUsersByUserName is deprecated and throws an
     * UnsupportedOperationException.
     * 
     * @param userName The parameter "userName" is a String that represents the
     *                 username of a user.
     * @return The method is not implemented and it throws an
     *         UnsupportedOperationException. Therefore,
     *         it does not return anything.
     */
    @Override
    @Deprecated
    public List<User> getAllUsersByUserName(String userName) {

        throw new UnsupportedOperationException("Unimplemented method 'getAllUsersByUserName'");
    }

    /**
     * The deleteUser function deletes a user from the database based on their id.
     * 
     * @param id The id parameter represents the unique identifier of the user that
     *           needs to be deleted
     *           from the system.
     */
    @Override
    public void deleteUser(int id) {
        log.info("DELETE with id :{}", id);
        this.userRepo.deleteById(id);
    }

    /**
     * The function getAllUsers() retrieves all users from the user repository and
     * returns them as a
     * list.
     * 
     * @return The getAllUsers() method is returning a List of User objects.
     */
    @Override
    public List<User> getAllUsers() {
        log.info("Getting all users");
        return this.userRepo.findAll();
    }

    /**
     * This function retrieves a user from the user repository based on their email
     * address.
     * 
     * @param email The parameter "email" is a String that represents the email
     *              address of the user.
     * @return The method is returning a User object.
     */
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

    /**
     * The function updateUserRole is marked as deprecated and throws an
     * UnsupportedOperationException.
     * 
     * @param userId The userId parameter is an integer that represents the unique
     *               identifier of the
     *               user whose role needs to be updated.
     * @return The method is not returning anything. It is throwing an
     *         UnsupportedOperationException.
     */
    @Override
    @Deprecated
    public User updateUserRole(int userId) {

        throw new UnsupportedOperationException("Unimplemented method");

    }

    /**
     * The function updates a user in the database and returns the updated user.
     * 
     * @param user The user object that needs to be updated.
     * @return The method is returning an updated User object.
     */
    @Override
    public User updateUser(User user) {
        log.info("UPDATE : user");

        return this.userRepo.save(user);
    }

    /**
     * The function "resetPassword" is marked as deprecated and throws an exception
     * indicating that it
     * is not implemented.
     * 
     * @param otp   The otp parameter stands for "One-Time Password". It is a
     *              temporary password that is
     *              typically used for authentication or password reset purposes.
     * @param email The email parameter is a string that represents the email
     *              address of the user for
     *              whom the password needs to be reset.
     */
    @Override
    @Deprecated
    public void resetPassword(int otp, String email) {
        throw new UnsupportedOperationException("Unimplemented method");
    }

    /**
     * The function updates the password of a user by encoding it and saving it in
     * the database.
     * 
     * @param user The "user" parameter is an object of the User class. It
     *             represents the user whose
     *             password needs to be updated.
     * @return The updated User object is being returned.
     */
    @Override
    public User updatePassword(User user) {
        log.info("savng new password");

        user.setUserPassword(encoder.encode(user.getUserPassword()));
        return this.userRepo.save(user);
    }

}
