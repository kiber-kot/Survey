package com.rest.questionnaire.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnswerDto {

    private long id;

    private long userId;

    private String answer;

    private Long questionId;
}
