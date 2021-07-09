package com.rest.questionnaire.mapping;

import com.rest.questionnaire.dto.QuestionDto;
import com.rest.questionnaire.dto.SurveyDto;
import com.rest.questionnaire.entity.QuestionEntity;
import com.rest.questionnaire.entity.SurveyEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class QuestionMapping {


    private final ModelMapper modelMapper;

    @Autowired
    public QuestionMapping(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public QuestionEntity toEntity(QuestionDto dto){
        return Objects.isNull(dto) ? null : modelMapper.map(dto, QuestionEntity.class);
    }

    public QuestionDto toDto(QuestionEntity entity){
        return Objects.isNull(entity) ? null : modelMapper.map(entity, QuestionDto.class);
    }

    public List<QuestionDto> toTdoList(List<QuestionEntity> entity){
        return entity.stream().map(this::toDto).collect(Collectors.toList());
    }
}
