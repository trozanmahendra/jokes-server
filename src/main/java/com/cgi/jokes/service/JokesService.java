package com.cgi.jokes.service;

import com.cgi.jokes.entity.Joke;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JokesService {
    Object getJokesFromApi();
    List<Joke> getNjokes(Integer count);
}
