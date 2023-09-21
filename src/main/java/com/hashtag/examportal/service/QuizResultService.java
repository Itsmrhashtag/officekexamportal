package com.hashtag.examportal.service;

import com.hashtag.examportal.model.QuizResult;

import java.util.List;

public interface QuizResultService {

    QuizResult addQuizResult(QuizResult quizResult);

    List<QuizResult> getQuizResults();

    List<QuizResult> getQuizResultsByUser(Long userId);
}
