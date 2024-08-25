package com.cgi.jokes.dao;

import com.cgi.jokes.dto.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JokesDao extends JpaRepository<Joke,Integer> {

}
