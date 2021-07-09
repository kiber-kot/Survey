package com.rest.questionnaire.dto.listOfQuestions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest.questionnaire.dto.AnswerDto;
import com.rest.questionnaire.utils.QuestionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class QuestionGetEmptyDto {

    private long id;

    private String questionText;

    private QuestionType questionType;

    private Long surveyId;

//    @JsonIgnore
    private List<AnswerGetEmptyDto> answerEntity;
}
