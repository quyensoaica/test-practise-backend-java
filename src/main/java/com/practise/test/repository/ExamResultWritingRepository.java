package com.practise.test.repository;

import com.practise.test.entity.ExamResultWriting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamResultWritingRepository extends JpaRepository<ExamResultWriting, String> {
}
