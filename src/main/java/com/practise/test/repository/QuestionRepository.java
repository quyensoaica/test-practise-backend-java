package com.practise.test.repository;

import com.practise.test.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("QuestionRepository")
public interface QuestionRepository extends JpaRepository<Question, String> {
    List<Question> findByIdIn(List<String> ids);
    List<Question> findByCategoryIdAndIsActive(String categoryId, boolean isActive);
    List<Question> findByLevelIdAndIsActive(String levelId, boolean isActive);
    List<Question> findBySkillIdAndIsActive(String skillId, boolean isActive);
    List<Question> findByCategoryIdAndIsDeleted(String categoryId, boolean isDeleted);
    List<Question> findByCategoryId(String categoryId);
}
