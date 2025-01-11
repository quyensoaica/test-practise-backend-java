package com.practise.test.repository;

import com.practise.test.entity.ExamResultListening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("ExamResultListeningRepository")
public interface ExamResultListeningRepository extends JpaRepository<ExamResultListening, String> {
}
