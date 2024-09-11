Sirma Academy Final Exam

My understanding of the task is to identify all possible pairs of players who have played together for the longest time
in common matches. In other words, if there are 4 players who have participated in one match, I need to find all 
possible combinations between them (e.g., pairing Player 1 with Player 2, Player 1 with Player 3,..., Player 2 with
Player 3,..., Player 3 with Player 4, etc.) and calculate the time they spent together in the same match. Once I have 
the unique pairs, I can check all other matches.

My approach to solving the problem is as follows:

1. Extract match IDs: I begin by extracting all the match IDs, as they are unique values. I store them in a List<Long>.

2. Group records by match ID: I then iterate through the list of match IDs, extracting all player records from database
grouped by each match ID. I store the match ID as the key and the corresponding records as a List<Records> in a map.

3. Implement the main algorithm: In the findLongestPlayedPlayerPairInCommonMatches method, I execute the main algorithm,
which identifies unique pairs of players by combining their ID numbers into a string. I use this string as the key in
two HashMaps. The first map holds an Integer value representing the total time the players spent together in common
matches. The second map holds a List<CommonPlayedMatchDto>, which stores data for the common matches, including the 
match, the total time the players spent together, and the total match duration.

4. Avoid duplicate pairs: To avoid creating duplicate pairs (such as pairing Player 1 with Player 2 and Player 2 with 
Player 1, which are the same pair), I use a boolean flag to exclude pairs that have already been added.

5. Sort by total time played: After generating all possible combinations, I sort the first map (Map<String, Integer>)
by the total time played together, in descending order.

6. Extract the top pairs: I then iterate through the sorted map to extract only the pairs with the longest time played
together. I compare all values to the topmost value in the map. From the map’s key, I extract the players' DTO objects,
the total time played together, and the list of common matches, and store this data in a list of 
LongestPlayedPairOfPlayersResponse.

7. Return the result: Finally, the list containing the results is returned to the PlayerStatisticsController, where it
can be accessed via a GET request.