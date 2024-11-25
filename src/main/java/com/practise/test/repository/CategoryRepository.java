package com.practise.test.repository;

import com.practise.test.entity.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, String> {
    List<Category> findByLevelId(String levelId, Sort sort);
    List<Category> findBySkillId(String skillId, Sort sort);
}
