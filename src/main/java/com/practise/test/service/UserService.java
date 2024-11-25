package com.practise.test.service;

import com.practise.test.dto.UserDTO;
import com.practise.test.entity.User;
import com.practise.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) {
//        User user = UserMapper.mapToUser(userDTO);
//        User newUser = userRepository.save(user);
//        return UserMapper.mapToUserDTO(newUser);
        return userDTO;
    }

    public User getUserById(Long id) {
        return null;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
