package com.rest.questionnaire.mapping;

import com.rest.questionnaire.dto.AnswerDto;
import com.rest.questionnaire.dto.QuestionDto;
import com.rest.questionnaire.entity.AnswerEntity;
import com.rest.questionnaire.entity.QuestionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AnswerMapping {

    private final ModelMapper modelMapper;

    @Autowired
    public AnswerMapping(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AnswerEntity toEntity(AnswerDto dto){
        return Objects.isNull(dto) ? null : modelMapper.map(dto, AnswerEntity.class);
    }

    public AnswerDto toDto(AnswerEntity entity){
        return Objects.isNull(entity) ? null : modelMapper.map(entity, AnswerDto.class);
    }

    public List<AnswerDto> toTdoList(List<AnswerEntity> entity){
        return entity.stream().map(this::toDto).collect(Collectors.toList());
    }
}
