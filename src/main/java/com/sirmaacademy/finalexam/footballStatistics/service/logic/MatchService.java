package com.sirmaacademy.finalexam.footballStatistics.service.logic;

import com.sirmaacademy.finalexam.footballStatistics.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public boolean isMatchExist(Long id) {
        return this.matchRepository.existsById(id);
    }

    public List<Long> getAllIds() {
        return this.matchRepository.findAllIds();
    }



}
