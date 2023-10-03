package com.hashtag.examportal.service;

import com.hashtag.examportal.model.Option;
import com.hashtag.examportal.model.Question;
import com.hashtag.examportal.model.Quiz;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface OptionService {

    Option addOption(String options,Question question) throws IOException;

    public Option addOptionWithImage(MultipartFile image,String options, Question question) throws IOException;
    List<Option> getOptions();

    Option getOption(Long optionId);

    void deleteOption(Long optionId);
    List<Option> getOptionsByQuestion(Question question);
}
