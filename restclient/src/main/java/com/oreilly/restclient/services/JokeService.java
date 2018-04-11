package com.oreilly.restclient.services;

import com.oreilly.restclient.json.JokeResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class JokeService {
  private static final String BASE = "http://api.icndb.com/jokes/random?limitTo=[nerdy]";
  private RestTemplate restTemplate;

  private Logger logger = LoggerFactory.getLogger(JokeResponse.class);

  @Autowired
  public JokeService(RestTemplateBuilder builder) {
    restTemplate = builder.build();
  }

  public String getJoke(String first, String last) {
    String url = String.format("%s&firstName=%s&lastName=%s", BASE, first, last);
    String joke = restTemplate.getForObject(url, JokeResponse.class).getValue().getJoke();
    logger.info(joke);
    return joke;
  }
}
