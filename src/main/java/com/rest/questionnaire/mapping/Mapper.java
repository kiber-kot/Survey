package com.rest.questionnaire.mapping;

import com.rest.questionnaire.dto.AbstractDto;
import com.rest.questionnaire.entity.AbstractEntity;

public interface Mapper<E extends AbstractEntity, D extends AbstractDto> {

    E toEntity(D dto);

    D toDto(E entity);
}
