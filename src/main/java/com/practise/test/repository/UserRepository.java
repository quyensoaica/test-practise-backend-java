package com.practise.test.repository;

import com.practise.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    User findByEmail(String email);
    User findByUsername(String username);
}
