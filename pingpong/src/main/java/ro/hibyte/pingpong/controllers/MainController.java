package ro.hibyte.pingpong.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.hibyte.pingpong.dto.GameDto;
import ro.hibyte.pingpong.dto.PlayerDto;
import ro.hibyte.pingpong.services.GameService;
import ro.hibyte.pingpong.services.MainService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pingpong")
public class MainController {

    private final MainService mainService;
    private final GameService gameService;

   @GetMapping("/players")
    public List<PlayerDto> getAllPlayers()
    {
        return mainService.getAllPlayers();
    }
    @PostMapping("/createplayers")
    public Long createPlayer(@RequestBody PlayerDto playerDto) {

        return mainService.save(playerDto);
    }

  @GetMapping("/games")
   public List<GameDto> getAllGames()
    {
        return gameService.getAllGames();
    }
    @DeleteMapping("/deletegames")
    public void deleteGame(@RequestParam Long id)
    {
        gameService.deleteGame(id);
    }
    @PostMapping("/creategames")
    public void createGame(@RequestBody GameDto gameDto,@RequestParam Long player1Id,@RequestParam Long player2Id) {

        gameService.createGame(gameDto,player1Id,player2Id);
    }
    @GetMapping("/leaderboard")
    public List<PlayerDto> Leaderboard()
    {
        List<PlayerDto> players=mainService.getAllPlayers();
        return mainService.SortbyWins(players);
    }
    @DeleteMapping("/deleteallgames")
    public void deleteAllGames()
    {
        gameService.DeleteAllGames();
    }
    @GetMapping("/directmatch")
    public List<GameDto> DirectMatch(@RequestParam Long player1Id,@RequestParam Long player2Id)
    {
        return gameService.DirectMatch(player1Id,player2Id);
    }






}
