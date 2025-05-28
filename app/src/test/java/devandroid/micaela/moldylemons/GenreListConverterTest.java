package devandroid.micaela.moldylemons;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import devandroid.micaela.moldylemons.data.local.GenreListConverter;
import devandroid.micaela.moldylemons.data.model.enums.Genre;

import static org.junit.jupiter.api.Assertions.*;

class GenreListConverterTest {

    @Test
    void givenValidGenreList_whenConvertingFromList_thenReturnsCorrectString() {
        List<Genre> genres = Arrays.asList(Genre.ACTION, Genre.DRAMA, Genre.COMEDY);
        String result = GenreListConverter.fromGenreList(genres);
        assertEquals("1,3,4", result);
    }

    @Test
    void givenEmptyGenreList_whenConvertingFromList_thenReturnsEmptyString() {
        List<Genre> genres = Collections.emptyList();
        String result = GenreListConverter.fromGenreList(genres);
        assertEquals("", result);
    }

    @Test
    void givenNullGenreList_whenConvertingFromList_thenReturnsEmptyString() {
        String result = GenreListConverter.fromGenreList(null);
        assertEquals("", result);
    }

    @Test
    void givenValidString_whenConvertingToList_thenReturnsCorrectGenreList() {
        String genres = "1,3,4";
        List<Genre> result = GenreListConverter.toGenreList(genres);
        assertEquals(3, result.size());
        assertTrue(result.contains(Genre.ACTION));
        assertTrue(result.contains(Genre.DRAMA));
        assertTrue(result.contains(Genre.COMEDY));
    }

    @Test
    void givenEmptyString_whenConvertingToList_thenReturnsEmptyList() {
        String genres = "";
        List<Genre> result = GenreListConverter.toGenreList(genres);
        assertTrue(result.isEmpty());
    }

    @Test
    void givenNullString_whenConvertingToList_thenReturnsEmptyList() {
        List<Genre> result = GenreListConverter.toGenreList(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void givenStringWithInvalidId_whenConvertingToList_thenIgnoresInvalids() {
        String invalidGenres = "1,abc,99,17";
        List<Genre> result = GenreListConverter.toGenreList(invalidGenres);
        assertTrue(result.contains(Genre.ACTION));
        assertTrue(result.contains(Genre.CRIME));
        assertEquals(2, result.size());
    }

    @Test
    void givenStringWithAllInvalidId_whenConvertingToList_thenReturnsEmptyList() {
        String invalidGenres = "o,abc,99,999";
        List<Genre> result = GenreListConverter.toGenreList(invalidGenres);
        assertTrue(result.isEmpty());
    }

    @Test
    void givenGenres_whenConvertedToStringAndBack_thenReturnsSameGenres() {
        List<Genre> original = Arrays.asList(Genre.DRAMA, Genre.COMEDY);
        String string = GenreListConverter.fromGenreList(original);
        List<Genre> converted = GenreListConverter.toGenreList(string);

        assertEquals(original.size(), converted.size());
        assertTrue(converted.containsAll(original));
    }

    @Test
    void givenString_whenConvertedToGenreListAndBack_thenReturnsSameString() {
        String originalString = "3,4";
        List<Genre> genres = GenreListConverter.toGenreList(originalString);
        String converted = GenreListConverter.fromGenreList(genres);

        assertEquals(originalString, converted);
    }
}
