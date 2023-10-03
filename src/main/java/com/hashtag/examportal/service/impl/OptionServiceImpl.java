package com.hashtag.examportal.service.impl;

import com.hashtag.examportal.model.Option;
import com.hashtag.examportal.model.Question;
import com.hashtag.examportal.model.Quiz;
import com.hashtag.examportal.repo.OptionRepository;
import com.hashtag.examportal.service.OptionService;
import com.hashtag.examportal.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    OptionRepository optionRepository;

    @Override
    public Option addOption(String options, Question question) throws IOException {
        Option option=new Option();
        option.setOptions(options);
        option.setQuestion(question);
        question.setNoOfOption(question.getNoOfOption()+1);
        return optionRepository.save(option);
    }
    @Override
    public Option addOptionWithImage(MultipartFile image,String options, Question question) throws IOException {

        byte[] imageBytes = ImageUtils.compressBytes(image.getBytes());

        Option option=new Option();
        option.setOptions(options);
        option.setQuestion(question);
        option.setOptionImage(imageBytes);
        question.setNoOfOption(question.getNoOfOption()+1);
        return optionRepository.save(option);
    }

    @Override
    public List<Option> getOptions() {
        return optionRepository.findAll();
        //return questionRepository.findByQuiz(quiz
    }

    @Override
    public Option getOption(Long optionId) {

        if(optionRepository.findById(optionId)==null){
            System.out.println("No option......................................");
            return null;
        }else{
            Option option = optionRepository.findById(optionId).get();
//            System.out.println(question);

            System.out.println(option.getOptionImage());
            if(option.getOptionImage()!=null){
                System.out.println(option.getOptionImage());
                option.setOptionImage(ImageUtils.decompressBytes(option.getOptionImage()));
                System.out.println(option.getOptionImage());
//                if(question.getQuestion_Image()!=null){
               option.setOptionImageLink("http://localhost:8080/api/question/image/"+option.getOptionId());
//
            }


            System.out.println(".....................................Decompress done .......................................");
            return  option;
        }

//        if (questionRepository.findById(quesId) == null) {
//            System.out.println("No question......................................");
//            return null;
//        } else {
//            Question question = questionRepository.findById(quesId).get();
////            System.out.println(question);
//
//            System.out.println(question.getQuestion_Image());
//            if(question.getQuestion_Image()!=null){
//                System.out.println(question.getQuestion_Image());
//                question.setQuestion_Image(ImageUtils.decompressBytes(question.getQuestion_Image()));
//                System.out.println(question.getQuestion_Image());
////                if(question.getQuestion_Image()!=null){
//                question.setQuestionImageLink("http://localhost:8080/api/question/image/"+question.getQuesId());
////
//            }
//
//
//            System.out.println(".....................................Decompress done .......................................");
//            return question;
//        }
    }

    @Override
    public void deleteOption(Long optionId) {
        try {
//            Option option = optionRepository.findById(optionId).orElse(null);
//            Question question = option.getQuestion();
//            question.setNoOfOption(question.getNoOfOption() - 1);
            optionRepository.deleteById(optionId);
            System.out.println("Deleted Sucessfully");
//        optionRepository.delete(option);
        } catch (Exception e) {
            // Handle exceptions (e.g., database errors or validation errors)
            throw new RuntimeException("Failed to delete the option.", e);
        }
    }

    @Override
    public List<Option> getOptionsByQuestion(Question question) {
        return optionRepository.findByQuestion(question);
    }

}
