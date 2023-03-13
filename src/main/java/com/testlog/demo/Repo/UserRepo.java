package com.testlog.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testlog.demo.models.User;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByName(String name);
}
