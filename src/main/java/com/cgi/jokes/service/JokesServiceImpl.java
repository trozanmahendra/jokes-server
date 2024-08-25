package com.cgi.jokes.service;

import com.cgi.jokes.dao.JokesDao;
import com.cgi.jokes.dto.Joke;
import com.cgi.jokes.exceptions.InvalidRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class JokesServiceImpl implements JokesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JokesServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;
@Autowired
    private JokesDao jokesDao;


    public ResponseEntity<Joke[]> getJokesFromApi(){
        return restTemplate
                .getForEntity("https://official-joke-api.appspot.com/random_ten",Joke[].class);
    }

    public List<Joke> getNjokes(Integer count) {
        LOGGER.info("Invoked getNJokes method");
        if(count > 100) throw new InvalidRequestException("Not more than 100 jokes per request are permitted");
        List<Joke> jokes = new ArrayList<>();
        int numberOfTimesJokesApiToBeCalled = 1;
        int remainder;
        if(count > 10){
            numberOfTimesJokesApiToBeCalled = count / 10;
            remainder = count % 10;
            numberOfTimesJokesApiToBeCalled = (remainder != 0) ? (numberOfTimesJokesApiToBeCalled + 1) : numberOfTimesJokesApiToBeCalled;
        }
        for(int i=0;i<numberOfTimesJokesApiToBeCalled;i++){
            jokes.addAll(Arrays.stream(Objects.requireNonNull(getJokesFromApi().getBody())).toList());
        }
        List<Joke> jokes1 = jokes.stream().sorted(Comparator.comparingInt(Joke::getId)).toList().subList(0,count);
        for(Joke joke : jokes1){
            jokesDao.save(joke);
        }
        return jokes1;
    }
}
