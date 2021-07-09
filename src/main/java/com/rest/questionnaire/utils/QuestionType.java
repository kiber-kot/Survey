package com.rest.questionnaire.utils;

import lombok.ToString;

@ToString
public enum QuestionType {
    TEST_RESPONSE, ONE_OPTION, MANY_OPTIONS;

    QuestionType() {
    }

}
