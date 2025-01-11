package com.practise.test.repository;

import com.practise.test.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("AnswerRepository")
public interface AnswerRepository extends JpaRepository<Answer, String> {
}
