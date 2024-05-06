package com.erturk.springboot.todo.survey;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;
import java.util.function.Predicate;

@Service
public class SurveyService {
    private static final List<Survey> surveys = new ArrayList<>();

    static {
        Question question1 = new Question(1,
                "Most Popular Cloud Platform Today",
                Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"),
                "AWS");
        Question question2 = new Question(2,
                "Fastest Growing Cloud Platform",
                Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"),
                "Google Cloud");
        Question question3 = new Question(3,
                "Most Popular DevOps Tool",
                Arrays.asList("Kubernetes", "Docker", "Terraform", "Azure DevOps"),
                "Kubernetes");
        Question question4 = new Question(4,
                "Most Popular Programming Language in Azerbaijan",
                Arrays.asList("Java", "Python", "C++", "Go"),
                "Java");
        Question question5 = new Question(5,
                "Most Suitable Programming Language in Data Science",
                Arrays.asList("Java", "Python", "C++", "Go"),
                "Python");

        Survey survey1 = new Survey(1,
                "IT Survey 1",
                "Survey regarding Cloud and DevOps",
                new ArrayList<>(Arrays.asList(question1, question2, question3)));
        Survey survey2 = new Survey(2,
                "IT Survey 2",
                "Survey regarding Programming Languages",
                new ArrayList<>(Arrays.asList(question4, question5)));

        surveys.add(survey1);
        surveys.add(survey2);
    }

    public List<Survey> retrieveAllSurveys() {
        return surveys;
    }

    public Survey retrieveSurveyById(int surveyId) {
        Predicate<? super Survey> predicate = survey -> survey.getId() == surveyId;
        Optional<Survey> survey = surveys.stream().filter(predicate).findFirst();
        return survey.orElse(null);
    }

    public List<Question> retrieveQuestionsBySurveyId(int surveyId) {
        Survey survey = retrieveSurveyById(surveyId);
        if (survey == null) return null;
        return survey.getQuestions();
    }

    public Question retrieveQuestionById(int surveyId, int questionId) {
        List<Question> questions = retrieveQuestionsBySurveyId(surveyId);
        if (questions == null) return null;
        Predicate<? super Question> predicate = question -> question.getId() == questionId;
        Optional<Question> question = questions.stream().filter(predicate).findFirst();
        return question.orElse(null);
    }

    public int addQuestion(int surveyId, Question question) {
        List<Question> questions = retrieveQuestionsBySurveyId(surveyId);
        question.setId(generateRandomId());
        questions.add(question);
        return question.getId();
    }

    public void deleteQuestion(int surveyId, int questionId) {
        List<Question> questions = retrieveQuestionsBySurveyId(surveyId);
        Predicate<? super Question> predicate = question -> question.getId() == questionId;
        questions.removeIf(predicate);
    }

    private static int generateRandomId() {
        SecureRandom secureRandom = new SecureRandom();
        return Math.abs(secureRandom.nextInt());
    }

    public void updateQuestion(int surveyId, int questionId, Question question) {
        List<Question> questions = retrieveQuestionsBySurveyId(surveyId);
        questions.removeIf(q -> q.getId() == questionId);
        questions.add(question);
    }
}
