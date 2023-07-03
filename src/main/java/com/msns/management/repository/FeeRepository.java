package com.msns.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msns.management.model.Fee;

public interface FeeRepository  extends JpaRepository<Fee,Integer>{
    
}
