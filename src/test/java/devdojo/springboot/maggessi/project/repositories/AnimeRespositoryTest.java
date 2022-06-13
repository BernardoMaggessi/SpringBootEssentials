package devdojo.springboot.maggessi.project.repositories;

import devdojo.springboot.maggessi.project.domain.Anime;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for Anime Repository")
class AnimeRespositoryTest {

    @Autowired
    private AnimeRespository animeRespository;

    @Test
    @DisplayName("Save creates anime when sucessful")
    void save_PersistAnime_WhenSuccesful(){
        Anime anime = createAnime();
        Anime savedAnime = this.animeRespository.save(anime);
        Assertions.assertThat(savedAnime).isNotNull();
        Assertions.assertThat(savedAnime.getId()).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isEqualTo(anime.getName());
    }
    private Anime createAnime(){
        return Anime.builder().name("Hajime no Ippo").build();
    }


}