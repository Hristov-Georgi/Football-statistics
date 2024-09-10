package com.sirmaacademy.finalexam.footballStatistics.service;

import com.sirmaacademy.finalexam.footballStatistics.model.entity.Team;
import com.sirmaacademy.finalexam.footballStatistics.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team findById(Long id) {
        return this.teamRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Team with id: " + id + " not found."));
    }

    public boolean isTeamExist(Long id) {
        return this.teamRepository.existsById(id);
    }

}
