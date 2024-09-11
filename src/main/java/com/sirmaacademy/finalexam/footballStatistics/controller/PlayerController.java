package com.sirmaacademy.finalexam.footballStatistics.controller;

import com.sirmaacademy.finalexam.footballStatistics.exceptions.PlayerNotFoundException;
import com.sirmaacademy.finalexam.footballStatistics.model.request.PlayerDeleteRequest;
import com.sirmaacademy.finalexam.footballStatistics.model.request.PlayerRegisterRequest;
import com.sirmaacademy.finalexam.footballStatistics.model.request.PlayerUpdateRequest;
import com.sirmaacademy.finalexam.footballStatistics.model.response.PlayerDtoResponse;
import com.sirmaacademy.finalexam.footballStatistics.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/findByName")
    public ResponseEntity<List<PlayerDtoResponse>> findPlayersByName(@RequestParam String name) {

        try {
            return ResponseEntity.ok(this.playerService.getByName(name));
        } catch (PlayerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of());
        }

    }

    @PostMapping("/add")
    public ResponseEntity<String> addPlayer(@Valid @RequestBody PlayerRegisterRequest playerData) {
            this.playerService.addNewPlayer(playerData);

            return ResponseEntity.ok("Player '"
                    + playerData.getFullName()
                    + "' added successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePlayer(@Valid @RequestBody PlayerUpdateRequest playerData) {
        this.playerService.updatePlayer(playerData);

        return ResponseEntity.ok("Player: '" + playerData.getFullName()
                + "' successfully updated.");
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePlayer(@Valid @RequestBody PlayerDeleteRequest playerData) {
        this.playerService.deletePlayer(playerData);

        return ResponseEntity.ok("Player: '" + playerData.getFullName()
                + "' successfully deleted.");
    }

}
