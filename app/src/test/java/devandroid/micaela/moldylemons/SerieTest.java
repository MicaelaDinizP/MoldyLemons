package devandroid.micaela.moldylemons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import devandroid.micaela.moldylemons.data.model.Couple;
import devandroid.micaela.moldylemons.data.model.Serie;
import devandroid.micaela.moldylemons.data.model.enums.Genre;
import devandroid.micaela.moldylemons.data.model.enums.MediaType;

public class SerieTest {
    private String title;
    private String description;
    private int seasons;
    private int totalEpisodes;
    private Genre actionGenre;
    private Genre sitcomGenre;
    private List<Genre> genreList;
    private Serie serie;
    private Couple couple;

    @BeforeEach
    void setUp() {
        Calendar cal = Calendar.getInstance();
        cal.set(2020, Calendar.JANUARY, 1);
        Date validDate = cal.getTime();
        this.title = "Inception";
        this.description = "A mind-bending thriller";
        this.actionGenre = Genre.ACTION;
        this.sitcomGenre = Genre.SITCOM;
        this.genreList = Arrays.asList(actionGenre, sitcomGenre);
        this.seasons = 2;
        this.totalEpisodes = 30;
        this.couple = new Couple("PersonOne", "PersonTwo", validDate, "user_123", "pass1234");
        this.couple.setId(1);
        this.serie = new Serie(this.title, this.description, this.genreList, this.seasons, this.totalEpisodes, couple);
    }

    @Test
    void givenAllValidData_whenCreatingSerie_thenCreateSucessfully() {
        this.serie = new Serie(this.title, this.description, this.genreList, this.seasons, this.totalEpisodes, this.couple);

        assertEquals(this.title, this.serie.getTitle());
        assertEquals(this.description, this.serie.getDescription());
        assertEquals(2, this.serie.getGenres().size());
        assertEquals(MediaType.SERIES, this.serie.getMediaType());
        assertTrue(this.serie.getGenres().contains(this.actionGenre));
        assertTrue(this.serie.getGenres().contains(this.sitcomGenre));
        assertNotNull(this.serie.getReviews());
        assertTrue(this.serie.getReviews().isEmpty());
        assertEquals(this.seasons, this.serie.getSeasons());
        assertEquals(this.totalEpisodes, this.serie.getTotalEpisodes());
    }

