package com.practise.test.controller;

import com.practise.test.dto.AppData.AppResponseBase;
import com.practise.test.dto.UserDTO;
import com.practise.test.entity.User;
import com.practise.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-users")
    public ResponseEntity<Map<String, Object>> getAllUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "") String search) {
        Map<String, Object> response = userService.getAllUsers(page, limit, search);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", 200);
        result.put("success", true);
        result.put("message", null);
        result.put("data", response);
        result.put("error", null);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/create-user")
    public Map<String, Object> createUser(@RequestBody User user) {
        return userService.createUser(user);  // Trả về trực tiếp kết quả từ service
    }

    @PutMapping("/update-user/{userId}")
    public Map<String, Object> updateUser(@PathVariable String userId, @RequestBody User data1) {
        return userService.updateUser(userId, data1);
    }

    @DeleteMapping("/delete-user/{userId}")
    public Map<String, Object> deleteUser(@PathVariable String userId) {
        return userService.deleteUser(userId);
    }
}

