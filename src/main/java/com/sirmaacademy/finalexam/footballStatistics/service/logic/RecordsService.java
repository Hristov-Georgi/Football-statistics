package com.sirmaacademy.finalexam.footballStatistics.service.logic;

import com.sirmaacademy.finalexam.footballStatistics.model.entity.Records;
import com.sirmaacademy.finalexam.footballStatistics.repository.RecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordsService {

    private final RecordsRepository recordsRepository;

    @Autowired
    public RecordsService(RecordsRepository recordsRepository) {
        this.recordsRepository = recordsRepository;
    }

    public List<Records> getAllByMatchId(Long id) {
        return this.recordsRepository.findAllByMatchId(id);
    }

    public int getTotalMatchDuration(Long matchId) {
        return this.recordsRepository.findTotalMatchDuration(matchId);
    }
}
