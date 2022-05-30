package devdojo.springboot.maggessi.project.repositories;

import devdojo.springboot.maggessi.project.domain.Anime;

import java.util.List;

public interface AnimeRespository{
    List<Anime> listAll();
}
