package com.hashtag.examportal.controller;

import com.hashtag.examportal.model.Category;
import com.hashtag.examportal.model.Quiz;
import com.hashtag.examportal.service.CategoryService;
import com.hashtag.examportal.service.QuizServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizServices quizServices;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addQuiz(@RequestParam String title,@RequestParam String description,@RequestParam Category category) {
        return ResponseEntity.ok(quizServices.addQuiz(title,description,category));
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'NORMAL')")
    public ResponseEntity<?> getQuizzes() {
        return ResponseEntity.ok(quizServices.getQuizzes());
    }

    @GetMapping("/{quizId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'NORMAL')")
    public ResponseEntity<?> getQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizServices.getQuiz(quizId));
    }

    @GetMapping(value = "/", params = "catId")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'NORMAL')")
    public ResponseEntity<?> getQuizByCategory(@RequestParam Long catId) {
        Category category = categoryService.getCategory(catId);
        return ResponseEntity.ok(quizServices.getQuizByCategory(category));
    }

    @PutMapping("/{quizId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateQuiz(@PathVariable Long quizId, @RequestBody Quiz quiz) {
        if (quizServices.getQuiz(quizId) != null) {
            return ResponseEntity.ok(quizServices.updateQuiz(quiz));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quiz with id : " + String.valueOf(quizId) + ", doesn't exists");
    }

    @DeleteMapping("/{quizId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteQuiz(@PathVariable Long quizId) {
        quizServices.deleteQuiz(quizId);
        return ResponseEntity.ok(true);
    }
}
