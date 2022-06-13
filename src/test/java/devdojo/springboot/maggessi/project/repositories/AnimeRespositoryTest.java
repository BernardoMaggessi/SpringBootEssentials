package devdojo.springboot.maggessi.project.repositories;

import devdojo.springboot.maggessi.project.domain.Anime;


import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Anime Repository")
@Log4j2
class AnimeRespositoryTest {

    @Autowired
    private AnimeRespository animeRespository;

    @Test
    @DisplayName("Save persists anime when sucessful")
    void save_PersistAnime_WhenSuccesful(){
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRespository.save(animeToBeSaved);

        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());
    }
    @Test
    @DisplayName("Save updates anime when sucessful")
    void save_UpdatesAnime_WhenSuccesful(){
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRespository.save(animeToBeSaved);

        animeSaved.setName("Overlord");
        Anime AnimeUpdated = this.animeRespository.save(animeSaved);


        Assertions.assertThat(AnimeUpdated).isNotNull();
        Assertions.assertThat(AnimeUpdated.getId()).isNotNull();
        Assertions.assertThat(AnimeUpdated.getName()).isEqualTo(animeSaved.getName());
    }

    @Test
    @DisplayName("Delete anime when sucessful")
    void delete_RemoveAnime_WhenSuccesful(){
        Anime animeToBeSaved = createAnime();

        Anime animeSaved = this.animeRespository.save(animeToBeSaved);

        this.animeRespository.delete(animeSaved);

        Optional<Anime> animeOptional =  this.animeRespository.findById(animeSaved.getId());

        Assertions.assertThat(animeOptional.isEmpty()).isTrue();

    }
    @Test
    @DisplayName("Find by name returns list when sucessful ")
    void findByName_returnListOfAnime_WhenSucessful(){
        Anime animeToBeSaved = createAnime();

        Anime animeSaved = this.animeRespository.save(animeToBeSaved);

        String name = animeSaved.getName();

        List<Anime> animes = this.animeRespository.findByName(name);

        Assertions.assertThat(animes).isNotEmpty();
        Assertions.assertThat(animes).contains(animeSaved);

    }
    @Test
    @DisplayName("Find by name returns empty list when anime is found ")
    void findByName_returnEmptyList_WhenAnimeIsNotFound(){
        List<Anime> animes = this.animeRespository.findByName("xaxa");

        Assertions.assertThat(animes).isEmpty();

    }
    private Anime createAnime(){
        return Anime.builder().name("Hajime no Ippo").build();
    }


}