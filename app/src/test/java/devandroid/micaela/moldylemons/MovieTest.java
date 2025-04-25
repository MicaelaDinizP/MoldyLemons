package devandroid.micaela.moldylemons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {
    private String title;
    private String description;
    private int duration;
    private Genre actionGenre;
    private List<Genre> genreList;
    private Movie movie;

    @BeforeEach
    void setUp() {
        this.title = "Inception";
        this.description = "A mind-bending thriller";
        this.duration = 148;
        this.actionGenre = Genre.ACTION;
        this.genreList = Arrays.asList(actionGenre);

        this.movie = new Movie(title, description, genreList, duration);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 148, 1000 * 60})
    void givenAllValidData_whenCreatingMovie_thenCreateSucessfully(int validDuration) {
        this.movie.setDuration(validDuration);
        this.duration = validDuration;
        assertEquals(this.title, this.movie.getTitle());
        assertEquals(this.description, this.movie.getDescription());
        assertEquals(this.duration, this.movie.getDuration());
        assertEquals(1, this.movie.getGenres().size());
        assertEquals(MediaType.ALL, this.movie.getMediaType());
        assertTrue(this.movie.getGenres().contains(this.actionGenre));
        assertNotNull(this.movie.getReviews());
        assertTrue(this.movie.getReviews().isEmpty());
    }

    @Test
    void givenNullGenreList_whenCreatingSerie_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Movie movie = new Movie(this.title, this.description, null, 100);
        });
        assertEquals("At least one genre must be provided.", exception.getMessage());
    }

    @Test
    void givenEmptyGenreList_whenCreatingSerie_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Movie movie = new Movie(this.title, this.description, new ArrayList<Genre>(), 100);
        });
        assertEquals("At least one genre must be provided.", exception.getMessage());
    }

    @Test
    void givenInvalidGenreList_whenCreatingSerie_thenThrowsIllegalArgumentException() {
        this.genreList = Arrays.asList(Genre.MECHA,Genre.SITCOM, Genre.COMEDY);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Movie movie = new Movie(this.title, this.description, genreList, 100);
        });
        assertEquals("Invalid genre for this type of media.", exception.getMessage());
    }

    @Test
    void whenGettingMediaType_thenReturnsCorrectType() {
        assertEquals(MediaType.ALL, this.movie.getMediaType());
    }

    @ParameterizedTest
    @ValueSource(ints = {0,-1})
    void givenNonPositiveDuration_whenCreatingMovie_thenThrowsIllegalArgumentException(int invalidDuration) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Movie movie = new Movie(this.title, this.description, this.genreList, invalidDuration);
        });
        assertEquals("Duration must be a positive number.", exception.getMessage());
    }

    @Test
    void givenTooLargeDuration_whenCreatingMovie_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Movie movie = new Movie(this.title, this.description, this.genreList, 1000 * 60 + 1);
        });
        assertEquals("Duration is unreasonably long.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 148, 1000 * 60})
    void givenValidDuration_whenSettingDuration_thenSetSucessfully(int validDuration) {
        this.movie.setDuration(validDuration);
        assertEquals(this.movie.getDuration(),validDuration);
    }
    @ParameterizedTest
    @ValueSource(ints = {0,-1})
    void givenNonPositiveDuration_whenSettingDuration_thenThrowsIllegalArgumentException(int invalidDuration) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.movie.setDuration(invalidDuration);
        });
        assertEquals("Duration must be a positive number.", exception.getMessage());
    }

    @Test
    void givenTooLargeDuration_whenSettingDuration_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.movie.setDuration(1000 * 60 + 1);
        });
        assertEquals("Duration is unreasonably long.", exception.getMessage());
    }

    @Test
    void givenValidGenre_whenAddGenreCalled_thenGenreIsAdded() {
        Genre newGenre = Genre.DRAMA;
        this.movie.addGenre(newGenre);
        assertTrue(movie.getGenres().contains(newGenre));
    }

    @Test
    void givenDuplicatedGenre_whenAddGenreCalled_thenThrowsIllegalArgumentException() {
        Genre newGenre = Genre.ACTION;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.movie.addGenre(newGenre);
        });
       assertEquals("Genre already added.", exception.getMessage());
    }

    @Test
    void givenInvalidGenre_whenAddGenreCalled_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            movie.addGenre(Genre.ISEKAI);
        });
        assertFalse(this.movie.getGenres().contains(Genre.ISEKAI));
        assertEquals("Invalid genre for this type of media.", exception.getMessage());
    }

    @Test
    void givenNullGenre_whenAddGenreCalled_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.movie.addGenre(null);
        });
        assertEquals("Genre cannot be null.", exception.getMessage());
    }

    @Test
    void givenValidGenre_whenRemoveGenreCalled_thenRemoveGenreSuccessfully() {
        this.movie.addGenre(Genre.COMEDY);
        this.movie.removeGenre(Genre.COMEDY);
        assertFalse(this.movie.getGenres().contains(Genre.COMEDY));
    }

    @Test
    void givenAbsentGenre_whenRemoveGenreCalled_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.movie.removeGenre(Genre.DRAMA);
        });
        assertEquals("Genre not found in the list.", exception.getMessage());
    }

    @Test
    void givenNullGenre_whenRemoveGenreCalled_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.movie.removeGenre(null);
        });
        assertEquals("Genre cannot be null.", exception.getMessage());
    }

    @Test
    void givenListWithValidGenres_whenSetGenresCalled_thenSetSucessfully() {
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ACTION);
        genres.add(Genre.COMEDY);
        genres.add(Genre.FANTASY);

        this.movie.setGenres(genres);

        assertTrue(this.movie.getGenres().contains(Genre.ACTION));
        assertTrue(this.movie.getGenres().contains(Genre.COMEDY));
        assertTrue(this.movie.getGenres().contains(Genre.FANTASY));
        assertEquals(this.movie.getGenres(), genres);
    }

    @Test
    void givenListWithInvalidGenres_whenSetGenresCalled_thenThrowsIllegalArgumentException() {
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ACTION);
        genres.add(Genre.COMEDY);
        genres.add(Genre.ISEKAI);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.movie.setGenres(genres);
        });
        assertEquals("Invalid genre for this type of media.", exception.getMessage());
        assertFalse(this.movie.getGenres().contains(Genre.ISEKAI));

        genres.remove(Genre.ISEKAI);
        genres.add(Genre.SITCOM);
        assertThrows(IllegalArgumentException.class, () -> {
            this.movie.setGenres(genres);
        });
        assertEquals("Invalid genre for this type of media.", exception.getMessage());
        assertFalse(this.movie.getGenres().contains(Genre.SITCOM));
    }

    @Test
    void givenEmptyGenreList_whenSetGenresCalled_thenThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.movie.setGenres(new ArrayList<Genre>());
        });
        assertEquals("At least one genre must be provided.", exception.getMessage());
    }

    @Test
    void givenNullGenreList_whenSetGenresCalled_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.movie.setGenres(null);
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
            this.movie.setGenres(genres);
        });
        assertEquals("Genre already added.", exception.getMessage());
    }
}
