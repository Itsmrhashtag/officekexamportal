package com.hashtag.examportal.repo;

import com.hashtag.examportal.model.Category;
import com.hashtag.examportal.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByCategory(Category category);
}
