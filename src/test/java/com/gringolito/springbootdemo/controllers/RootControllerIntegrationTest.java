package com.gringolito.springbootdemo.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RootControllerIntegrationTest {
    @Autowired
    TestRestTemplate rest;
    @LocalServerPort
    private int port;
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Test
    public void getInfo() throws Exception {
        String baseUrl = "http://localhost:" + port + "/";

        ResponseEntity<String> response = rest.getForEntity(baseUrl, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        JsonNode json = new ObjectMapper().readTree(response.getBody());
        assertEquals(appName, json.path("name").asText());
        assertEquals(appVersion, json.path("version").asText());
    }

}
