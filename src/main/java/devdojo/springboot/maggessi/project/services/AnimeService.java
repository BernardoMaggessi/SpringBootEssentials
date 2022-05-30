package devdojo.springboot.maggessi.project.services;

import devdojo.springboot.maggessi.project.domain.Anime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {
    //private final AnimeRepository animeRepository;
    public List<Anime> listAll(){
        return List.of(new Anime(1L,"Boku No Hero"), new Anime(2L,"Berserk"));
    }
}
