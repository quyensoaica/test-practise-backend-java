package com.practise.test.controller;

import com.practise.test.dto.AppData.AppResponseBase;
import com.practise.test.dto.skill.UpdateSkillRequestDTO;
import com.practise.test.entity.Skill;
import com.practise.test.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/skills")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @GetMapping("/get-skills")
    public ResponseEntity<AppResponseBase> getAllSkills() {
        AppResponseBase response = skillService.getAllSkills();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/get-skill/{id}")
    public ResponseEntity<AppResponseBase> getSkillById(@PathVariable String id) {
        AppResponseBase response = skillService.getSkillById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/update-skill/{id}")
    public ResponseEntity<AppResponseBase> updateSkill(@RequestBody UpdateSkillRequestDTO dataUpdate, @PathVariable String id) {
        AppResponseBase response = skillService.updateSkill(id, dataUpdate);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
