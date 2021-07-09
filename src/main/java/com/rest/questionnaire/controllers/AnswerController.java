package com.rest.questionnaire.controllers;

import com.rest.questionnaire.entity.AnswerEntity;
import com.rest.questionnaire.entity.QuestionEntity;
import com.rest.questionnaire.entity.SurveyEntity;
import com.rest.questionnaire.exeption.NoSurveyFound;
import com.rest.questionnaire.service.AnswerService;
import com.rest.questionnaire.service.QuestionService;
import com.rest.questionnaire.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnswerController {

    private final AnswerService answerService;
    private final SurveyService surveyService;

    @Autowired
    public AnswerController(AnswerService answerService, SurveyService surveyService) {
        this.answerService = answerService;
        this.surveyService = surveyService;
    }

    @PostMapping("/answer/{id}")
    public ResponseEntity<?> createAnswer(@PathVariable long id, @RequestBody List<AnswerEntity> entity) {
        try {
            return ResponseEntity.ok(answerService.createAnswer(id, entity));
        } catch (NoSurveyFound noSurveyFound) {
            return ResponseEntity.badRequest().body(noSurveyFound.getMessage());
        }
    }

    @PutMapping("/answer/{id}")
    public ResponseEntity<?> updateAnswer(@PathVariable long id, @RequestBody List<AnswerEntity> entity){
        try {
            return ResponseEntity.ok(answerService.updateAnswer(id,entity));
        } catch (NoSurveyFound noSurveyFound) {
            return ResponseEntity.badRequest().body(noSurveyFound.getMessage());
        }
    }

    @DeleteMapping("/answer/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable long id){
        try{
            answerService.deleteAnswer(id);
            return ResponseEntity.ok().body("Опрос с id = '" + id + "' удален");
        } catch (NoSurveyFound noSurveyFound) {
            return ResponseEntity.badRequest().body(noSurveyFound.getMessage());
        }
    }

    @GetMapping("/answer/{id}")
    public ResponseEntity<?> getAnswer(@PathVariable long id){
        try {
            return ResponseEntity.ok(answerService.getAnswer(id));
        } catch (NoSurveyFound noSurveyFound) {
            noSurveyFound.printStackTrace();
            return ResponseEntity.badRequest().body(noSurveyFound.getMessage());
        }
    }

    @GetMapping("/answer/active")
    public ResponseEntity<?> getActiveSurvey(){
        try {
            return ResponseEntity.ok(answerService.getActiveSurvey());
        } catch (NoSurveyFound noSurveyFound) {
            noSurveyFound.printStackTrace();
            return ResponseEntity.badRequest().body(noSurveyFound.getMessage());
        }
    }

    @GetMapping("/answer/go_to_survey/{id}")
    public ResponseEntity<?> getListOfQuestions(@PathVariable long id){
        try {
            return ResponseEntity.ok(surveyService.getListOfQuestions(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/answer/survey_user_id/{id}")
    public ResponseEntity<?> getSurveyByUserId(@PathVariable Long id){
        try {
            return ResponseEntity.ok(answerService.getListSurveyById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
