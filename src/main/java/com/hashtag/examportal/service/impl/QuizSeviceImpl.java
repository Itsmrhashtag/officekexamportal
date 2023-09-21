package com.hashtag.examportal.service.impl;

import com.hashtag.examportal.model.Category;
import com.hashtag.examportal.model.Quiz;
import com.hashtag.examportal.repo.CategoryRepository;
import com.hashtag.examportal.repo.QuizRepository;
import com.hashtag.examportal.service.QuizServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizSeviceImpl implements QuizServices {
    @Autowired
    public QuizRepository quizRepository;

    @Autowired
    public CategoryRepository categoryRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {
//
//        quiz.setCategory(quiz.getCategory().setNumOfQuizzes(quiz.getCategory().getNumOfQuizzes()+1));

        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> getQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        return quizRepository.findById(quizId).isPresent() ? quizRepository.findById(quizId).get() : null;
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public void deleteQuiz(Long quizId) {
        quizRepository.deleteById(quizId);
    }

    @Override
    public List<Quiz> getQuizByCategory(Category category) {
        return quizRepository.findByCategory(category);
    }

}
