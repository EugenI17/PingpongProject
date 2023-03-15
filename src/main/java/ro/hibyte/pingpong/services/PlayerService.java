package ro.hibyte.pingpong.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.hibyte.pingpong.dto.PlayerDto;
import ro.hibyte.pingpong.entities.Player;
import ro.hibyte.pingpong.repositories.PlayerRepository;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PlayerService {


    private final PlayerRepository playerRepository;

    public Long save(PlayerDto playerDto) {
        Player player = Player.builder()
                .name(playerDto.getName())
                .wins(playerDto.getWins())
                .build();

        player = playerRepository.save(player);
        return player.getId();
    }

  public List<PlayerDto> getAllPlayers()
    {
        return playerRepository.findAll().stream().map(player -> PlayerDto.builder()
                        .id(player.getId())
                        .name(player.getName())
                        .wins(player.getWins())
                        .build())
                .collect(Collectors.toList());
    }

    public List<PlayerDto> SortbyWins(List<PlayerDto> players){
        for(int i=0;i<players.size();i++){
            for(int j=i+1;j<players.size();j++){
                if(players.get(i).getWins()<players.get(j).getWins()){
                    PlayerDto aux=players.get(i);
                    players.set(i,players.get(j));
                    players.set(j,aux);
                }
            }
        }
        return players;
    }




}


