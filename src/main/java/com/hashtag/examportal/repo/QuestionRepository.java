package com.hashtag.examportal.repo;

import com.hashtag.examportal.model.Question;
import com.hashtag.examportal.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByQuiz(Quiz quiz);
}
