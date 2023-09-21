package com.hashtag.examportal.controller;

import com.hashtag.examportal.model.Question;
import com.hashtag.examportal.model.Quiz;
import com.hashtag.examportal.service.QuestionService;
import com.hashtag.examportal.service.QuizServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizServices quizServices;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addQuestion(@RequestParam("image") MultipartFile image,
                                         @RequestParam("content") String content,
                                         @RequestParam("answer") String answer,
                                         @RequestParam("quiz")Quiz quiz) throws IOException {
        return ResponseEntity.ok(questionService.addQuestion(image,content,answer,quiz));
    }
// MultipartFile imageFile, String content, MultipartFile option1_image, MultipartFile option2_image, MultipartFile option3_image, MultipartFile option4_image, String answer, Quiz quiz
//
//    @PostMapping("/image/")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public ResponseEntity<?> addQuestionWithImage(@RequestParam("image") MultipartFile image,
//                                         @RequestParam("content") String content,
//                                         @RequestParam("option1") MultipartFile option1_image,
//                                         @RequestParam("option2") MultipartFile option2_image,
//                                         @RequestParam("option3") MultipartFile option3_image,
//                                         @RequestParam("option4") MultipartFile option4_image,
//                                         @RequestParam("answer") String answer,
//                                         @RequestParam("quiz")Quiz quiz) throws IOException {
//        return ResponseEntity.ok(questionService.addQuestionWithImage(image,content,option1_image,option2_image,option3_image,option4_image,answer,quiz));
//    }

    @GetMapping("/{questionId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'NORMAL')")
    public ResponseEntity<?> getQuestion(@PathVariable Long questionId) {
        Question question = questionService.getQuestion(questionId);


        return ResponseEntity.ok(question);
    }
    @GetMapping("/image/{questionId}")
//    @PreAuthorize("hasAnyAuthority('ADMIN', 'NORMAL')")
    public ResponseEntity<?> getQuestionwithImage(@PathVariable Long questionId) {
        Question question = questionService.getQuestion(questionId);
//        if(question!=null && question.getImage()!=null){
//
//            HttpHeaders headers=new HttpHeaders();
//            headers.setContentType(MediaType.IMAGE_JPEG);
//
//            return new ResponseEntity<>(question.getImage(),headers,HttpStatus.OK);
//
////            return ResponseEntity.ok(question);
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Question with id : " + String.valueOf(questionId) + ", doesn't exists");
//    }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(question.getImage());
    }
    @GetMapping(value = "/", params = "quizId")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'NORMAL')")
    public ResponseEntity<?> getQuestionsByQuiz(@RequestParam Long quizId) {
        Quiz quiz = quizServices.getQuiz(quizId);
        Set<Question> questions = quiz.getQuestions();
        return ResponseEntity.ok(questions);
    }

    @PutMapping("/{questionId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateQuestion(@PathVariable Long questionId, @RequestBody Question question) {
        if (questionService.getQuestion(questionId) != null) {
            return ResponseEntity.ok(questionService.updateQuestion(questionId, question));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Question with id : " + String.valueOf(questionId) + ", doesn't exists");
    }

    @DeleteMapping("/{questionId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.ok(true);
    }

}
