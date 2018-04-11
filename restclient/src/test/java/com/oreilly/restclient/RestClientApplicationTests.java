package com.oreilly.restclient;

import java.text.NumberFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestClientApplicationTests {
  @Autowired
  private ApplicationContext ctx;

  @Autowired
  @Qualifier("defaultCurrencyFormat")
  private NumberFormat nf;

  @Test
  public void contextLoads() {
    System.out.println(ctx.getClass().getName());
    System.out.println("There are " + ctx.getBeanDefinitionCount() + " beans in the application context");
    for (String name : ctx.getBeanDefinitionNames()) {
      System.out.println(name);
    }
  }

  @Test
  public void checkDefaultCurrencyFormat() {
    double amount = 12345678.901234;
    System.out.println(nf.format(amount));
  }

  @Test
  public void checkDeutschCurrencyFormat() {
    double amount = 12345678.901234;
    NumberFormat formatter = ctx.getBean("deutschCurrencyFormat", NumberFormat.class);
    System.out.println(formatter.format(amount));
  }
}
