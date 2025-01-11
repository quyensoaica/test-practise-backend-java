package com.practise.test.repository;

import com.practise.test.entity.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ExamQuestionRepository")
public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, String> {
    @Query("SELECT eq FROM ExamQuestion eq " +
            "JOIN FETCH eq.question q " +
            "JOIN FETCH q.skill s " +
//            "JOIN FETCH q.level l " +
//            "LEFT JOIN FETCH q.subQuestions sq " +
//            "LEFT JOIN FETCH sq.answers a " +
            "WHERE eq.exam.id = :examId " +
            "AND q.skillId = :skillId " +
            "ORDER BY q.level.id ASC")
    List<ExamQuestion> getListQuestion(
            @Param("examId") String examId,
            @Param("skillId") String skillId
    );

    ExamQuestion findByExamIdAndLevelId(String examId, String levelId);
}
