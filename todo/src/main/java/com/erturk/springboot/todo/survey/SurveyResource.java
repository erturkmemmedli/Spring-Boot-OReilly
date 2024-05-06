package com.erturk.springboot.todo.survey;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class SurveyResource {
    public SurveyResource(SurveyService surveyService) {
        super();
        this.surveyService = surveyService;
    }

    public SurveyService surveyService;

    @RequestMapping("/surveys")
    public List<Survey> retrieveAllSurveys() {
        return surveyService.retrieveAllSurveys();
    }

    @RequestMapping("/surveys/{surveyId}")
    public Survey retrieveSurveyById(@PathVariable int surveyId) {
        Survey survey = surveyService.retrieveSurveyById(surveyId);
        if (survey == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return survey;
    }

    @RequestMapping("/surveys/{surveyId}/questions")
    public List<Question> retrieveQuestionsBySurveyId(@PathVariable int surveyId) {
        List<Question> questions = surveyService.retrieveQuestionsBySurveyId(surveyId);
        if (questions == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return questions;
    }

    @RequestMapping("surveys/{surveyId}/questions/{questionId}")
    public Question retrieveQuestionById(@PathVariable int surveyId, @PathVariable int questionId) {
        Question question = surveyService.retrieveQuestionById(surveyId, questionId);
        if (question == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return question;
    }

    @RequestMapping(value = "/surveys/{surveyId}/questions", method = RequestMethod.POST)
    public ResponseEntity<Object> addQuestion(@PathVariable int surveyId, @RequestBody Question question) {
        int questionId = surveyService.addQuestion(surveyId, question);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{questionId}").buildAndExpand(questionId).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "surveys/{surveyId}/questions/{questionId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteQuestion(@PathVariable int surveyId, @PathVariable int questionId) {
        surveyService.deleteQuestion(surveyId, questionId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "surveys/{surveyId}/questions/{questionId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateQuestion(@PathVariable int surveyId, @PathVariable int questionId,
                                                 @RequestBody Question question) {
        surveyService.updateQuestion(surveyId, questionId, question);
        return ResponseEntity.noContent().build();
    }
}
