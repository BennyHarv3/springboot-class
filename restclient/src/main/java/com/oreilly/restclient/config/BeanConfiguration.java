package com.oreilly.restclient.config;

import java.util.Locale;
import java.text.NumberFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  public NumberFormat defaultCurrencyFormat() {
    return NumberFormat.getCurrencyInstance();
  }

  @Bean
  public NumberFormat deutschCurrencyFormat() {
    return NumberFormat.getCurrencyInstance(Locale.GERMANY);
  }
}
