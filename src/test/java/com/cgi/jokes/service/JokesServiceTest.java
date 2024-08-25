package com.cgi.jokes.service;

import com.cgi.jokes.dao.JokesDao;
import com.cgi.jokes.entity.Joke;
import com.cgi.jokes.exceptions.InvalidRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JokesServiceTest {
    @MockBean
    private JokesDao jokesDao;
    @InjectMocks
    private JokesServiceImpl jokesService;

    @MockBean
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getJokesFromApiTest(){
        Joke[] jokes1 = {new Joke(),new Joke()};
        ResponseEntity<Joke[]> responseEntity = new ResponseEntity<>(jokes1, HttpStatus.OK);
        when(restTemplate.getForEntity("https://official-joke-api.appspot.com/random_ten", Joke[].class)).thenReturn(responseEntity);
        when(jokesService.getJokesFromApi()).thenReturn(responseEntity);
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void getNJokesTest(){
        List<Joke> jokes = Arrays.asList(new Joke(),new Joke());
        Joke[] jokes1 = {new Joke(1,"","",""),new Joke(2,"","","")};
        ResponseEntity<Joke[]> responseEntity = new ResponseEntity<>(jokes1, HttpStatus.OK);
        when(restTemplate.getForEntity("https://official-joke-api.appspot.com/random_ten", Joke[].class)).thenReturn(responseEntity);
        when(jokesService.getNjokes(2)).thenReturn(jokes);
        assertThrows(InvalidRequestException.class,() -> jokesService.getNjokes(101));
    }

}
