package devandroid.micaela.moldylemons.data.local.mapper;

import devandroid.micaela.moldylemons.data.model.*;
import devandroid.micaela.moldylemons.data.model.enums.*;

public class MediaMapper {

    public static Media fromEntity(MediaEntity entity, Couple couple) {
        if(entity.mediaType == null){
            throw new IllegalArgumentException("Media type must not be null.");
        }
        if(couple.getId() == 0){
            throw new IllegalArgumentException("Couple id must not be 0.");
        }
        switch (entity.mediaType) {
            case ALL:
                return new Movie(
                        entity.id,
                        entity.title,
                        entity.description,
                        entity.genres,
                        entity.duration,
                        couple
                );

            case SERIES:
                return new Serie(
                        entity.id,
                        entity.title,
                        entity.description,
                        entity.genres,
                        entity.seasons,
                        entity.totalEpisodes,
                        couple
                );

            case ANIME:
                return new Anime(
                        entity.id,
                        entity.title,
                        entity.description,
                        entity.genres,
                        entity.seasons,
                        entity.totalEpisodes,
                        entity.studio,
                        entity.demographic,
                        couple
                );

            default:
                throw new IllegalArgumentException("Unsupported media type: " + entity.mediaType);
        }
    }

    public static MediaEntity toEntity(Media media) {
        MediaEntity entity = new MediaEntity();

        entity.id = media.getId();
        entity.title = media.getTitle();
        entity.description = media.getDescription();
        entity.genres = media.getGenres();
        entity.mediaType = media.getMediaType();
        entity.coupleId = media.getCouple().getId();

        if (media instanceof Movie) {
            entity.duration = ((Movie) media).getDuration();

        } else if (media instanceof Serie && !(media instanceof Anime)) {
            Serie serie = (Serie) media;
            entity.seasons = serie.getSeasons();
            entity.totalEpisodes = serie.getTotalEpisodes();

        } else if (media instanceof Anime) {
            Anime anime = (Anime) media;
            entity.seasons = anime.getSeasons();
            entity.totalEpisodes = anime.getTotalEpisodes();
            entity.studio = anime.getStudio();
            entity.demographic = anime.getDemographic();
        }

        return entity;
    }
}