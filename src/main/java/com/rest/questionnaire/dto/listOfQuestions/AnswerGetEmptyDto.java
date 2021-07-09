package com.rest.questionnaire.dto.listOfQuestions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnswerGetEmptyDto {

    private long id;

    private long userId;

    private String answer;

    private Long questionId;
}
