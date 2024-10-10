package com.sirmaacademy.finalexam.footballStatistics.service.logic;

import com.sirmaacademy.finalexam.footballStatistics.model.dto.CommonPlayedMatchDto;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.*;
import com.sirmaacademy.finalexam.footballStatistics.model.response.*;
import com.sirmaacademy.finalexam.footballStatistics.service.PlayerService;
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
    public LongestPlayedPairOfPlayersService(RecordsService recordsService, MatchService matchService,
                                             PlayerService playerService) {
        this.recordsService = recordsService;
        this.matchService = matchService;
        this.playerService = playerService;
    }

    /**
     * Main logic to extract all pair of players who have played together in common matches.
     */
    public List<LongestPlayedPairOfPlayersResponse> findLongestPlayedPlayerPairInCommonMatches() {

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
            Integer matchDuration = getTotalMatchDuration(m.getKey());
            List<Records> recordsList = m.getValue();

            for (int current = 0; current < m.getValue().size() - 1; current++) {
                Records currentRecord = recordsList.get(current);

                for (int next = current + 1; next < m.getValue().size(); next++) {

                    Records nextRecord = recordsList.get(next);

                    String playersPairNames = concatPlayerIds(currentRecord.getPlayer().getId(), nextRecord.getPlayer().getId());

                    int timePlayedTogether = timePlayedTogetherInCurrentMatch(currentRecord, nextRecord);

                    pairTotalPlayedTimeMap.putIfAbsent(playersPairNames, 0);
                    pairTotalPlayedTimeMap.put(playersPairNames,
                            pairTotalPlayedTimeMap.get(playersPairNames) + timePlayedTogether);

                    MatchDtoResponse matchDtoResponse = mapMatchToMatchDtoResponse(currentRecord.getMatch());

                    CommonPlayedMatchDto currentMatch =
                            new CommonPlayedMatchDto(matchDtoResponse, timePlayedTogether, matchDuration);

                    commonMatchPlayed.putIfAbsent(playersPairNames, new ArrayList<>());
                    commonMatchPlayed.get(playersPairNames).add(currentMatch);
                }

            }

        }
        pairTotalPlayedTimeMap = sortMapByValueInteger(pairTotalPlayedTimeMap);

        return getListOfLongestPlayedPlayers(pairTotalPlayedTimeMap, commonMatchPlayed);
    }

    /**
     * Return Map with Key - matchID and Value - List<Records> for that match.
     */
    private Map<Long, List<Records>> getRecordsGroupedByMatchId() {
        Map<Long, List<Records>> recordsByMatchIdMap = new HashMap<>();
        List<Long> matchIdList = this.matchService.getAllIds();

        for (Long id : matchIdList) {
            List<Records> recordsByIdList = this.recordsService.getAllByMatchId(id);

            recordsByMatchIdMap.put(id, recordsByIdList);
        }
        return recordsByMatchIdMap;
    }

    /**
     * Concatenates players id's in unique username, which is used as Key in the storage Maps.
     */
    private String concatPlayerIds(Long firstPlayerId, Long secondPlayerId) {
        return String.join("|",
                String.valueOf(firstPlayerId),
                String.valueOf(secondPlayerId));
    }

    /**
     * Calculate and return total played time together for players pair in common match.
     */
    private Integer timePlayedTogetherInCurrentMatch(Records firstPlayer, Records secondPlayer) {

        int fromTime = Math.max(firstPlayer.getFromMinutes(), secondPlayer.getFromMinutes());
        int toTime = Math.min(firstPlayer.getToMinutes(), secondPlayer.getToMinutes());

        int num = Math.subtractExact(toTime, fromTime);
        return Math.max(num, 0);
    }

    /**
     * Return total match duration for single match.
     * Data is fetched from database.
     */
    private int getTotalMatchDuration(Long matchId) {
        return this.recordsService.getTotalMatchDuration(matchId);
    }

    /**
     * Map single Match object to DTO object.
     */
    private MatchDtoResponse mapMatchToMatchDtoResponse(Match match) {
        List<ScoreDtoResponse> scoresDtoList = new ArrayList<>();

        for (Score s : match.getScores()) {
            scoresDtoList.add(mapScoreToscoreDtoResponse(s));
        }

        return new MatchDtoResponse(match.getLocalDate(), scoresDtoList);
    }

    /**
     * Map Score object to DTO object.
     */
    private ScoreDtoResponse mapScoreToscoreDtoResponse(Score score) {
        TeamDtoResponse teamDto = mapTeamToTeamDtoResponse(score.getTeam());

        return new ScoreDtoResponse(teamDto, score.getScoredGoals());
    }

    /**
     * Map Team object to DTO object.
     */
    private TeamDtoResponse mapTeamToTeamDtoResponse(Team team) {
        return new TeamDtoResponse(team.getName(), team.getManagerFullName(), team.getFootballGroup());
    }

    /**
     * Extract Player objects from map key value.
     * Map Player object to DTO object.
     */
    private List<PlayerStatisticDtoResponse> extractPlayersResponseFromMapKey(String mapKeyIds) {
        Long firstPlayerId = Long.parseLong(mapKeyIds.split("\\|")[0]);
        Long secondPlayerId = Long.parseLong(mapKeyIds.split("\\|")[1]);

        Player firstPlayer = this.playerService.getById(firstPlayerId);
        Player secondPlayer = this.playerService.getById(secondPlayerId);

        PlayerStatisticDtoResponse firstPlayerResponse = new PlayerStatisticDtoResponse(
                firstPlayer.getTeamNumber(), firstPlayer.getFieldPosition(), firstPlayer.getFullName());
        PlayerStatisticDtoResponse secondPlayerResponse = new PlayerStatisticDtoResponse(
                secondPlayer.getTeamNumber(), secondPlayer.getFieldPosition(), secondPlayer.getFullName());

        return List.of(firstPlayerResponse, secondPlayerResponse);
    }

    /**
     * Extract List<CommonPlayedMatchDto> for specific pair of players.
     */
    private List<CommonPlayedMatchDto> extractListOfCommonPlayedMatchesById(String id,
                                                                            Map<String, List<CommonPlayedMatchDto>> commonPlayedMatches) {

        for (Map.Entry<String, List<CommonPlayedMatchDto>> m : commonPlayedMatches.entrySet()) {

            if (m.getKey().equals(id)) {
                return m.getValue();
            }

        }
        throw new RuntimeException("Record with id '" + id + "' was not found.");
    }

    /**
     * Sort a Map by Value in reversed order.
     */
    private Map<String, Integer> sortMapByValueInteger(Map<String, Integer> playersMap) {
        return playersMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    /**
     * Return refined List of longest played pair of players in common matches.
     * Return only the players with the longest time. Time is compared by the topmost value.
     */
    private List<LongestPlayedPairOfPlayersResponse> getListOfLongestPlayedPlayers(
            Map<String, Integer> sortedPlayersPairs,
            Map<String, List<CommonPlayedMatchDto>> commonPlayedMatches) {

        Integer duration = null;
        List<LongestPlayedPairOfPlayersResponse> longestPlayList = new ArrayList<>();

        for (Map.Entry<String, Integer> spp : sortedPlayersPairs.entrySet()) {

            if (duration == null) {
                duration = spp.getValue();
            }

            if (spp.getValue() < duration) {
                return longestPlayList;
            }

            List<PlayerStatisticDtoResponse> playersDto = extractPlayersResponseFromMapKey(spp.getKey());
            Integer totalPlayedTimeTogether = spp.getValue();
            List<CommonPlayedMatchDto> matchesList = extractListOfCommonPlayedMatchesById(
                    spp.getKey(), commonPlayedMatches);

            LongestPlayedPairOfPlayersResponse longestPlay = new LongestPlayedPairOfPlayersResponse(
                    playersDto, totalPlayedTimeTogether, matchesList);
            longestPlayList.add(longestPlay);
        }
        return longestPlayList;
    }

}
