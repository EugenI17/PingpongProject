package ro.hibyte.pingpong.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.hibyte.pingpong.dto.GameDto;
import ro.hibyte.pingpong.dto.PlayerDto;
import ro.hibyte.pingpong.services.GameService;
import ro.hibyte.pingpong.services.PlayerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/player")
public class PlayerController {

    private final PlayerService playerService;

   @GetMapping("/players")
    public List<PlayerDto> getAllPlayers()
    {
        return playerService.getAllPlayers();
    }
    @PostMapping("/createplayers")
    public Long createPlayer(@RequestBody PlayerDto playerDto) {

        return playerService.save(playerDto);
    }
    @GetMapping("/leaderboard")
    public List<PlayerDto> Leaderboard()
    {
        List<PlayerDto> players= playerService.getAllPlayers();
        return playerService.SortbyWins(players);
    }




}
