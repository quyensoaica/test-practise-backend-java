package com.practise.test.repository;

import com.practise.test.entity.ExamResultSpeaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ExamResultSpeakingRepository")
public interface ExamResultSpeakingRepository extends JpaRepository<ExamResultSpeaking, String> {
    List<ExamResultSpeaking> findByExamQuestionId(String examQuestionId);
}
