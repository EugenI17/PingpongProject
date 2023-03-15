package ro.hibyte.pingpong.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import ro.hibyte.pingpong.entities.Game;


public interface GameRepository extends JpaRepository<Game, Long> {
}
