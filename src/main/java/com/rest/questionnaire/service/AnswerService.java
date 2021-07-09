package com.rest.questionnaire.service;


import com.rest.questionnaire.dao.AnswerRepository;
import com.rest.questionnaire.dao.QuestionRepository;
import com.rest.questionnaire.dao.SurveyRepository;
import com.rest.questionnaire.dto.AnswerDto;
import com.rest.questionnaire.dto.QuestionDto;
import com.rest.questionnaire.dto.SurveyDto;
import com.rest.questionnaire.dto.SurveyOnlyActiveDto;
import com.rest.questionnaire.entity.AnswerEntity;
import com.rest.questionnaire.entity.QuestionEntity;
import com.rest.questionnaire.entity.SurveyEntity;
import com.rest.questionnaire.exeption.NoSurveyFound;
import com.rest.questionnaire.mapping.AnswerMapping;
import com.rest.questionnaire.mapping.QuestionMapping;
import com.rest.questionnaire.mapping.SurveyMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnswerService {

    private final AnswerMapping mapping;
    private final SurveyMapping surveyMapping;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;

    @Autowired
    public AnswerService(AnswerMapping mapping,
                           AnswerRepository answerRepository,
                           QuestionRepository questionRepository,
                           SurveyRepository surveyRepository,
                           SurveyMapping surveyMapping) {
        this.mapping = mapping;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.surveyRepository = surveyRepository;
        this.surveyMapping = surveyMapping;
    }

    public List<AnswerDto> createAnswer(long id, List<AnswerEntity> entity) throws NoSurveyFound {
        Optional<QuestionEntity> questionEntity = getQuestion(id);
        if(questionEntity.isEmpty()){
            throw new NoSurveyFound("Ошибка, не найден вопрос с ID = " + id);
        }
        entity.forEach(e -> e.setQuestionId(questionEntity.get()));

        return mapping.toTdoList(answerRepository.saveAll(entity));
    }

        public List<AnswerDto> updateAnswer(long id, List<AnswerEntity> entity) throws NoSurveyFound {
        Optional<QuestionEntity> questionEntity = getQuestion(id);
        if(questionEntity.isEmpty()){
            throw new NoSurveyFound("Ошибка, не найден вопрос с ID = " + id);
        }
        entity.forEach(e -> e.setQuestionId(questionEntity.get()));

        return mapping.toTdoList(answerRepository.saveAll(entity));
    }

    public AnswerDto getAnswer(long id) throws NoSurveyFound {
        Optional<AnswerEntity> answerEntity = answerRepository.findById(id);
        if (answerEntity.isEmpty()) {
            throw new NoSurveyFound("Ошибка, не найден вопрос с ID = " + id);
        }
        return mapping.toDto(answerEntity.get());
    }

    public void deleteAnswer(long id) throws NoSurveyFound {
        Optional<AnswerEntity> answerEntity = answerRepository.findById(id);
        if (answerEntity.isEmpty()) {
            throw new NoSurveyFound("Ошибка, не найден вопрос с ID = " + id);
        }
        answerRepository.deleteById(id);
    }

    private Optional<QuestionEntity> getQuestion(long id){
        return questionRepository.findById(id);
    }


    public List<SurveyOnlyActiveDto> getActiveSurvey() throws NoSurveyFound {

        List<SurveyEntity> surveyEntity = surveyRepository.findAllActiveSurvey(LocalDateTime.now());
        if(surveyEntity.isEmpty()){
            throw new NoSurveyFound("На данный момент отсутствуют активные опросы");
        }

        return surveyMapping.toTdoList(surveyEntity);
    }

    public List<SurveyDto> getListSurveyById(Long id){
        List<AnswerEntity> answerListId = answerRepository.getAllByUserId(id);
        List<QuestionEntity> answerUserId = answerListId.stream().distinct().map(e -> e.getQuestionId()).collect(Collectors.toList());
        List<Long> v1 = answerUserId.stream().distinct().map(e -> e.getId()).collect(Collectors.toList());
        List<QuestionEntity> questionListId =  questionRepository.findAllById(v1);
        List<SurveyEntity> longId = questionListId.stream().distinct().map(e -> e.getSurveyId()).collect(Collectors.toList());
        List<Long> longSurveyId = longId.stream().distinct().map(e -> e.getId()).collect(Collectors.toList());
        return surveyMapping.toTdoListNormal(surveyRepository.findAllById(longSurveyId));
    }
}
