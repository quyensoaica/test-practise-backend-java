package com.practise.test.repository;

import com.practise.test.entity.Exam;
import com.practise.test.entity.ExamSkillStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("ExamRepository")
public interface ExamRepository extends JpaRepository<Exam, String> {
    @Query("SELECT e FROM Exam e WHERE e.userId = :userId AND e.isDeleted = false ORDER BY e.createdAt DESC")
    List<Exam> findExamsByUserId(@Param("userId") String userId);
}
