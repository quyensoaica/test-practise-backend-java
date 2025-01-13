package com.practise.test.repository;

import com.practise.test.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  FeedbackRepository  extends JpaRepository<Feedback,String> {
}
