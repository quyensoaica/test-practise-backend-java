package com.practise.test.repository;

import com.practise.test.entity.ExamSkillStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ExamSkillStatusRepository")
public interface ExamSkillStatusRepository extends JpaRepository<ExamSkillStatus, String> {
    @Query("""
        SELECT ess
        FROM ExamSkillStatus ess
        JOIN FETCH ess.skill s
        WHERE ess.examId = :examId
        ORDER BY ess.order ASC
    """)
    List<ExamSkillStatus> findSkillStatusesByExamId(@Param("examId") String examId);

    ExamSkillStatus findByExamIdAndSkillId(String examId, String skillId);
}
