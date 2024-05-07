package com.example.demo;

import com.movies.descriptions.moviedescriptions.MovieDescriptionsApplication;
import com.movies.descriptions.moviedescriptions.MovieDescriptionsController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = MovieDescriptionsApplication.class)
@AutoConfigureMessageVerifier
public class ContractTestBase {

    @Autowired
    private MovieDescriptionsController movieDescriptionsController;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(MockMvcBuilders.standaloneSetup(movieDescriptionsController));
    }

}
