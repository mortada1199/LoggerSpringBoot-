package com.testlog.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testlog.demo.controllers.User;
import com.testlog.demo.models.UserModel;

public interface UserRepo extends JpaRepository<UserModel,Long> {
    
}
