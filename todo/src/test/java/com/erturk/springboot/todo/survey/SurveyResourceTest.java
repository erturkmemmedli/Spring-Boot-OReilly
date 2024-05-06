package com.erturk.springboot.todo.survey;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SurveyResource.class)
//@AutoConfigureMockMvc(addFilters = false)
public class SurveyResourceTest {
    @MockBean
    private SurveyService surveyService;
    @Autowired
    private MockMvc mockMvc;
    private static final String SPECIFIC_QUESTION_URL = "http://localhost:8080/surveys/1/questions/3";
    private static final String GENERIC_QUESTIONS_URL = "/surveys/2/questions";

    @Test
    @WithMockUser(username = "Erturk", password = "meselcun", roles = "USER")
    void retrieveQuestionById_404Scenario() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(SPECIFIC_QUESTION_URL)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(404, mvcResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser(username = "Erturk", password = "meselcun", roles = "USER")
    void retrieveQuestionById_basicScenario() throws Exception {
        //String accessToken = "my-access-token";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(SPECIFIC_QUESTION_URL)
                .accept(MediaType.APPLICATION_JSON);
                //.header("Authorization", "Bearer " + accessToken);

        Question question = new Question(3,
                "Most Popular DevOps Tool",
                Arrays.asList("Kubernetes", "Docker", "Terraform", "Azure DevOps"),
                "Kubernetes");

        when(surveyService.retrieveQuestionById(1, 3)).thenReturn(question);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        String expectedResponse = """
                {
                    "id":3,
                    "description":"Most Popular DevOps Tool",
                    "options":
                        [
                            "Kubernetes",
                            "Docker",
                            "Terraform",
                            "Azure DevOps"
                        ],
                    "correctAnswer":"Kubernetes"
                }
                """;

        assertEquals(200, mvcResult.getResponse().getStatus());
        JSONAssert.assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString(), false);
    }

    @Test
    @WithMockUser(username = "Erturk", password = "meselcun", roles = "ADMIN")
    void addQuestion_basicScenario() throws Exception {
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

        when(surveyService.addQuestion(anyInt(), any())).thenReturn(0);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(GENERIC_QUESTIONS_URL)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf());

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andReturn();
        String locationHeader = mvcResult.getResponse().getHeader("Location");

        assertEquals(201, mvcResult.getResponse().getStatus());
        assert locationHeader != null;
        assertTrue(locationHeader.contains("/surveys/2/questions"));
    }
}
