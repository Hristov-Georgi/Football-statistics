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

    public LongestPlayedPairOfPlayers findLongestPlayedPlayerPairInCommonMatches() {
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

        Map<String, Integer> resultMap = pairTotalPlayedTimeMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(100)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new ));

        String resultMapKey = extractMapKey(resultMap);

        return new LongestPlayedPairOfPlayers(extractPlayersFromMapKey(resultMapKey),
                resultMap.get(resultMapKey), commonMatchPlayed.get(resultMapKey));
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
        Long secondPlayerId = Long.parseLong(mapKeyIds.split("\\|")[0]);

        Player firstPlayer = this.playerService.getById(firstPlayerId);
        Player secondPlayer = this.playerService.getById(secondPlayerId);

        return List.of(firstPlayer, secondPlayer);
    }

    private String extractMapKey(Map<String, Integer> map) {
        String name = null;
        for (Map.Entry<String, Integer> m : map.entrySet()){
            name = m.getKey();
        }

        if (name == null) {
            throw new NullPointerException("Map Key is null");
        }
        return name;
    }

}
