package com.erturk.springboot.todo.survey;

import org.json.JSONException;
import org.junit.jupiter.api.Test;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

public class JsonAssertTest {
    @Test
    void jsonAssert_learningBasics() throws JSONException {
        String expectedResponse = """
                {"id":5,"description":"Most Suitable Programming Language in Data Science","options":["Java","Python","C++","Go"],"correctAnswer":"Python"}
                """;

        String actualResponse = """
            {
                "id": 5,
                "description": "Most Suitable Programming Language in Data Science",
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

        String expectedResponse1 = """
                {"id":5,"correctAnswer":"Python"}
                """;

        assertEquals(expectedResponse, actualResponse, true);
        assertEquals(expectedResponse1, actualResponse, false);
    }
}
