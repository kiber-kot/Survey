package com.rest.questionnaire.dto.listOfQuestions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rest.questionnaire.dto.QuestionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class SurveyGetEmptyDto {

    private long id;

    private String surveyName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    private String description;

    private List<QuestionGetEmptyDto> questions;
}
