package com.hashtag.examportal.controller;

import com.hashtag.examportal.model.Option;
import com.hashtag.examportal.model.Question;
import com.hashtag.examportal.model.Quiz;
import com.hashtag.examportal.repo.OptionRepository;
import com.hashtag.examportal.service.OptionService;
import com.hashtag.examportal.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/options")
public class OptionController {

    @Autowired
    OptionRepository optionRepository;

    @Autowired
    OptionService optionService;

    @Autowired
    QuestionService questionService;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addOption(@RequestParam("image") MultipartFile image,@RequestParam("option") String options,
                                         @RequestParam("question") Question question) throws IOException {
        return ResponseEntity.ok(optionService.addOptionWithImage(image,options,question));
    }
    @PostMapping("/without")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addOption(@RequestParam("options") String options,
                                       @RequestParam("question") Question question) throws IOException {
        return ResponseEntity.ok(optionService.addOption(options,question));
    }

    @GetMapping("/{optionId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'NORMAL')")
    public ResponseEntity<?> getOption(@PathVariable Long optionId) {
        Option option = optionService.getOption(optionId);
        return ResponseEntity.ok(option);
    }
    @GetMapping("/image/{optionId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'NORMAL')")
    public ResponseEntity<?> getOptionwithImage(@PathVariable Long optionId) {
        Option question = optionService.getOption(optionId);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(question.getOptionImage());
    }

    @GetMapping(value = "/", params = "questionId")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'NORMAL')")
    public ResponseEntity<?> getOptionsByQuestion(@RequestParam Long questionId) {
//        System.out.println("........bvbncbdfnn,vhsvsdhfnf.......,.....................");
        if(questionService.getQuestion(questionId) != null){
        Question question = questionService.getQuestion(questionId);
        Set<Option> options = question.getOptions();
        System.out.println(options.size());
        return ResponseEntity.ok(options);
    }
    else{
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{optionId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteOption(@PathVariable Long optionId) {
        optionService.deleteOption(optionId);
        return ResponseEntity.ok("Deleted");
    }
}
