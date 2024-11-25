package com.practise.test.repository;

import com.practise.test.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("SkillRepository")
public interface SkillRepository extends JpaRepository<Skill, String> {
}
