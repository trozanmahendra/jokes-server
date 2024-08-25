package com.cgi.jokes.controller;

import com.cgi.jokes.entity.Joke;
import com.cgi.jokes.service.JokesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(JokesController.class)
public class JokesControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JokesService jokesService;
    @InjectMocks
    private JokesController jokesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getNJokesTest() throws Exception {
        List<Joke> jokes = new ArrayList<>();
        jokes.add(Joke.builder().build());
        jokes.add(Joke.builder().build());
        when(jokesService.getNjokes(2)).thenReturn(jokes);
        mockMvc.perform(MockMvcRequestBuilders.get("/jokes").param("count","2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status").value("Success"))
                .andExpect(jsonPath("$.message").value("2 jokes fetched"))
                .andExpect(jsonPath("$.obj").isArray());
    }

}
