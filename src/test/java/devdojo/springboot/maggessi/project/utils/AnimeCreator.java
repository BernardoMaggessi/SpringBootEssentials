package devdojo.springboot.maggessi.project.utils;

import devdojo.springboot.maggessi.project.domain.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved(){
        return Anime.builder().name("Hajime no Ippo").build();
    }

    public static Anime createValidAnime(){
        return Anime.builder().name("Hajime no Ippo").build();
    }

    public static Anime createValidUpdatedAnime(){
        return Anime.builder().name("Hajime no Ippo 2").build();
    }

}