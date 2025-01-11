package com.practise.test.repository;

import com.practise.test.dto.question.GroupedQuestionDTO;
import com.practise.test.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository("QuestionRepository")
public interface QuestionRepository extends JpaRepository<Question, String> {
    List<Question> findByIdIn(List<String> ids);
    List<Question> findByCategoryIdAndIsActive(String categoryId, boolean isActive);
    List<Question> findByLevelIdAndIsActive(String levelId, boolean isActive);
    List<Question> findBySkillIdAndIsActive(String skillId, boolean isActive);
    List<Question> findByCategoryIdAndIsDeleted(String categoryId, boolean isDeleted);
    List<Question> findByCategoryId(String categoryId);

    @Query("SELECT q.level.id AS levelId, q.id AS questionId FROM Question q ORDER BY q.level.id ASC")
    List<Object[]> findLevelIdAndQuestionIds();
}
