package com.oreilly.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import static org.hamcrest.Matchers.containsInAnyOrder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.oreilly.persistence.entities.Officer;
import com.oreilly.persistence.entities.Rank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JdbcOfficerDAOTest {
  @Autowired
  @Qualifier("jdbcOfficerDAO")
  private OfficerDAO dao;

  @Test
  public void save() {
    Officer officer = new Officer(Rank.LIEUTENANT, "Nyota", "Uhuru");
    officer = dao.save(officer);
    assertNotNull(officer.getId());
  }

  @Test
  public void findByIdThatExists() throws Exception {
    Optional<Officer> officer = dao.findById(1);
    assertTrue(officer.isPresent());
    assertEquals(1, officer.get().getId().intValue());
  }

  @Test
  public void findByIdThatDoesNotExist() throws Exception {
    Optional<Officer> officer = dao.findById(999);
    assertFalse(officer.isPresent());
  }

  @Test
  public void count() throws Exception {
    assertEquals(5, dao.count());
  }

  @Test
  public void findAll() throws Exception {
    List<String> dbNames = dao.findAll().stream().map(Officer::getLast).collect(Collectors.toList());
    assertThat(dbNames, containsInAnyOrder("Kirk", "Picard", "Sisko", "Janeway", "Archer"));
  }

  @Test
  public void delete() throws Exception {
    IntStream.rangeClosed(1, 5).forEach(id -> {
      Optional<Officer> officer = dao.findById(id);
      assertTrue(officer.isPresent());
      dao.delete(officer.get());
    });
    assertEquals(0, dao.count());
  }

  @Test
  public void existsById() throws Exception {
    IntStream.rangeClosed(1, 5).forEach(id -> assertTrue(String.format("%d should exist", id), dao.existsById(id)));
  }
}