    @Test
    void givenNullGenreList_whenCreatingSerie_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Serie serie = new Serie(this.title, this.description, null,this.seasons, this.totalEpisodes, this.couple);
        });
        assertEquals("At least one genre must be provided.", exception.getMessage());
    }

    @Test
    void givenEmptyGenreList_whenCreatingSerie_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Serie serie = new Serie(this.title, this.description, new ArrayList<Genre>(),this.seasons, this.totalEpisodes, this.couple);
        });
        assertEquals("At least one genre must be provided.", exception.getMessage());
    }

    @Test
    void givenInvalidGenreList_whenCreatingSerie_thenThrowsIllegalArgumentException() {
        this.genreList = Arrays.asList(Genre.MECHA,Genre.SITCOM, Genre.COMEDY);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Serie serie = new Serie(this.title, this.description, genreList, this.seasons, this.totalEpisodes, this.couple);
        });
        assertEquals("Invalid genre for this type of media.", exception.getMessage());
    }

    @Test
    void givenNegativeSeasons_whenCreatingSerie_thenThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Serie(this.title, this.description, this.genreList, -1, this.totalEpisodes, this.couple);
        });
        assertEquals("Seasons must be greater than or equal to zero.", exception.getMessage());
    }

    @Test
    void givenNegativeTotalEpisodes_whenCreatingSerie_thenThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Serie(this.title, this.description, this.genreList, this.seasons, -5, this.couple);
        });
        assertEquals("Total episodes must be greater than or equal to zero.", exception.getMessage());
    }

    @Test
    void whenGettingMediaType_thenReturnsCorrectType() {
        assertEquals(MediaType.SERIES, this.serie.getMediaType());
    }
    @Test
    void whenCreatingSerieWithoutSeasonsAndEpisodes_thenDefaultsAreZero() {
        Serie serie = new Serie("Friends", "Comedy sitcom",
                Arrays.asList(Genre.COMEDY), this.couple);

        assertEquals(0, serie.getSeasons());
        assertEquals(0, serie.getTotalEpisodes());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, 100})
    void givenNonNegativeSeasons_whenSetSeasonsCalled_thenSeasonsUpdated(int validSeasons) {
        this.serie.setSeasons(validSeasons);
        assertEquals(validSeasons, this.serie.getSeasons());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 20, 50})
    void givenNonNegativeTotalEpisodes_whenSetTotalEpisodesCalled_thenTotalEpisodesUpdated(int validTotalEpisodes) {
        this.serie.setTotalEpisodes(validTotalEpisodes);
        assertEquals(validTotalEpisodes, this.serie.getTotalEpisodes());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10})
    void givenNegativeSeasons_whenSetSeasonsCalled_thenThrowsIllegalArgumentException(int invalidSeasons) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            this.serie.setSeasons(invalidSeasons);
        });
        assertEquals("Seasons must be greater than or equal to zero.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5})
    void givenNegativeTotalEpisodes_whenSetTotalEpisodesCalled_thenThrowsIllegalArgumentException(int invalidTotalEpisodes) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            this.serie.setTotalEpisodes(invalidTotalEpisodes);
        });
        assertEquals("Total episodes must be greater than or equal to zero.", exception.getMessage());
    }

    @Test
    void givenInvalidAnimeGenre_whenCreatingSerie_thenThrowsIllegalArgumentException() {
        List<Genre> invalidGenres = Arrays.asList(Genre.SHOUNEN, Genre.SHOUJO, Genre.MECHA, Genre.ISEKAI, Genre.YAOI, Genre.YURI);

        for (Genre genre : invalidGenres) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Serie(this.title, this.description, Arrays.asList(genre), this.seasons, this.totalEpisodes, this.couple);
            });
            assertEquals("Invalid genre for this type of media.", exception.getMessage());
        }
    }

    @Test
    void givenValidSeriesGenre_whenCreatingSerie_thenAcceptsGenre() {
        List<Genre> validGenres = Arrays.asList(Genre.SITCOM, Genre.CRIME);

        for (Genre genre : validGenres) {
            Serie serie = new Serie(this.title, this.description, Arrays.asList(genre), this.seasons, this.totalEpisodes, this.couple);
            assertNotNull(serie);
            assertTrue(serie.getGenres().contains(genre));
        }
    }

    @Test
    void givenValidAllGenre_whenCreatingSerie_thenAcceptsGenre() {
        List<Genre> validGenres = Arrays.asList(Genre.ACTION, Genre.MYSTERY, Genre.DRAMA, Genre.COMEDY, Genre.ROMANCE);

        for (Genre genre : validGenres) {
            Serie serie = new Serie(this.title, this.description, Arrays.asList(genre), this.seasons, this.totalEpisodes, this.couple);
            assertNotNull(serie);
            assertTrue(serie.getGenres().contains(genre));
        }
    }

    @Test
    void givenValidGenre_whenAddGenreCalled_thenGenreIsAdded() {
        Genre newGenre = Genre.DRAMA;
        this.serie.addGenre(newGenre);
        assertTrue(serie.getGenres().contains(newGenre));
    }

    @Test
    void givenDuplicatedGenre_whenAddGenreCalled_thenThrowsIllegalArgumentException() {
        Genre newGenre = Genre.ACTION;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.serie.addGenre(newGenre);
        });
        assertEquals("Genre already added.", exception.getMessage());
    }

    @Test
    void givenInvalidGenre_whenAddGenreCalled_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            serie.addGenre(Genre.ISEKAI);
        });
        assertFalse(this.serie.getGenres().contains(Genre.ISEKAI));
        assertEquals("Invalid genre for this type of media.", exception.getMessage());
    }

    @Test
    void givenNullGenre_whenAddGenreCalled_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.serie.addGenre(null);
        });
        assertEquals("Genre cannot be null.", exception.getMessage());
    }

    @Test
    void givenValidGenre_whenRemoveGenreCalled_thenRemoveGenreSuccessfully() {
        this.serie.addGenre(Genre.COMEDY);
        this.serie.removeGenre(Genre.COMEDY);
        assertFalse(this.serie.getGenres().contains(Genre.COMEDY));
    }

    @Test
    void givenAbsentGenre_whenRemoveGenreCalled_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.serie.removeGenre(Genre.DRAMA);
        });
        assertEquals("Genre not found in the list.", exception.getMessage());
    }

    @Test
    void givenNullGenre_whenRemoveGenreCalled_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.serie.removeGenre(null);
        });
        assertEquals("Genre cannot be null.", exception.getMessage());
    }

    @Test
    void givenListWithValidGenres_whenSetGenresCalled_thenSetSucessfully() {
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ACTION);
        genres.add(Genre.COMEDY);
        genres.add(Genre.SITCOM);

        this.serie.setGenres(genres);

        assertTrue(this.serie.getGenres().contains(Genre.ACTION));
        assertTrue(this.serie.getGenres().contains(Genre.COMEDY));
        assertTrue(this.serie.getGenres().contains(Genre.SITCOM));
        assertEquals(this.serie.getGenres(), genres);
    }

    @Test
    void givenListWithInvalidGenres_whenSetGenresCalled_thenThrowsIllegalArgumentException() {
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ACTION);
        genres.add(Genre.COMEDY);
        genres.add(Genre.ISEKAI);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.serie.setGenres(genres);
        });
        assertEquals("Invalid genre for this type of media.", exception.getMessage());
        assertFalse(this.serie.getGenres().contains(Genre.ISEKAI));
    }

    @Test
    void givenEmptyGenreList_whenSetGenresCalled_thenThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.serie.setGenres(new ArrayList<Genre>());
        });
        assertEquals("At least one genre must be provided.", exception.getMessage());
    }

    @Test
    void givenNullGenreList_whenSetGenresCalled_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.serie.setGenres(null);
        });
        assertEquals("At least one genre must be provided.", exception.getMessage());
    }

    @Test
    void givenDuplicatedGenre_whenSetGenreCalled_thenThrowsIllegalArgumentException() {
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ACTION);
        genres.add(Genre.ACTION);
        genres.add(Genre.COMEDY);
        genres.add(Genre.ISEKAI);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.serie.setGenres(genres);
        });
        assertEquals("Genre already added.", exception.getMessage());
    }

}