package ro.hibyte.pingpong.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import ro.hibyte.pingpong.entities.Player;


public interface MainRepository extends JpaRepository<Player, Long> {


}
