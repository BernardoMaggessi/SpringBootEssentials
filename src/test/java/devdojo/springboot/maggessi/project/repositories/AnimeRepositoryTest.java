package devdojo.springboot.maggessi.project.repositories;

import devdojo.springboot.maggessi.project.domain.Anime;


import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static devdojo.springboot.maggessi.project.utils.AnimeCreator.createAnimeToBeSaved;

@DataJpaTest
@DisplayName("Tests for Anime Repository")
@Log4j2
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save persists anime when sucessful")
    void save_PersistAnime_WhenSuccesful(){
        Anime animeToBeSaved = createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());
    }


    @Test
    @DisplayName("Save updates anime when sucessful")
    void save_UpdatesAnime_WhenSuccesful(){
        Anime animeToBeSaved = createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        animeSaved.setName("Overlord");
        Anime AnimeUpdated = this.animeRepository.save(animeSaved);


        Assertions.assertThat(AnimeUpdated).isNotNull();
        Assertions.assertThat(AnimeUpdated.getId()).isNotNull();
        Assertions.assertThat(AnimeUpdated.getName()).isEqualTo(animeSaved.getName());
    }

    @Test
    @DisplayName("Delete anime when sucessful")
    void delete_RemoveAnime_WhenSuccesful(){
        Anime animeToBeSaved = createAnimeToBeSaved();

        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        this.animeRepository.delete(animeSaved);

        Optional<Anime> animeOptional =  this.animeRepository.findById(animeSaved.getId());

        Assertions.assertThat(animeOptional.isEmpty()).isTrue();

    }
    @Test
    @DisplayName("Find by name returns list when sucessful ")
    void findByName_returnListOfAnime_WhenSucessful(){
        Anime animeToBeSaved = createAnimeToBeSaved();

        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        String name = animeSaved.getName();

        List<Anime> animes = this.animeRepository.findByName(name);

        Assertions.assertThat(animes).isNotEmpty().contains(animeSaved);


    }
    @Test
    @DisplayName("Find by name returns empty list when anime is found ")
    void findByName_returnEmptyList_WhenAnimeIsNotFound(){
        List<Anime> animes = this.animeRepository.findByName("xaxa");

        Assertions.assertThat(animes).isEmpty();

    }
    @Test
    @DisplayName("Save throw ConstraintValidationException when name is empty")
    void save_ThrowsConstraintsValidationException_WhenNameIsEmpty(){
        Anime anime = new Anime();

        // Assertions.assertThatThrownBy(() ->  this.animeRespository.save(anime)).isInstanceOf(ConstraintViolationException.class);

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.animeRepository.save(anime))
                .withMessageContaining("The anime name cannot be empty");
    }


}