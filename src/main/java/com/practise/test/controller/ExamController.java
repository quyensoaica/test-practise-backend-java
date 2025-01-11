package com.practise.test.controller;

import com.practise.test.dto.AppData.AppResponseBase;
import com.practise.test.dto.exam.SubmitSkillRequestDTO;
import com.practise.test.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exams")
public class ExamController {
    @Autowired
    private ExamService examService;

    @PostMapping("/start-new-exam")
    public ResponseEntity<AppResponseBase> startNewExam(@RequestAttribute("userId") String userId) {
        AppResponseBase response = examService.startNewExam(userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/current-exam")
    public ResponseEntity<AppResponseBase> getCurrentExam(@RequestAttribute("userId") String userId) {
        AppResponseBase response = examService.getCurrentExam(userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/participate-exam")
    public ResponseEntity<AppResponseBase> participateExam(@RequestAttribute("userId") String userId) {
        AppResponseBase response = examService.participateExam(userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/continue-exam")
    public ResponseEntity<AppResponseBase> continueExam(@RequestAttribute("userId") String userId) {
        AppResponseBase response = examService.continueExam(userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/submit-skill")
    public ResponseEntity<AppResponseBase> submitExam(@RequestAttribute("userId") String userId, @RequestBody SubmitSkillRequestDTO dataSubmit) {
        AppResponseBase response = examService.submitExam(userId, dataSubmit);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/get-score/{examId}")
    public ResponseEntity<AppResponseBase> getScore(@PathVariable String examId) {
        AppResponseBase response = examService.getScoreOfExam(examId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/get-result/{examId}")
    public ResponseEntity<AppResponseBase> getExamResult(@PathVariable String examId, @RequestParam String skillId) {
        AppResponseBase response = examService.getResultOfExam(examId, skillId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/my-exams")
    public ResponseEntity<AppResponseBase> getMyExams(@RequestAttribute("userId") String userId) {
        AppResponseBase response = examService.getMyExams(userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
