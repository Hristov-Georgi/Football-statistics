package com.sirmaacademy.finalexam.footballStatistics.service;

import com.sirmaacademy.finalexam.footballStatistics.model.dto.CommonPlayedMatchDto;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.Player;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.Records;
import com.sirmaacademy.finalexam.footballStatistics.model.response.LongestPlayedPairOfPlayers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LongestPlayedPairOfPlayersService {

    private final RecordsService recordsService;
    private final MatchService matchService;
    private final PlayerService playerService;

    @Autowired
    public LongestPlayedPairOfPlayersService(RecordsService recordsService, MatchService matchService, PlayerService playerService) {
        this.recordsService = recordsService;
        this.matchService = matchService;
        this.playerService = playerService;
    }

    public List<LongestPlayedPairOfPlayers> findLongestPlayedPlayerPairInCommonMatches() {

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

                    if (isTrue) {                                                                //eachRecord
                        String playersPairNames = concatPlayerIds(firstRecord.getPlayer().getId(), eachRecord.getPlayer().getId());

                        int timePlayedTogether = timePlayedTogetherInCurrentMatch(firstRecord, eachRecord); //eachRecord

                        pairTotalPlayedTimeMap.putIfAbsent(playersPairNames, 0);
                        pairTotalPlayedTimeMap.put(playersPairNames,
                                pairTotalPlayedTimeMap.get(playersPairNames) + timePlayedTogether);

                        CommonPlayedMatchDto currentMatch =
                                new CommonPlayedMatchDto(firstRecord.getMatch(), timePlayedTogether, matchDuration);

                        commonMatchPlayed.putIfAbsent(playersPairNames, new ArrayList<>());
                        commonMatchPlayed.get(playersPairNames).add(currentMatch);
                    }

                }
                isTrue = false;
                firstRecord = nextRecord;
            }

        }
        //TODO: remove
//        Map<String, List<CommonPlayedMatchDto>> commonMap = commonMatchPlayed
//                .entrySet()
//                .stream()
//                .sorted(Comparator.comparingInt(v -> v.getValue().size()))
//                .filter(v -> v.getValue().size() > 5)
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//                        (e1, e2) -> e1, LinkedHashMap::new));

        pairTotalPlayedTimeMap = sortPairTotalPlayedMap(pairTotalPlayedTimeMap);

        return getListOfLongestPlayedPlayers(pairTotalPlayedTimeMap, commonMatchPlayed);
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

    private List<Player> extractPlayersFromMapKey(String mapKeyIds) {
        Long firstPlayerId = Long.parseLong(mapKeyIds.split("\\|")[0]);
        Long secondPlayerId = Long.parseLong(mapKeyIds.split("\\|")[1]);

        Player firstPlayer = this.playerService.getById(firstPlayerId);
        Player secondPlayer = this.playerService.getById(secondPlayerId);

        return List.of(firstPlayer, secondPlayer);
    }

    private List<CommonPlayedMatchDto> extractListOfCommonPlayedMatchesById(String id,
                                        Map<String, List<CommonPlayedMatchDto>> commonPlayedMatches) {

        for (Map.Entry<String, List<CommonPlayedMatchDto>> m : commonPlayedMatches.entrySet()) {

            if (m.getKey().equals(id)) {
                return m.getValue();
            }

        }
        throw new RuntimeException("Record with id '" + id + "' was not found.");
    }

//    //TODO: remove
//    private String extractMapKey(Map<String, Integer> map) {
//        String name = null;
//        for (Map.Entry<String, Integer> m : map.entrySet()){
//            name = m.getKey();
//        }
//
//        if (name == null) {
//            throw new NullPointerException("Map Key is null");
//        }
//        return name;
//    }

    //TODO: set better name :)
    private Map<String, Integer> sortPairTotalPlayedMap(Map<String, Integer> playersMap) {
        return playersMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    private List<LongestPlayedPairOfPlayers> getListOfLongestPlayedPlayers(
            Map<String, Integer> sortedPlayersPairs,
            Map<String, List<CommonPlayedMatchDto>> commonPlayedMatches) {

        Integer duration = null;
        List<LongestPlayedPairOfPlayers> longestPlayList = new ArrayList<>();

        for (Map.Entry<String, Integer> spp : sortedPlayersPairs.entrySet()) {

            if (duration == null) {
                duration = spp.getValue();
            }

            if (spp.getValue() < duration) {
                return longestPlayList;
            }

            List<Player> players = extractPlayersFromMapKey(spp.getKey());
            Integer totalPlayedTimeTogether = spp.getValue();
            List<CommonPlayedMatchDto> matchesList = extractListOfCommonPlayedMatchesById(
                    spp.getKey(), commonPlayedMatches);

            LongestPlayedPairOfPlayers longestPlay = new LongestPlayedPairOfPlayers(
                    players, totalPlayedTimeTogether, matchesList);
            longestPlayList.add(longestPlay);
        }
        return longestPlayList;
    }

}
