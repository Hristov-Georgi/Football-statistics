package com.sirmaacademy.finalexam.footballStatistics.service;

import com.sirmaacademy.finalexam.footballStatistics.model.dto.CommonPlayedMatchDto;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.Records;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LongestPlayedPairOfPlayersService {

    private final RecordsService recordsService;
    private final MatchService matchService;

    @Autowired
    public LongestPlayedPairOfPlayersService(RecordsService recordsService, MatchService matchService) {
        this.recordsService = recordsService;
        this.matchService = matchService;
    }

    //TODO: change return value
    public void  findLongestPlayedPlayerPairInCommonMatches() {
        /**
         * pairTotalPlayedTimeMap and commonMatchesPlayed keys are concatenation
         * of firstPlayerId + | + secondPlayerId (for example: "1|2")
         */
        Map<String, Integer> pairTotalPlayedTimeMap = new HashMap<>();
        Map<String, List<CommonPlayedMatchDto>> commonMatchPlayed = new HashMap<>();

        /**
         * Long key - Match_Id value.
         * List<Records> - contains all data for this match id. All Records are ordered by Player id in ASC order.
         */
        Map<Long, List<Records>> recordsByMatchIdMap = getRecordsGroupedByMatchId();

        for (Map.Entry<Long, List<Records>> m : recordsByMatchIdMap.entrySet()) {

            Integer matchDuration = null;
            Records firstRecord = null;

            for (Records nextRecord : m.getValue()) {

                if (firstRecord == null) {
                    firstRecord = nextRecord;
                    matchDuration = getTotalMatchDuration(nextRecord.getMatch().getId());
                    continue;
                }

                if (firstRecord == nextRecord) {
                    break;
                }

                boolean isTrue = false;
                for (Records eachRecord : m.getValue()) {

                    if (firstRecord == eachRecord) {
                        isTrue = true;
                        continue;
                    }

                    if (isTrue) {
                        String playersPairNames = concatPlayerIds(firstRecord.getPlayer().getId(), eachRecord.getPlayer().getId());
                        int timePlayedTogether = timePlayedTogetherInCurrentMatch(firstRecord, eachRecord);

                        pairTotalPlayedTimeMap.putIfAbsent(playersPairNames, 0);
                        pairTotalPlayedTimeMap.put(playersPairNames,
                                pairTotalPlayedTimeMap.get(playersPairNames) + timePlayedTogether);

                        CommonPlayedMatchDto currentMatch =
                                new CommonPlayedMatchDto(firstRecord.getMatch().getId(), timePlayedTogether, matchDuration);

                        commonMatchPlayed.putIfAbsent(playersPairNames, new ArrayList<>());
                        commonMatchPlayed.get(playersPairNames).add(currentMatch);
                    }

                }
                isTrue = false;
                firstRecord = nextRecord;
            }

        }

        System.out.println();

    }

    private Map<Long, List<Records>> getRecordsGroupedByMatchId() {
        Map<Long, List<Records>> recordsByMatchIdMap = new HashMap<>();
        List<Long> matchIdList = this.matchService.getAllIds();

        for (Long id : matchIdList) {
            List<Records> recordsByIdList = this.recordsService.getAllByMatchId(id);

            recordsByMatchIdMap.put(id, recordsByIdList);
        }
        return recordsByMatchIdMap;
    }

    private String concatPlayerIds(Long firstPlayerId, Long secondPlayerId) {
        return String.join("|",
                String.valueOf(firstPlayerId),
                String.valueOf(secondPlayerId));
    }

    private Integer timePlayedTogetherInCurrentMatch(Records firstPlayer, Records secondPlayer) {

        int fromTime = Math.max(firstPlayer.getFromMinutes(), secondPlayer.getFromMinutes());
        int toTime = Math.min(firstPlayer.getToMinutes(), secondPlayer.getToMinutes());

        int num = Math.subtractExact(toTime, fromTime);
        return Math.max(num, 0);
    }

    private int getTotalMatchDuration(Long matchId) {
        return this.recordsService.getTotalMatchDuration(matchId);
    }







}
