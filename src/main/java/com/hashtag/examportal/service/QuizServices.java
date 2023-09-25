package com.hashtag.examportal.service;

import com.hashtag.examportal.model.Category;
import com.hashtag.examportal.model.Quiz;

import java.util.List;

public interface QuizServices {
    Quiz addQuiz(String title,String description,Category category);

    List<Quiz> getQuizzes();

    Quiz getQuiz(Long quizId);

    Quiz updateQuiz(Quiz quiz);

    void deleteQuiz(Long quizId);

    // Extra methods
    List<Quiz> getQuizByCategory(Category category);
}
