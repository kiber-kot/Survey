package com.rest.questionnaire.dto;

//import com.rest.questionnaire.entity.AnswerEntity;
import com.rest.questionnaire.entity.AnswerEntity;
import com.rest.questionnaire.entity.SurveyEntity;
import com.rest.questionnaire.utils.QuestionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class QuestionDto {

    private long id;

    private String questionText;

    private QuestionType questionType;

    private Long surveyId;

    private List<AnswerDto> answerEntity;
}
