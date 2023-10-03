package com.hashtag.examportal.repo;

import com.hashtag.examportal.model.Option;
import com.hashtag.examportal.model.Question;
import com.hashtag.examportal.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {

    List<Option> findByQuestion(Question question);
}
