package com.sirmaacademy.finalexam.footballStatistics.repository;

import com.sirmaacademy.finalexam.footballStatistics.model.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("SELECT m.id FROM Match m")
    List<Long> findAllIds();


}
