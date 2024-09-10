package com.sirmaacademy.finalexam.footballStatistics.repository;

import com.sirmaacademy.finalexam.footballStatistics.model.entity.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordsRepository extends JpaRepository<Records, Long> {

    @Query("SELECT r FROM Records r WHERE r.match.id = :id ORDER BY r.player.id ASC")
    List<Records> findAllByMatchId(@Param("id") Long matchId);

    @Query("SELECT max(r.toMinutes) FROM Records r WHERE r.match.id = :id")
    int findTotalMatchDuration(@Param("id") Long matchId);
}
