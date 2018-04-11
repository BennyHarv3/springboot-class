package com.oreilly.persistence.dao;

import java.util.List;

import com.oreilly.persistence.entities.Officer;
import com.oreilly.persistence.entities.Rank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface OfficerRepository extends JpaRepository<Officer, Integer> {
  List<Officer> findByRank(@Param("rank") Rank rank);

  List<Officer> findByLast(@Param("last") String last);
}