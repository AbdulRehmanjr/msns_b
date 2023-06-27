package com.msns.management.utils;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.msns.management.model.Role;
import com.msns.management.model.User;
import com.msns.management.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailService implements UserDetailsService{
        
 
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User response = this.userRepo.findByUserEmail(email);

        if (response == null) {
            log.error("No user with {} given email ", email);
            return null;
        }

        return new org.springframework.security.core.userdetails.User(response.getUserEmail(),
                response.getUserPassword(),getAuthorities(response.getRole()));
    }

    private List<GrantedAuthority> getAuthorities(Role role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
       
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        
        return authorities;
    }
}

