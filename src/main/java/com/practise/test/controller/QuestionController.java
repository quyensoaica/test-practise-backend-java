package com.practise.test.controller;

import com.practise.test.dto.AppData.AppResponseBase;
import com.practise.test.model.question.QuestionDetail;
import com.practise.test.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/get-all-questions")
    public ResponseEntity<AppResponseBase> getAllQuestion() {
        return null;
    }

    @PostMapping("/create-question")
    public ResponseEntity<AppResponseBase> createQuestion(@RequestBody List<QuestionDetail> listQuestions, @RequestAttribute("userId") String userId) {
        AppResponseBase response = questionService.createQuestion(listQuestions, userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/get-questions-by-category/{categoryId}")
    public ResponseEntity<AppResponseBase> getQuestionsByCategory(@PathVariable String categoryId, @RequestParam(required = false) String status) {
        AppResponseBase response = questionService.getAllQuestionByCategoryId(categoryId, status);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/get-questions-by-level/{levelId}")
    public ResponseEntity<AppResponseBase> getQuestionsByLevel(@PathVariable String levelId) {
        AppResponseBase response = questionService.getAllQuestionByLevelId(levelId, true);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/get-questions-by-skill/{skillId}")
    public ResponseEntity<AppResponseBase> getQuestionsBySkill(@PathVariable String skillId) {
        AppResponseBase response = questionService.getAllQuestionBySkillId(skillId, true);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/get-question-detail/{questionId}")
    public ResponseEntity<AppResponseBase> getQuestionDetail(@PathVariable String questionId) {
        AppResponseBase response = questionService.getQuestionDetail(questionId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

//    @PutMapping("/update-question/{questionId}")
//    public ResponseEntity<AppResponseBase> updateQuestion(@PathVariable String questionId, @RequestBody QuestionDetail questionDetail, @RequestAttribute("userId") String userId) {
//        AppResponseBase response = questionService.updateQuestion(questionId, questionDetail, userId);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }

    @DeleteMapping("/delete-question/{questionId}")
    public ResponseEntity<AppResponseBase> deleteQuestion(@PathVariable String questionId) {
        AppResponseBase response = questionService.deleteQuestion(questionId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/restore-question/{questionId}")
    public ResponseEntity<AppResponseBase> restoreQuestion(@PathVariable String questionId) {
        AppResponseBase response = questionService.restoreQuestion(questionId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/active-question/{questionId}")
    public ResponseEntity<AppResponseBase> activeQuestion(@PathVariable String questionId) {
        AppResponseBase response = questionService.activeQuestion(questionId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/inactive-question/{questionId}")
    public ResponseEntity<AppResponseBase> inactiveQuestion(@PathVariable String questionId) {
        AppResponseBase response = questionService.inactiveQuestion(questionId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/delete-question-permanently/{questionId}")
    public ResponseEntity<AppResponseBase> deleteQuestionPermanently(@PathVariable String questionId) {
        AppResponseBase response = questionService.deleteQuestionPermanently(questionId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
