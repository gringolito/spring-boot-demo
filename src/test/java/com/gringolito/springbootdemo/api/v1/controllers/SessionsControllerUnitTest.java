package com.gringolito.springbootdemo.api.v1.controllers;

import com.gringolito.springbootdemo.repositories.SessionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionsController.class)
public class SessionsControllerUnitTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private SessionRepository sessionRepository;

    @Test
    public void listOk() throws Exception {
        mvc.perform(get("/api/v1/sessions"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(sessionRepository, times(1)).findAll();
    }

    @Test(expected = Exception.class)
    public void listFail() throws Exception {
        doThrow(Exception.class).when(sessionRepository).findAll();

        mvc.perform(get("/api/v1/sessions"));
    }
}
