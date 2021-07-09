package com.rest.questionnaire.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rest.questionnaire.utils.QuestionType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "questions")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String questionText;

    @Enumerated(value = EnumType.STRING)
    private QuestionType questionType;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private SurveyEntity surveyId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionId")
    private List<AnswerEntity> answerEntity;

    @JsonCreator
    public QuestionEntity(@JsonProperty("survey_id") Long surveyId ) {
        this.setId(surveyId);
    }
}
