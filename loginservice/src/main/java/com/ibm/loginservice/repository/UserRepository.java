package com.ibm.loginservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.loginservice.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);
}
