package com.sirmaacademy.finalexam.footballStatistics.repository;

import com.sirmaacademy.finalexam.footballStatistics.model.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CSVToDatabaseInsert {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertTeamsCSVFile(List<Team> teams) {

        for (Team t : teams) {
            entityManager.createNativeQuery("INSERT INTO teams (id, name, manager_full_name, football_group)" +
                    " VALUES (?, ?, ?, ?)")
                    .setParameter(1, t.getId())
                    .setParameter(2, t.getName())
                    .setParameter(3, t.getManagerFullName())
                    .setParameter(4, t.getFootballGroup().getValue())
                    .executeUpdate();
        }

    }



}
