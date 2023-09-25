package com.hashtag.examportal.service.impl;

import com.hashtag.examportal.model.Question;
import com.hashtag.examportal.model.Quiz;
import com.hashtag.examportal.repo.QuestionRepository;
import com.hashtag.examportal.service.QuestionService;
import com.hashtag.examportal.service.QuizServices;
import com.hashtag.examportal.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuizServices quizRepository;

//    @Override
//    public Question addQuestion(Question question) {
////        Quiz quiz = quizRepository.findById(question.getQuiz().getQuizId()).get();
////        quiz.setNumOfQuestions(quiz.getNumOfQuestions() + 1);
////        quizRepository.save(quiz);
//        return questionRepository.save(question);
//    }

    @Override
    public Question addQuestion(MultipartFile image, String content,String answer,Quiz quiz) throws IOException {

            byte[] imageBytes = ImageUtils.compressBytes(image.getBytes());


            Question question = new Question();
            question.setContent(content);
            question.setQuestion_Image(imageBytes);
            question.setAnswer(answer);
//            question.setQuestionLink("http://localhost:8080/api/question/image/"+question.getQuesId());
            quiz.setNumOfQuestions(quiz.getNumOfQuestions()+1);
            question.setQuiz(quiz);

            return questionRepository.save(question);
    }

    @Override
    public Question addWithoutQuestion(String content, String answer, Quiz quiz) throws IOException {
        Question question = new Question();
        question.setContent(content);
        question.setAnswer(answer);
        quiz.setNumOfQuestions(quiz.getNumOfQuestions()+1);
        question.setQuiz(quiz);

        return questionRepository.save(question);
    }

//    @Override
//    public Question addQuestionWithImage(MultipartFile imageFile, String content, MultipartFile option1_image, MultipartFile option2_image, MultipartFile option3_image, MultipartFile option4_image, String answer, Quiz quiz) throws IOException {
//        byte[] imageBytes = compressBytes(imageFile.getBytes());
//        byte[] option1Bytes = compressBytes(option1_image.getBytes());
//        byte[] option2Bytes = compressBytes(option2_image.getBytes());
//        byte[] option3Bytes = compressBytes(option3_image.getBytes());
//        byte[] option4Bytes = compressBytes(option4_image.getBytes());
//
//
//        Question question = new Question();
//        question.setContent(content);
//        question.setImage(imageBytes);
//        question.setOption1_image(option1Bytes);
//        question.setOption2_image(option2Bytes);
//        question.setOption3_image(option3Bytes);
//        question.setOption4_image(option4Bytes);
//        question.setAnswer(answer);
//        quiz.setNumOfQuestions(quiz.getNumOfQuestions()+1);
//        question.setQuiz(quiz);
//
//        return questionRepository.save(question);
//    }

    @Override
    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestion(Long quesId) {
        if (questionRepository.findById(quesId) == null) {
            System.out.println("No question......................................");
            return null;
        } else {
            Question question = questionRepository.findById(quesId).get();
//            System.out.println(question);

            System.out.println(question.getQuestion_Image());
            if(question.getQuestion_Image()!=null){
                System.out.println(question.getQuestion_Image());
                question.setQuestion_Image(ImageUtils.decompressBytes(question.getQuestion_Image()));
                System.out.println(question.getQuestion_Image());
//                if(question.getQuestion_Image()!=null){
                question.setQuestionImageLink("http://localhost:8080/api/question/image/"+question.getQuesId());
//
                }


            System.out.println(".....................................Decompress done .......................................");
            return question;
        }
    }

    @Override
    public Question updateQuestion(Long qusetionId,Question question) {
        Question exquestion = questionRepository.findById(qusetionId).get();
        exquestion.setContent(question.getContent());
        exquestion.setQuestion_Image(question.getQuestion_Image());
//        exquestion.setOption1(question.getOption1());
//        exquestion.setOption2(question.getOption2());
//        exquestion.setOption3(question.getOption3());
//        exquestion.setOption4(question.getOption4());
        exquestion.setAnswer(question.getAnswer());
        return questionRepository.save(exquestion);
    }

    @Override
    public void deleteQuestion(Long questionId) {
//        Question question = questionRepository.findById(questionId).get();
//        Quiz quiz=question.getQuiz();
//        quiz.setNumOfQuestions(quiz.getNumOfQuestions()-1);
        System.out.println("........................Question Id.........................."+questionId);
        questionRepository.deleteById(questionId);
    }
//    @Override
//    public void deleteQuiz(Long quizId) {
//        quizRepository.deleteById(quizId);
//    }

    @Override
    public List<Question> getQuestionsByQuiz(Quiz quiz) {
        return questionRepository.findByQuiz(quiz);
    }

//    public static byte[] compressBytes(byte[] data) {
//        Deflater deflater = new Deflater();
//        deflater.setInput(data);
//        deflater.finish();
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//        byte[] buffer = new byte[1024];
//        while (!deflater.finished()) {
//            int count = deflater.deflate(buffer);
//            outputStream.write(buffer, 0, count);
//        }
//        try {
//            outputStream.close();
//        } catch (IOException e) {
//        }
//        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
//        return outputStream.toByteArray();
//    }
//    // uncompress the image bytes before returning it to the angular application
//    public static byte[] decompressBytes(byte[] data) {
//        Inflater inflater = new Inflater();
//        inflater.setInput(data);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//        byte[] buffer = new byte[1024];
//        try {
//            while (!inflater.finished()) {
//                int count = inflater.inflate(buffer);
//                outputStream.write(buffer, 0, count);
//            }
//            outputStream.close();
//        } catch (IOException ioe) {
//        } catch (DataFormatException e) {
//        }
//        return outputStream.toByteArray();
//    }
}
