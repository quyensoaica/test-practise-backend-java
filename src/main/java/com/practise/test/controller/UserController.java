package com.practise.test.controller;

import com.practise.test.dto.AppData.AppResponseBase;
import com.practise.test.dto.UserDTO;
import com.practise.test.entity.User;
import com.practise.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/users")
public class UserController {
//    private UserService userService;
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO user = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                user
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<AppResponseBase> getListUser() {

        List<User> listUser = userService.getAllUser();

        return ResponseEntity.status(HttpStatus.OK).body(
                new AppResponseBase(
                    200,
                    true,
                    "Oke",
                    listUser,
                    null
                )
        );
    }
}
