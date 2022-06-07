package devdojo.springboot.maggessi.project.Mapper;

import devdojo.springboot.maggessi.project.domain.Anime;
import devdojo.springboot.maggessi.project.requests.AnimePostRequestBody;
import devdojo.springboot.maggessi.project.requests.AnimePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {
    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);

    public abstract Anime toAnime(AnimePutRequestBody animePostRequestBody);
}