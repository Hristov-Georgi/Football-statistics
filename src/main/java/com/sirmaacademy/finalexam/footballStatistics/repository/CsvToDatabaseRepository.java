package com.sirmaacademy.finalexam.footballStatistics.repository;

import com.sirmaacademy.finalexam.footballStatistics.model.entity.Match;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.Player;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.Records;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CsvToDatabaseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveRecords(List<Records> records) {

        for (Records r : records) {
            entityManager.createNativeQuery("INSERT INTO records (id, from_minutes, to_minutes, match_id, player_id)" +
                            " VALUES (?, ?, ?, ?, ?)")
                    .setParameter(1, r.getId())
                    .setParameter(2, r.getFromMinutes())
                    .setParameter(3, r.getToMinutes())
                    .setParameter(4, r.getMatchId())
                    .setParameter(5, r.getPlayerId())
                    .executeUpdate();
        }

    }

    @Transactional
    public void saveMatches(List<Match> matches) {

        for (Match m : matches) {
            entityManager.createNativeQuery("INSERT INTO matches (id, local_date)" +
                            " VALUES (?, ?)")
                    .setParameter(1, m.getId())
                    .setParameter(2, m.getLocalDate())
                    .executeUpdate();
        }

    }

    @Transactional
    public void savePlayers(List<Player> players) {

        for (Player p : players) {
            entityManager.createNativeQuery("INSERT INTO players (id, field_position, full_name, team_number, team_id)" +
                            " VALUES (?, ?, ?, ?, ?)")
                    .setParameter(1, p.getId())
                    .setParameter(2, p.getFieldPosition().name())
                    .setParameter(3, p.getFullName())
                    .setParameter(4, p.getTeamNumber())
                    .setParameter(5, p.getTeamId())
                    .executeUpdate();
        }

    }

    @Transactional
    public void saveTeams(List<Team> teams) {

        for (Team t : teams) {
            entityManager.createNativeQuery("INSERT INTO teams (id, name, manager_full_name, football_group)" +
                            " VALUES (?, ?, ?, ?)")
                    .setParameter(1, t.getId())
                    .setParameter(2, t.getName())
                    .setParameter(3, t.getManagerFullName())
                    .setParameter(4, t.getFootballGroup().name())
                    .executeUpdate();
        }

    }

}
