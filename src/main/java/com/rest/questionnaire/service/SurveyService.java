package com.rest.questionnaire.service;

import com.rest.questionnaire.dao.SurveyRepository;
import com.rest.questionnaire.dto.SurveyDto;
import com.rest.questionnaire.dto.listOfQuestions.SurveyGetEmptyDto;
import com.rest.questionnaire.entity.AnswerEntity;
import com.rest.questionnaire.entity.SurveyEntity;
import com.rest.questionnaire.exeption.NoSurveyFound;
import com.rest.questionnaire.mapping.SurveyMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SurveyService {

    private final SurveyMapping mapping;

    private final SurveyRepository repository;

    @Autowired
    public SurveyService(SurveyMapping mapping, SurveyRepository repository) {
        this.mapping = mapping;
        this.repository = repository;
    }

    public SurveyDto createSurvey(SurveyEntity entity){
        return mapping.toDto(repository.save(entity));
    }

    public SurveyDto updateSurvey(SurveyEntity entity) throws NoSurveyFound {
        Optional<SurveyEntity> surveyEntity = repository.findById(entity.getId());
        if(surveyEntity.isEmpty()){
            throw new NoSurveyFound("Не найден опрос с ID = " + entity.getId());
        }
        LocalDateTime startDate = surveyEntity.get().getStartDate();
        entity.setStartDate(startDate);

        return mapping.toDto(repository.save(entity));
    }

    public SurveyDto getSurvey(long id) throws NoSurveyFound {
        Optional<SurveyEntity> surveyEntity = repository.findById(id);
        if (surveyEntity.isEmpty()) {
            throw new NoSurveyFound("Не найден опрос с ID = " + id);
        }
        return mapping.toDto(surveyEntity.get());
    }

    public void deleteSurvey(long id) throws NoSurveyFound {
        Optional<SurveyEntity> surveyEntity = repository.findById(id);
        if (surveyEntity.isEmpty()) {
            throw new NoSurveyFound("Не найден опрос с ID = " + id);
        }
        repository.deleteById(id);
    }

    public SurveyGetEmptyDto getListOfQuestions(long id){
        SurveyEntity surveyEntity = repository.getById(id);
        List<AnswerEntity> entity = new ArrayList<>();
        surveyEntity.getQuestions().forEach(e -> e.setAnswerEntity(entity));

        return mapping.toDtoGetEmptyDto(surveyEntity);
    }

}
