package devandroid.micaela.moldylemons;

import devandroid.micaela.moldylemons.data.local.mapper.MediaMapper;
import devandroid.micaela.moldylemons.data.model.*;
import devandroid.micaela.moldylemons.data.model.enums.Demographic;
import devandroid.micaela.moldylemons.data.model.enums.Genre;
import devandroid.micaela.moldylemons.data.model.enums.MediaType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MediaMapperTest {
    private Couple validCouple;
    private Date validDate;
    private Media media;
    private int id;
    private String title;
    private String description;
    List<Genre> genreList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Calendar cal = Calendar.getInstance();
        cal.set(2020, Calendar.JANUARY, 1);
        this.validDate = cal.getTime();
        this.validCouple = new Couple(1,"PersonOne", "PersonTwo", validDate, "user_123", "pass1234");
        this.id = 1;
        this.title = "Movie Number One";
        this.description = "This is the first tested movie";
    }

    @Test
    void givenValidMovieMediaEntityObject_whenMappingFromEntity_thenReturnsMovieInstance(){
        MediaEntity movieMediaEntity = new MediaEntity();
        movieMediaEntity.mediaType = MediaType.ALL;
        movieMediaEntity.id = this.id;
        movieMediaEntity.title = this.title;
        movieMediaEntity.description = this.description;
        this.genreList.add(Genre.ACTION);
        this.genreList.add(Genre.COMEDY);
        movieMediaEntity.genres = this.genreList;
        movieMediaEntity.duration = 189;

       this.media = MediaMapper.fromEntity(movieMediaEntity,this.validCouple);
        assertInstanceOf(Movie.class, this.media);
    }

    @Test
    void givenInvalidMovieMediaEntityObject_missingDurationField_whenMappingFromEntity_thenThrowsIllegalException(){
        MediaEntity invalidEntity = new MediaEntity();
        invalidEntity.mediaType = MediaType.ALL;
        invalidEntity.id = this.id;
        invalidEntity.title = this.title;
        invalidEntity.description = this.description;
        this.genreList.add(Genre.ACTION);
        this.genreList.add(Genre.COMEDY);
        invalidEntity.genres = this.genreList;

        assertThrows(IllegalArgumentException.class, () -> {
            MediaMapper.fromEntity(invalidEntity, this.validCouple);
        });
    }

    @Test
    void givenValidSerieMediaEntityObject_whenMappingFromEntity_thenReturnsSerieInstance(){
        MediaEntity serieMediaEntity = new MediaEntity();
        serieMediaEntity.mediaType = MediaType.SERIES;
        serieMediaEntity.id = this.id;
        serieMediaEntity.title = this.title;
        serieMediaEntity.description = this.description;
        this.genreList.add(Genre.ACTION);
        this.genreList.add(Genre.COMEDY);
        serieMediaEntity.genres = this.genreList;
        serieMediaEntity.seasons = 3;
        serieMediaEntity.totalEpisodes = 36;

        this.media = MediaMapper.fromEntity(serieMediaEntity,this.validCouple);
        assertInstanceOf(Serie.class, this.media);
    }

    @Test
    void givenValidSerieMediaEntityObject_missingNonMandatoryFields_whenMappingFromEntity_thenReturnsSerieInstance(){
        MediaEntity serieMediaEntity = new MediaEntity();
        serieMediaEntity.mediaType = MediaType.SERIES;
        serieMediaEntity.id = this.id;
        serieMediaEntity.title = this.title;
        serieMediaEntity.description = this.description;
        this.genreList.add(Genre.ACTION);
        this.genreList.add(Genre.COMEDY);
        serieMediaEntity.genres = this.genreList;

        this.media = MediaMapper.fromEntity(serieMediaEntity,this.validCouple);
        assertInstanceOf(Serie.class, this.media);
    }

    @Test
    void givenValidAnimeMediaEntityObject_whenMappingFromEntity_thenReturnsAnimeInstance(){
        MediaEntity animeMediaEntity = new MediaEntity();
        animeMediaEntity.mediaType = MediaType.ANIME;
        animeMediaEntity.id = this.id;
        animeMediaEntity.title = this.title;
        animeMediaEntity.description = this.description;
        this.genreList.add(Genre.ACTION);
        this.genreList.add(Genre.COMEDY);
        animeMediaEntity.genres = this.genreList;
        animeMediaEntity.seasons = 3;
        animeMediaEntity.totalEpisodes = 36;
        animeMediaEntity.studio = "studio";
        animeMediaEntity.demographic = Demographic.JOSEI;

        this.media = MediaMapper.fromEntity(animeMediaEntity,this.validCouple);
        assertInstanceOf(Anime.class, this.media);
    }

    @Test
    void givenValidAnimeMediaEntityObject_missingNonMandatoryFields_whenMappingFromEntity_thenReturnsAnimeInstance(){
        MediaEntity animeMediaEntity = new MediaEntity();
        animeMediaEntity.mediaType = MediaType.ANIME;
        animeMediaEntity.id = this.id;
        animeMediaEntity.title = this.title;
        animeMediaEntity.description = this.description;
        this.genreList.add(Genre.ACTION);
        this.genreList.add(Genre.COMEDY);
        animeMediaEntity.genres = this.genreList;

        this.media = MediaMapper.fromEntity(animeMediaEntity,this.validCouple);
        assertInstanceOf(Anime.class, this.media);
    }

    @Test
    void givenInvalidMediaTypeMediaEntityObject_whenMappingAnObjectFromEntity_thenThrowsIllegalArgumentException(){
        MediaEntity invalidEntity = new MediaEntity();
        invalidEntity.mediaType = null;
        invalidEntity.id = this.id;
        invalidEntity.title = this.title;
        invalidEntity.description = this.description;
        this.genreList.add(Genre.ACTION);
        this.genreList.add(Genre.COMEDY);
        invalidEntity.genres = this.genreList;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.media = MediaMapper.fromEntity(invalidEntity,this.validCouple);
        });
        assertEquals("Media type must not be null." , exception.getMessage());
    }

    @Test
    void givenInvalidCoupleId_whenMappingAnObjectFromEntity_thenThrowsIllegalArgumentException(){
        MediaEntity invalidEntity = new MediaEntity();
        invalidEntity.mediaType = MediaType.SERIES;
        invalidEntity.id = this.id;
        invalidEntity.title = this.title;
        invalidEntity.description = this.description;
        this.genreList.add(Genre.ACTION);
        this.genreList.add(Genre.COMEDY);
        invalidEntity.genres = this.genreList;
        Couple invalidCouple = new Couple("PersonOne", "PersonTwo", this.validDate, "user_123", "pass1234");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.media = MediaMapper.fromEntity(invalidEntity,invalidCouple);
        });
        assertEquals("Couple id must not be 0.", exception.getMessage());
    }

    @Test
    void givenValidMovie_whenMappingToEntity_thenAllMandatoryFieldsMappedCorrectly() {
        Movie movie = new Movie(1, "Valid Movie", "A test movie",
                Arrays.asList(Genre.ACTION), 120, validCouple);

        MediaEntity entity = MediaMapper.toEntity(movie);

        assertEquals(1, entity.id);
        assertEquals("Valid Movie", entity.title);
        assertEquals("A test movie", entity.description);
        assertEquals(Arrays.asList(Genre.ACTION), entity.genres);
        assertEquals(MediaType.ALL, entity.mediaType);
        assertEquals(120, entity.duration);
        assertEquals(validCouple.getId(), entity.coupleId);
    }

    @Test
    void givenValidSerie_whenMappingToEntity_thenAllFieldsMappedCorrectly() {
        Serie serie = new Serie(1, "Valid Serie", "A test serie",
                Arrays.asList(Genre.ACTION, Genre.DRAMA), 2, 24, validCouple);

        MediaEntity entity = MediaMapper.toEntity(serie);

        assertEquals(1, entity.id);
        assertEquals("Valid Serie", entity.title);
        assertEquals("A test serie", entity.description);
        assertEquals(Arrays.asList(Genre.ACTION, Genre.DRAMA), entity.genres);
        assertEquals(MediaType.SERIES, entity.mediaType);
        assertEquals(2, entity.seasons);
        assertEquals(24, entity.totalEpisodes);
        assertEquals(validCouple.getId(), entity.coupleId);
    }

    @Test
    void givenValidAnime_whenMappingToEntity_thenFieldsAreCorrectlyMapped() {
        Anime anime = new Anime(1, "Spirited Away", "Fantasy anime movie", Arrays.asList(Genre.FANTASY, Genre.MYSTERY),
                1, 1, "Studio Ghibli", Demographic.JOSEI, validCouple
        );

        MediaEntity entity = MediaMapper.toEntity(anime);

        assertEquals(1, entity.id);
        assertEquals("Spirited Away", entity.title);
        assertEquals("Fantasy anime movie", entity.description);
        assertEquals(Arrays.asList(Genre.FANTASY, Genre.MYSTERY), entity.genres);
        assertEquals(MediaType.ANIME, entity.mediaType);
        assertEquals(1, entity.seasons);
        assertEquals(1, entity.totalEpisodes);
        assertEquals("Studio Ghibli", entity.studio);
        assertEquals(Demographic.JOSEI, entity.demographic);
        assertEquals(validCouple.getId(), entity.coupleId);
    }


}
