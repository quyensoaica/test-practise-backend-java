package com.practise.test.repository;

import com.practise.test.entity.ExamResultReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("ExamResultReadingRepository")
public interface ExamResultReadingRepository extends JpaRepository<ExamResultReading, String> {
}
