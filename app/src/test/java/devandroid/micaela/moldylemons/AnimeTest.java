package devandroid.micaela.moldylemons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import devandroid.micaela.moldylemons.data.model.Anime;
import devandroid.micaela.moldylemons.data.model.enums.Demographic;
import devandroid.micaela.moldylemons.data.model.enums.Genre;
import devandroid.micaela.moldylemons.data.model.enums.MediaType;

public class AnimeTest {
    private String title;
    private String description;
    private List<Genre> genreList;
    private Anime anime;

    @BeforeEach
    void setUp() {
        this.title = "Anime X Anime";
        this.description = "A mind-bending Anime";
        this.genreList = Arrays.asList(Genre.MECHA, Genre.COMEDY);
        this.anime = new Anime(this.title, this.description, this.genreList);
    }

    @Test
    void givenAllValidData_whenCreatingAnime_thenCreateSucessfully() {
        this.anime = new Anime(this.title, this.description, this.genreList,2,26,"StudioX", Demographic.JOSEI);

        assertEquals(this.title, this.anime.getTitle());
        assertEquals(this.description, this.anime.getDescription());
        assertEquals(2, this.anime.getGenres().size());
        assertEquals(MediaType.ANIME, this.anime.getMediaType());
        assertTrue(this.anime.getGenres().contains(Genre.MECHA));
        assertTrue(this.anime.getGenres().contains(Genre.COMEDY));
        assertTrue(this.anime.getReviews().isEmpty());
        assertEquals(2, this.anime.getSeasons());
        assertEquals(26, this.anime.getTotalEpisodes());
        assertEquals("StudioX", this.anime.getStudio());
        assertEquals(Demographic.JOSEI, this.anime.getDemographic());
    }

    @Test
    void whenGettingMediaType_thenReturnsCorrectType() {
        assertEquals(MediaType.ANIME, this.anime.getMediaType());
    }

    @Test
    void givenNullGenreList_whenCreatingAnime_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Anime anime = new Anime(this.title, this.description, null,2,26,"StudioX",Demographic.JOSEI);
        });
        assertEquals("At least one genre must be provided.", exception.getMessage());
    }

    @Test
    void givenEmptyGenreList_whenCreatingAnime_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Anime anime = new Anime(this.title, this.description, new ArrayList<Genre>(),2,26,
                    "StudioX",Demographic.JOSEI);
        });
        assertEquals("At least one genre must be provided.", exception.getMessage());
    }

    @Test
    void givenInvalidGenreList_whenCreatingAnime_thenThrowsIllegalArgumentException() {
        this.genreList = Arrays.asList(Genre.MECHA,Genre.SITCOM, Genre.COMEDY);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Anime anime = new Anime(this.title, this.description, genreList,2,26,
                    "StudioX",Demographic.JOSEI);
        });
        assertEquals("Invalid genre for this type of media.", exception.getMessage());
    }

    @Test
    void givenValidGenre_whenAddGenreCalled_thenGenreIsAdded() {
        Genre newGenre = Genre.DRAMA;
        this.anime.addGenre(newGenre);
        assertTrue(anime.getGenres().contains(newGenre));
    }

    @Test
    void givenDuplicatedGenre_whenAddGenreCalled_thenThrowsIllegalArgumentException() {
            Genre newGenre = Genre.MECHA;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.anime.addGenre(newGenre);
        });
        assertEquals("Genre already added.", exception.getMessage());
    }

    @Test
    void givenInvalidGenre_whenAddGenreCalled_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            anime.addGenre(Genre.SITCOM);
        });
        assertFalse(this.anime.getGenres().contains(Genre.SITCOM));
        assertEquals("Invalid genre for this type of media.", exception.getMessage());
    }

    @Test
    void givenNullGenre_whenAddGenreCalled_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.anime.addGenre(null);
        });
        assertEquals("Genre cannot be null.", exception.getMessage());
    }

    @Test
    void givenValidGenre_whenRemoveGenreCalled_thenRemoveGenreSuccessfully() {
        this.anime.addGenre(Genre.ISEKAI);
        this.anime.removeGenre(Genre.ISEKAI);
        assertFalse(this.anime.getGenres().contains(Genre.ISEKAI));
    }

    @Test
    void givenAbsentGenre_whenRemoveGenreCalled_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.anime.removeGenre(Genre.SHOUJO);
        });
        assertEquals("Genre not found in the list.", exception.getMessage());
    }

    @Test
    void givenNullGenre_whenRemoveGenreCalled_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.anime.removeGenre(null);
        });
        assertEquals("Genre cannot be null.", exception.getMessage());
    }

    @Test
    void givenListWithValidGenres_whenSetGenresCalled_thenSetSucessfully() {
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.SHOUNEN);
        genres.add(Genre.YAOI);
        genres.add(Genre.ISEKAI);

        this.anime.setGenres(genres);

        assertTrue(this.anime.getGenres().contains(Genre.SHOUNEN));
        assertTrue(this.anime.getGenres().contains(Genre.YAOI));
        assertTrue(this.anime.getGenres().contains(Genre.ISEKAI));
        assertEquals(this.anime.getGenres(), genres);
    }

    @Test
    void givenListWithInvalidGenres_whenSetGenresCalled_thenThrowsIllegalArgumentException() {
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ACTION);
        genres.add(Genre.COMEDY);
        genres.add(Genre.SITCOM);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.anime.setGenres(genres);
        });
        assertEquals("Invalid genre for this type of media.", exception.getMessage());
        assertFalse(this.anime.getGenres().contains(Genre.SITCOM));
    }

    @Test
    void givenEmptyGenreList_whenSetGenresCalled_thenThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.anime.setGenres(new ArrayList<Genre>());
        });
        assertEquals("At least one genre must be provided.", exception.getMessage());
    }

    @Test
    void givenNullGenreList_whenSetGenresCalled_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.anime.setGenres(null);
        });
        assertEquals("At least one genre must be provided.", exception.getMessage());
    }

    @Test
    void givenDuplicatedGenre_whenSetGenreCalled_thenThrowsIllegalArgumentException() {
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.MECHA);
        genres.add(Genre.MECHA);
        genres.add(Genre.COMEDY);
        genres.add(Genre.ISEKAI);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.anime.setGenres(genres);
        });
        assertEquals("Genre already added.", exception.getMessage());
    }
}
