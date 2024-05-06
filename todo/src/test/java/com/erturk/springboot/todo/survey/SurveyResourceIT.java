package com.erturk.springboot.todo.survey;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SurveyResourceIT {
    private static final String SPECIFIC_QUESTION_URL = "/surveys/2/questions/5";
    private static final String GENERIC_QUESTIONS_URL = "/surveys/2/questions";

    @Autowired
    TestRestTemplate template;

    @Test
    void retrieveQuestionById_basicScenario() throws JSONException {
        ResponseEntity<String> responseEntity = template.getForEntity(SPECIFIC_QUESTION_URL, String.class);

        String expectedResponse = """
                {"id":5,"options":["Java","Python","C++","Go"],"correctAnswer":"Python"}
                """;

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals("application/json", responseEntity.getHeaders().get("Content-Type").getFirst());
        //assertEquals(expectedResponse.trim(), responseEntity.getBody());
        JSONAssert.assertEquals(expectedResponse, responseEntity.getBody(), false);
    }

    @Test
    void retrieveAllSurveys_basicScenario() throws JSONException {
        ResponseEntity<String> responseEntity = template.getForEntity(GENERIC_QUESTIONS_URL, String.class);

        String expectedResponse = """
                [
                    {
                        "id": 4
                    },
                    {
                        "id": 5
                    }
                ]
                """;

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals("application/json", responseEntity.getHeaders().get("Content-Type").getFirst());
        JSONAssert.assertEquals(expectedResponse, responseEntity.getBody(), false);
    }

    @Test
    void addQuestion_basicScenario() {
        String requestBody = """
                {
                    "description": "My Favorite Programming Language",
                    "options":
                        [
                            "Java",
                            "Python",
                            "C++",
                            "Go"
                        ],
                    "correctAnswer": "Python"
                }
                """;

        String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJFcnR1cmsiLCJpYXQiOjE3MTMzNTY2MDYsImV4cCI6MTcxMzQ0MzAwNn0.aOYaWoIhwq88CNY0-nwtiN4FokAGbt8d67-YtMrSBtw";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<String> httpEntity = new HttpEntity<String >(requestBody, headers);

        ResponseEntity<String> responseEntity = template.exchange(GENERIC_QUESTIONS_URL,
                HttpMethod.POST, httpEntity, String.class);

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

        String locationHeader = responseEntity.getHeaders().get("Location").get(0);

        assertTrue(locationHeader.contains("/surveys/2/questions/"));

        template.delete(locationHeader);
    }

    String performBasicAuthEncoding(String user, String password) {
        String combined = user + ":" + password;
        byte[] encodedBytes = Base64.getEncoder().encode(combined.getBytes());
        return new String(encodedBytes);
    }
}
