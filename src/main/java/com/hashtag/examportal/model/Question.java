package com.hashtag.examportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "questions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quesId;

    @Column(name = "content", length = 5000)
    private String content;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "answer")
    private String answer;

    @OneToMany(mappedBy = "questions", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Option> options = new HashSet<>();


    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;

}
