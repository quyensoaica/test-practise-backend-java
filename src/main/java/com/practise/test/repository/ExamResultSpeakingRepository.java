package com.practise.test.repository;

import com.practise.test.entity.ExamResultSpeaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ExamResultSpeakingRepository")
public interface ExamResultSpeakingRepository extends JpaRepository<ExamResultSpeaking, String> {
    List<ExamResultSpeaking> findByExamQuestionId(String examQuestionId);

    @Query("SELECT ers FROM ExamResultSpeaking ers " +
        "JOIN FETCH ers.examQuestion eq " +
        "WHERE eq.examId = :examId")
    List<ExamResultSpeaking> findByExamId(@Param("examId") String examId);
}
