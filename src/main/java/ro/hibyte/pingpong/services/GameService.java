package ro.hibyte.pingpong.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.hibyte.pingpong.dto.GameDto;
import ro.hibyte.pingpong.entities.Game;
import ro.hibyte.pingpong.entities.Player;
import ro.hibyte.pingpong.repositories.GameRepository;
import ro.hibyte.pingpong.repositories.PlayerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class GameService {
    private final GameRepository gameRepository;
    private final PlayerRepository PlayerRepository;
   public void createGame(GameDto gameDto,Long player1Id,Long player2Id) {


        Game game = Game.builder()
                .player1Id(player1Id)
                .player2Id(player2Id)
                .player1Score(gameDto.getPlayer1Score())
                .player2Score(gameDto.getPlayer2Score())
                .build();


      if(gameDto.getPlayer1Score()>gameDto.getPlayer2Score())
      {
           gameDto.setWinner(PlayerRepository.findById(player1Id).get().getName());
      }
       else
      {
           gameDto.setWinner(PlayerRepository.findById(player2Id).get().getName());
      }
       game.setWinner(WinnerId(gameDto.getWinner()));
       RaiseWins(WinnerId(gameDto.getWinner()));
       gameRepository.save(game);
    }

   public String WinnerId(String winner){
        List<Player> players = PlayerRepository.findAll();
        for(Player player:players){
            if(player.getName().equals(winner)){
                return player.getName();
            }
        }
        return null;
    }
   public void RaiseWins(String winner){
        List<Player> players = PlayerRepository.findAll();
        for(Player player:players){
            if(player.getName().equals(winner)){
                player.setWins(player.getWins()+1);
            }
        }
   }




   public List<GameDto> getAllGames()
    {
        return gameRepository.findAll().stream().map(game -> GameDto.builder()
                        .id(game.getId())
                        .player1Id(game.getPlayer1Id())
                        .player2Id(game.getPlayer2Id())
                        .player1Score(game.getPlayer1Score())
                        .player2Score(game.getPlayer2Score())
                        .winner(game.getWinner())
                        .build())
                .collect(Collectors.toList());
    }
    @Transactional
    public void deleteGame(Long id) {
        SetWins(id);
        gameRepository.deleteById(id);
    }
    public void SetWins(Long id){
        Game game= gameRepository.findById(id).get();
        if(game.getWinner().equals(PlayerRepository.findById(game.getPlayer1Id()).get().getName())){
            PlayerRepository.findById(game.getPlayer1Id()).get().setWins(PlayerRepository.findById(game.getPlayer1Id()).get().getWins()-1);
        }
        else{
            PlayerRepository.findById(game.getPlayer2Id()).get().setWins(PlayerRepository.findById(game.getPlayer2Id()).get().getWins()-1);
        }


    }
    @Transactional
    public void DeleteAllGames(){
        gameRepository.deleteAll();
        List<Player> players = PlayerRepository.findAll();
        for(Player player:players){
            player.setWins(0);
        }
    }
    public List<GameDto> DirectMatch(Long player1Id,Long player2Id){
        return gameRepository.findAll().stream().filter(game -> game.getPlayer1Id().equals(player1Id) && game.getPlayer2Id().equals(player2Id)).map(game -> GameDto.builder()
                .id(game.getId())
                .player1Id(game.getPlayer1Id())
                .player2Id(game.getPlayer2Id())
                .player1Score(game.getPlayer1Score())
                .player2Score(game.getPlayer2Score())
                .winner(game.getWinner())
                .build())
                .collect(Collectors.toList());

    }


}
