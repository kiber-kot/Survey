package com.rest.questionnaire.dao;

import com.rest.questionnaire.entity.AnswerEntity;
import com.rest.questionnaire.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

//    @Query(value = "select survey_id from questions where id in(?)", nativeQuery = true)
//    List<Long> getAllById(Collection<AnswerEntity> id);

//    List<Long> findAllById(Collection<Long> id);
}
