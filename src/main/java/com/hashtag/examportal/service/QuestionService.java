package com.hashtag.examportal.service;

import com.hashtag.examportal.model.Question;
import com.hashtag.examportal.model.Quiz;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface QuestionService {

    Question addQuestion(MultipartFile imageFile,
                         String content,

                         String answer,
                         Quiz quiz) throws IOException;

//    Question addQuestionWithImage(MultipartFile imageF,
//                         String content,
//                         MultipartFile option1_image,
//                                  MultipartFile option2_image,
//                                  MultipartFile option3_image,
//                                  MultipartFile option4_image,
//                         String answer,
//                         Quiz quiz) throws IOException;
    List<Question> getQuestions();

    Question getQuestion(Long quesId);

    Question updateQuestion(Long questionId, Question question);

    void deleteQuestion(Long questionId);

    //Extra Methods
    List<Question> getQuestionsByQuiz(Quiz quiz);
}
