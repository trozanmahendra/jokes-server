package com.cgi.jokes.controller;

import com.cgi.jokes.dto.ApiResponse;
import com.cgi.jokes.dto.Joke;
import com.cgi.jokes.service.JokesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JokesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JokesController.class);


    @Autowired
    private JokesService jokesService;


    @GetMapping("/jokes")
    public ResponseEntity<ApiResponse> getNJokes(@RequestParam Integer count) {
        List<Joke> jokes = jokesService.getNjokes(count);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().obj(jokes).status("Success").message("%d jokes fetched".formatted(jokes.size())).build());
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello All");
    }

}
