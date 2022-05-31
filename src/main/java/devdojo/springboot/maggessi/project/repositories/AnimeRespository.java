package devdojo.springboot.maggessi.project.repositories;

import devdojo.springboot.maggessi.project.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeRespository extends JpaRepository<Anime, Long> {

}
