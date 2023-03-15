package ro.hibyte.pingpong.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.hibyte.pingpong.dto.GameDto;
import ro.hibyte.pingpong.services.GameService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/game")

public class GameController {
    private final GameService gameService;
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
