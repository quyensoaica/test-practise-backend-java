package com.practise.test.repository;

import com.practise.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    User findByUsername(String username);
}
