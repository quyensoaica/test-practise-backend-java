package com.practise.test.service;

import com.practise.test.dto.AppData.AppErrorBase;
import com.practise.test.dto.AppData.AppResponseBase;
import com.practise.test.dto.Feedback.IPaginationBase;
import com.practise.test.dto.Feedback.ISendFeedbackDTO;
import com.practise.test.dto.Feedback.PaginationResponseDTO;
import com.practise.test.entity.Feedback;
import com.practise.test.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public AppResponseBase sendFeedback(ISendFeedbackDTO feedback) {
        try {
            if (feedback == null || feedback.getFullName() == null || feedback.getEmail() == null || feedback.getFeedback() == null) {
                return new AppResponseBase(
                        HttpStatus.BAD_REQUEST.value(),
                        false,
                        "Thông tin nhập vào không hợp lệ",
                        null,
                        new AppErrorBase("Bad Request", "Thông tin nhập vào không hợp lệ")
                );
            }

            Feedback newFeedback = new Feedback();
            newFeedback.setId(UUID.randomUUID().toString());
            newFeedback.setFullName(feedback.getFullName());
            newFeedback.setEmail(feedback.getEmail());
            newFeedback.setPhoneNumber(feedback.getPhoneNumber());
            newFeedback.setFeedback(feedback.getFeedback());

            Feedback savedFeedback = feedbackRepository.save(newFeedback);

            return new AppResponseBase(
                    HttpStatus.OK.value(),
                    true,
                    "Phản hồi thành công",
                    savedFeedback,
                    null
            );
        } catch (Exception e) {
            // Log error message
            System.out.println("Error in FeedbackService - method sendFeedback() at " + System.currentTimeMillis() + " with message " + e.getMessage());
            return new AppResponseBase(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    false,
                    "Hệ thống đang gặp sự cố, vui lòng thử lại sau",
                    null,
                    new AppErrorBase("Internal Server Error", "Hệ thống đang gặp sự cố")
            );
        }
    }

    public AppResponseBase getAllFeedback(IPaginationBase pagination) {
        try {
            int page = pagination.getPage() < 1 ? 1 : pagination.getPage();
            int limit = pagination.getLimit() < 1 ? 10 : pagination.getLimit();

            // Tạo Pageable để phân trang
            Page<Feedback> feedbackPage = feedbackRepository.findAll(
                    PageRequest.of(page - 1, limit)
            );

            // Tạo response phân trang
            PaginationResponseDTO dataResponse = new PaginationResponseDTO(
                    feedbackPage.getContent(),
                    page,
                    limit,
                    feedbackPage.getTotalElements(),
                    (int) Math.ceil((double) feedbackPage.getTotalElements() / limit)
            );

            return new AppResponseBase(
                    HttpStatus.OK.value(),
                    true,
                    "Get list feedbacks successfully",
                    dataResponse,
                    null
            );
        } catch (Exception e) {
            // Log error message
            System.out.println("Error in FeedbackService - method getAllFeedback() with message " + e.getMessage());
            return new AppResponseBase(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    false,
                    "Internal Server Error",
                    null,
                    new AppErrorBase("Internal Server Error", e.getMessage())
            );
        }
    }
}

