package com.rest.questionnaire.dao;

import com.rest.questionnaire.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

//    List<AnswerEntity> findAllByUserId(long id);

//    @Query(value = "select id from answers where user_id = ?", nativeQuery = true)
//    List<Long> getAllById(long user_id);

//    List<Long> findAllByUserId(long userId);

    List<AnswerEntity> getAllByUserId(Long userId);
}
