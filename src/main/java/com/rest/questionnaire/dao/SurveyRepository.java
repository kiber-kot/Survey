package com.rest.questionnaire.dao;

import com.rest.questionnaire.entity.SurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SurveyRepository extends JpaRepository<SurveyEntity, Long> {

    @Query(value = "select * from survey where end_date >= ? order by end_date",nativeQuery = true)
    List<SurveyEntity> findAllActiveSurvey(LocalDateTime end_date);
}
