package com.hashtag.examportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "num_of_quizzes")
    private int numOfQuizzes;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Quiz> quizzes = new ArrayList<>();

//    @Override
//    public String toString() {
//        return "Category{" +
//                "catId=" + catId +
//                ", title='" + title + '\'' +
//                ", description='" + description + '\'' +
//                ", quizzes=" + quizzes +
//                '}';
//    }
//
//    public Long getCatId() {
//        return catId;
//    }
//
//    public void setCatId(Long catId) {
//        this.catId = catId;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public List<Quiz> getQuizzes() {
//        return quizzes;
//    }
//
//    public void setQuizzes(List<Quiz> quizzes) {
//        this.quizzes = quizzes;
//    }


    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumOfQuizzes() {
        return numOfQuizzes;
    }

    public void setNumOfQuizzes(int numOfQuizzes) {
        this.numOfQuizzes = numOfQuizzes;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    @Override
    public String toString() {
        return "Category{" +
                "catId=" + catId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", numOfQuizzes=" + numOfQuizzes +
                ", quizzes=" + quizzes +
                '}';
    }
}
