package com.practise.test.repository;

import com.practise.test.entity.SubQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("SubQuestionRepository")
public interface SubQuestionRepository extends JpaRepository<SubQuestion, String> {

}
