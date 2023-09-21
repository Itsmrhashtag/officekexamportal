package com.hashtag.examportal.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "options_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quesId;

    @Column(name = "options")
    private String options;

    @ManyToOne(fetch = FetchType.EAGER)
    private Question questions;

}
