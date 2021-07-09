package com.rest.questionnaire.service;


import com.rest.questionnaire.dao.QuestionRepository;
import com.rest.questionnaire.dao.SurveyRepository;
import com.rest.questionnaire.dto.QuestionDto;
import com.rest.questionnaire.dto.SurveyDto;
import com.rest.questionnaire.entity.QuestionEntity;
import com.rest.questionnaire.entity.SurveyEntity;
import com.rest.questionnaire.exeption.NoSurveyFound;
import com.rest.questionnaire.mapping.QuestionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionService {

    private final QuestionMapping mapping;
    private final QuestionRepository repository;
    private final SurveyRepository surveyRepository;

    @Autowired
    public QuestionService(QuestionMapping mapping, QuestionRepository repository, SurveyRepository surveyRepository) {
        this.mapping = mapping;
        this.repository = repository;
        this.surveyRepository = surveyRepository;
    }

    public List<QuestionDto> createQuestion(long id, List<QuestionEntity> entity) throws NoSurveyFound {
        Optional<SurveyEntity> surveyEntity = getSurvey(id);
        if(surveyEntity.isEmpty()){
            throw new NoSurveyFound("Ошибка, не найден опросник с ID = " + id);
        }
        entity.forEach(e -> e.setSurveyId(surveyEntity.get()));

        return mapping.toTdoList(repository.saveAll(entity));
    }

    public List<QuestionDto> updateQuestion(long id, List<QuestionEntity> entity) throws NoSurveyFound {
        Optional<SurveyEntity> surveyEntity = getSurvey(id);
        if(surveyEntity.isEmpty()){
            throw new NoSurveyFound("Ошибка, не найден опросник с ID = " + id);
        }
        entity.forEach(e -> e.setSurveyId(surveyEntity.get()));

        return mapping.toTdoList(repository.saveAll(entity));
    }

    public QuestionDto getQuestion(long id) throws NoSurveyFound {
        Optional<QuestionEntity> questionEntity = repository.findById(id);
        if (questionEntity.isEmpty()) {
            throw new NoSurveyFound("Не найден вопрос с ID = " + id);
        }
        return mapping.toDto(questionEntity.get());
    }

    public void deleteQuestion(long id) throws NoSurveyFound {
        Optional<QuestionEntity> questionEntity = repository.findById(id);
        if (questionEntity.isEmpty()) {
            throw new NoSurveyFound("Не найден вопрос с ID = " + id);
        }
        repository.deleteById(id);
    }

    private Optional<SurveyEntity> getSurvey(long id){
        return surveyRepository.findById(id);
    }
}
