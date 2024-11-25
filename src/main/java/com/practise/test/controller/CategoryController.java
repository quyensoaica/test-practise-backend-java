package com.practise.test.controller;

import com.practise.test.dto.AppData.AppResponseBase;
import com.practise.test.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get-categories")
    public ResponseEntity<AppResponseBase> getAllCategories() {
        AppResponseBase response = categoryService.getAllCategories();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
