package com.practise.test.controller;

import com.practise.test.dto.AppData.AppResponseBase;
import com.practise.test.dto.Feedback.IPaginationBase;
import com.practise.test.dto.Feedback.ISendFeedbackDTO;
import com.practise.test.dto.Feedback.PaginationResponseDTO;
import com.practise.test.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedbacks")

public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/get-feedbacks")
    public AppResponseBase getAllFeedbacks(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        IPaginationBase paginationData = new IPaginationBase(page, limit);

        AppResponseBase response = feedbackService.getAllFeedback(paginationData);

        return response;
    }

    @PostMapping("/send-feedback")
    public ResponseEntity<AppResponseBase> sendFeedback(@RequestBody ISendFeedbackDTO sendFeedbackDTO) {
        AppResponseBase response = feedbackService.sendFeedback(sendFeedbackDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
