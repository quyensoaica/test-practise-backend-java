package com.practise.test.controller;

import com.practise.test.dto.AppData.AppResponseBase;
import com.practise.test.dto.category.CategoryDataRequest;
import com.practise.test.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get-category/{id}")
    public ResponseEntity<AppResponseBase> getCategoryById(@PathVariable String id) {
        AppResponseBase response = categoryService.getCategoryById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/get-category-of-skill/{skillId}")
    public ResponseEntity<AppResponseBase> getCategoryOfSkill(@PathVariable String skillId) {
        AppResponseBase response = categoryService.getCategoryOfSkill(skillId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/get-category-of-level/{levelId}")
    public ResponseEntity<AppResponseBase> getCategoryOfLevel(@PathVariable String levelId) {
        AppResponseBase response = categoryService.getCategoryOfLevel(levelId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/update-category/{id}")
    public ResponseEntity<AppResponseBase> updateCategory(@PathVariable String id, @RequestBody CategoryDataRequest categoryDataRequest, @RequestAttribute("userId") String userId) {
        AppResponseBase response = categoryService.updateCategory(id, categoryDataRequest, userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/create-category")
    public ResponseEntity<AppResponseBase> createCategory(@RequestBody CategoryDataRequest categoryDataRequest, @RequestAttribute("userId") String userId) {
        AppResponseBase response = categoryService.createNewCategory(categoryDataRequest, userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<AppResponseBase> deleteCategory(@PathVariable String id) {
        AppResponseBase response = categoryService.deleteCategory(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/restore-category/{id}")
    public ResponseEntity<AppResponseBase> restoreCategory(@PathVariable String id) {
        AppResponseBase response = categoryService.restoreCategory(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/active-category/{id}")
    public ResponseEntity<AppResponseBase> activeCategory(@PathVariable String id) {
        AppResponseBase response = categoryService.activeCategory(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/inactive-category/{id}")
    public ResponseEntity<AppResponseBase> inactiveCategory(@PathVariable String id) {
        AppResponseBase response = categoryService.inactiveCategory(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/delete-category-permanently/{id}")
    public ResponseEntity<AppResponseBase> deleteCategoryPermanently(@PathVariable String id) {
        AppResponseBase response = categoryService.deleteCategoryPermanently(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
