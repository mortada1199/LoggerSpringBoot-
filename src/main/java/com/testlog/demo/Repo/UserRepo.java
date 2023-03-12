package com.testlog.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testlog.demo.models.User;

public interface UserRepo extends JpaRepository<User,Long> {
    
}
