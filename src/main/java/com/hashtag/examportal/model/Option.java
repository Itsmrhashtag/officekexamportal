package com.hashtag.examportal.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long optionId;

    @Column(name = "options")
    private String options;

    @JsonIgnore
    @Lob
    @Column(name = "option_image")
    private byte[] optionImage;

    @Column(name="option_image_link")
    private String optionImageLink;

    @ManyToOne(fetch = FetchType.EAGER)
    private Question question;

}
