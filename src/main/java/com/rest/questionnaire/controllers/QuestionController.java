package com.rest.questionnaire.controllers;

import com.rest.questionnaire.entity.QuestionEntity;
import com.rest.questionnaire.exeption.NoSurveyFound;
import com.rest.questionnaire.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/question/{id}")
    public ResponseEntity<?> createQuestion(@PathVariable long id, @RequestBody List<QuestionEntity> entity) {
        try {
            return ResponseEntity.ok(questionService.createQuestion(id, entity));
        } catch (NoSurveyFound noSurveyFound) {
            return ResponseEntity.badRequest().body(noSurveyFound.getMessage());
        }
    }

    @PutMapping("/question/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable long id, @RequestBody List<QuestionEntity> entity){
        try {
            return ResponseEntity.ok(questionService.updateQuestion(id,entity));
        } catch (NoSurveyFound noSurveyFound) {
            return ResponseEntity.badRequest().body(noSurveyFound.getMessage());
        }
    }

    @DeleteMapping("/question/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable long id){
        try{
            questionService.deleteQuestion(id);
            return ResponseEntity.ok().body("Опрос с id = '" + id + "' удален");
        } catch (NoSurveyFound noSurveyFound) {
            return ResponseEntity.badRequest().body(noSurveyFound.getMessage());
        }
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<?> getQuestion(@PathVariable long id){
        try {
            return ResponseEntity.ok(questionService.getQuestion(id));
        } catch (NoSurveyFound noSurveyFound) {
            noSurveyFound.printStackTrace();
            return ResponseEntity.badRequest().body(noSurveyFound.getMessage());
        }
    }
}
