package com.practise.test.repository;

import com.practise.test.entity.Level;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("levelRepository")
public interface LevelRepository extends JpaRepository<Level, String> {
    List<Level> findBySkillId(String levelId, Sort sort);
}
