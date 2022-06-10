package devdojo.springboot.maggessi.project.services;

import devdojo.springboot.maggessi.project.Mapper.AnimeMapper;
import devdojo.springboot.maggessi.project.domain.Anime;
import devdojo.springboot.maggessi.project.exception.BadRequestException;
import devdojo.springboot.maggessi.project.requests.AnimePostRequestBody;
import devdojo.springboot.maggessi.project.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import devdojo.springboot.maggessi.project.repositories.AnimeRespository;

import javax.transaction.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRespository animeRespository;

    public Page<Anime> listAll(Pageable pageable) {
        return animeRespository.findAll(pageable);
    }

    public List<Anime> findByName(String name) {
        return animeRespository.findByName(name);
    }

    public Anime findByIdOrThrowBadRequestException(long id) {
        return animeRespository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not found"));
    }
    @Transactional(rollbackOn = Exception.class)
    public Anime save(AnimePostRequestBody animePostRequestBody){
        return animeRespository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
    }

    public void delete(long id) {
        animeRespository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        anime.setId(savedAnime.getId());
        animeRespository.save(anime);
    }
}