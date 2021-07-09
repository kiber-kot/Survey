package com.rest.questionnaire.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "answers")
@Getter
@Setter
@NoArgsConstructor
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String answer;

    private long userId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity questionId;

    @JsonCreator
    public AnswerEntity (@JsonProperty("question_id") Long questionId ) {
        this.setId(questionId);
    }
}
