package com.rest.questionnaire.controllers;

import com.rest.questionnaire.entity.SurveyEntity;
import com.rest.questionnaire.exeption.NoSurveyFound;
import com.rest.questionnaire.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SurveyController {

    private final SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping("/survey")
    public ResponseEntity<?> createSurvey(@RequestBody SurveyEntity entity){
        return ResponseEntity.ok(surveyService.createSurvey(entity));
    }

    @PutMapping("/survey")
    public ResponseEntity<?> updateSurvey(@RequestBody SurveyEntity entity){
        try {
            return ResponseEntity.ok(surveyService.updateSurvey(entity));
        } catch (NoSurveyFound noSurveyFound) {
            return ResponseEntity.badRequest().body(noSurveyFound.getMessage());
        }
    }

    @DeleteMapping("/survey/{id}")
    public ResponseEntity<?> deleteSurvey(@PathVariable long id){
        try{
            surveyService.deleteSurvey(id);
            return ResponseEntity.ok().body("Опрос с id = '" + id + "' удален");
        } catch (NoSurveyFound noSurveyFound) {
            return ResponseEntity.badRequest().body(noSurveyFound.getMessage());
        }
    }

    @GetMapping("/survey/{id}")
    public ResponseEntity<?> getSurvey(@PathVariable long id){
        try {
            return ResponseEntity.ok(surveyService.getSurvey(id));
        } catch (NoSurveyFound noSurveyFound) {
            noSurveyFound.printStackTrace();
            return ResponseEntity.badRequest().body(noSurveyFound.getMessage());
        }
    }
}
