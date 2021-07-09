package com.rest.questionnaire.mapping;

import com.rest.questionnaire.dto.AbstractDto;
import com.rest.questionnaire.dto.AnswerDto;
import com.rest.questionnaire.dto.SurveyDto;
import com.rest.questionnaire.dto.SurveyOnlyActiveDto;
import com.rest.questionnaire.dto.listOfQuestions.SurveyGetEmptyDto;
import com.rest.questionnaire.entity.AnswerEntity;
import com.rest.questionnaire.entity.SurveyEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class SurveyMapping {

    private final ModelMapper modelMapper;

    @Autowired
    public SurveyMapping(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SurveyEntity toEntity(SurveyDto dto){
        return Objects.isNull(dto) ? null : modelMapper.map(dto, SurveyEntity.class);
    }

    public SurveyDto toDto(SurveyEntity entity){
        return Objects.isNull(entity) ? null : modelMapper.map(entity, SurveyDto.class);
    }

    public SurveyOnlyActiveDto toDtoActive(SurveyEntity entity){
        return Objects.isNull(entity) ? null : modelMapper.map(entity, SurveyOnlyActiveDto.class);
    }

    public List<SurveyDto> toTdoListNormal(List<SurveyEntity> entity){
        return entity.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<SurveyOnlyActiveDto> toTdoList(List<SurveyEntity> entity){
        return entity.stream().map(this::toDtoActive).collect(Collectors.toList());
    }

    public SurveyGetEmptyDto toDtoGetEmptyDto(SurveyEntity entity){
        return Objects.isNull(entity) ? null : modelMapper.map(entity, SurveyGetEmptyDto.class);
    }

    public List<SurveyGetEmptyDto> toTdoGetEmptyDtoList(List<SurveyEntity> entity){
        return entity.stream().map(this::toDtoGetEmptyDto).collect(Collectors.toList());
    }

}
