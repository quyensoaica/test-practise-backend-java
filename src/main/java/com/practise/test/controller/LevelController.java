package com.practise.test.controller;

import com.practise.test.dto.AppData.AppResponseBase;
import com.practise.test.dto.level.UpdateLevelRequestDTO;
import com.practise.test.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/levels")
public class LevelController {
    @Autowired
    private LevelService levelService;

    @GetMapping("/get-levels")
    public ResponseEntity<AppResponseBase> getAllLevels() {
        AppResponseBase response = levelService.getAllLevels();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/get-level/{id}")
    public ResponseEntity<AppResponseBase> getLevelById(@PathVariable String id) {
        AppResponseBase response = levelService.getLevelById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/update-level/{id}")
    public ResponseEntity<AppResponseBase> updateLevel(@RequestBody UpdateLevelRequestDTO dataUpdate, @PathVariable String id) {
        AppResponseBase response = levelService.updateLevel(id, dataUpdate);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/get-level-of-skill/{skillId}")
    public ResponseEntity<AppResponseBase> getLevelOfSkill(@PathVariable String skillId) {
        AppResponseBase response = levelService.getlistLevelOfSkillId(skillId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
