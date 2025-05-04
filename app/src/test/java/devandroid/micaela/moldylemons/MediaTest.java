package devandroid.micaela.moldylemons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

import devandroid.micaela.moldylemons.data.model.Couple;
import devandroid.micaela.moldylemons.data.model.Media;
import devandroid.micaela.moldylemons.data.model.Review;
import devandroid.micaela.moldylemons.data.model.enums.Genre;
import devandroid.micaela.moldylemons.data.model.enums.MediaType;

class MediaTest {
    private Media media;
    private Genre validGenre;
    private Genre invalidGenre;
    private Review reviewPersonOne;
    private String writtenByPersonOne;
    private String writtenByPersonTwo;
    private Review reviewPersonTwo;
    private Couple couple;

    static class TestMedia extends Media {
        public TestMedia(String title, String description, List<Genre> genres, Couple couple) {
            super(title, description, genres, couple);
        }
        public TestMedia(int id,String title, String description, List<Genre> genres, Couple couple) {
            super(id,title, description, genres, couple);
        }
        @Override
        protected MediaType defineMediaType() {
            return MediaType.ALL;
        }
    }

    @BeforeEach
    void setUp() {
        Calendar cal = Calendar.getInstance();
        cal.set(2020, Calendar.JANUARY, 1);
        Date validDate = cal.getTime();
        this.validGenre = Genre.ACTION;
        this.invalidGenre = Genre.ISEKAI;
        this.writtenByPersonOne = "PersonOne";
        this.writtenByPersonTwo = "PersonTwo";
        this.couple = new Couple(this.writtenByPersonOne, this.writtenByPersonTwo, validDate, "user_123", "pass1234");
        this.couple.setId(1);
        this.media = new TestMedia("Test Title", "Test Description", Arrays.asList(validGenre), couple);
        this.reviewPersonOne = new Review("Review", "Content", this.writtenByPersonOne ,"😀",5,1);
        this.reviewPersonTwo = new Review("Review", "Content", this.writtenByPersonTwo ,"😀",5,1);
    }

    @Test
    void givenAllValidData_whenCreatingMedia_thenCreateSucessfully(){
        List<Genre> validGenres = new ArrayList<>();
        validGenres.add(Genre.ACTION);
        validGenres.add(Genre.COMEDY);

        Media validMedia = new TestMedia("TheBestMovie", "Action and comedy movie", validGenres, this.couple);
        validMedia.addReview(this.reviewPersonOne);
        validMedia.addReview(this.reviewPersonTwo);
        assertEquals("TheBestMovie", validMedia.getTitle());
        assertEquals("Action and comedy movie", validMedia.getDescription());
        assertTrue(validMedia.getGenres().containsAll(validGenres));
        assertEquals(2, validMedia.getReviews().size());
        assertEquals(MediaType.ALL, validMedia.getMediaType());
    }

    @Test
    void givenNullTittle_whenCreatingMedia_thenThrowsIllegalException(){
        List<Genre> validGenres = new ArrayList<>();
        validGenres.add(Genre.ACTION);
        validGenres.add(Genre.COMEDY);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Media invalidMedia = new TestMedia(null, "Action and comedy movie", validGenres, this.couple);
        });
        assertEquals("Title cannot be null or empty.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n"})
    void givenEmptyTittle_whenCreatingMedia_thenThrowsIllegalException(String invalidTitle){
        List<Genre> validGenres = new ArrayList<>();
        validGenres.add(Genre.ACTION);
        validGenres.add(Genre.COMEDY);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Media invalidMedia = new TestMedia(invalidTitle, "Action and comedy movie", validGenres, this.couple);
        });
        assertEquals("Title cannot be null or empty.", exception.getMessage());
    }

    @Test
    void givenInvalidGenreList_whenCreatingMedia_thenThrowsIllegalException(){
        List<Genre> invalidGenres = new ArrayList<>();
        invalidGenres.add(this.invalidGenre);
        invalidGenres.add(Genre.COMEDY);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Media invalidMedia = new TestMedia("TheBestMovie", "Action and comedy movie", invalidGenres, this.couple);
        });
        assertEquals("Invalid genre for this type of media.", exception.getMessage());
    }

    @Test
    void givenNullGenreList_whenCreatingMedia_thenThrowsIllegalException(){
        List<Genre> invalidGenres = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Media invalidMedia = new TestMedia("TheBestMovie", "Action and comedy movie", invalidGenres, this.couple);
        });
        assertEquals("At least one genre must be provided.", exception.getMessage());
    }

    @Test
    void givenEmptyGenreList_whenCreatingMedia_thenThrowsIllegalException(){
        List<Genre> invalidGenres = new ArrayList<>();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Media invalidMedia = new TestMedia("TheBestMovie", "Action and comedy movie", invalidGenres, this.couple);
        });
        assertEquals("At least one genre must be provided.", exception.getMessage());
    }
    @Test
    void givenNullCouple_whenCreatingMedia_thenThrowsIllegalException(){
        List<Genre> validGenres = new ArrayList<>();
        validGenres.add(Genre.ACTION);
        validGenres.add(Genre.COMEDY);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Media invalidMedia = new TestMedia("TheBestMovie", "Action and comedy movie", validGenres, null);
        });
        assertEquals("Couple cannot be null.", exception.getMessage());
    }
    @Test
    void givenCoupleWithInvalidId_whenCreatingMedia_thenThrowsIllegalException(){
        List<Genre> validGenres = new ArrayList<>();
        validGenres.add(Genre.ACTION);
        validGenres.add(Genre.COMEDY);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.couple.setId(0);
            Media invalidMedia = new TestMedia("TheBestMovie", "Action and comedy movie", validGenres, this.couple);
        });
        assertEquals("ID must be greater than zero.", exception.getMessage());
    }

    @Test
    void whenGettingMediaType_thenReturnsCorrectType() {
        assertEquals(MediaType.ALL, this.media.getMediaType());
    }

    @Test
    void givenValidGenre_whenAddGenreCalled_thenGenreIsAdded() {
        Genre newGenre = Genre.DRAMA;
        this.media.addGenre(newGenre);
        assertTrue(media.getGenres().contains(newGenre));
    }

    @Test
    void givenInvalidGenre_whenAddGenreCalled_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            media.addGenre(invalidGenre);
        });
        assertFalse(this.media.getGenres().contains(this.invalidGenre));
        assertEquals("Invalid genre for this type of media.", exception.getMessage());
    }

    @Test
    void givenNullGenre_whenAddGenreCalled_thenThrowsIllegalArgumentException(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            media.addGenre(null);
        });
        assertFalse(this.media.getGenres().contains(null));
        assertEquals("Genre cannot be null.", exception.getMessage());
    }

    @Test
    void givenValidGenre_whenRemoveGenreCalled_thenRemoveGenreSuccessfully() {
        this.media.removeGenre(this.validGenre);
        assertFalse(this.media.getGenres().contains(this.validGenre));
    }

    @Test
    void givenInvalidGenre_whenRemoveGenreCalled_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            media.removeGenre(invalidGenre);
        });
        assertFalse(this.media.getGenres().contains(this.invalidGenre));
        assertEquals("Genre not found in the list.", exception.getMessage());
    }

    @Test
    void givenNullGenre_whenRemoveGenreCalled_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            media.removeGenre(null);
        });
        assertFalse(this.media.getGenres().contains(null));
        assertEquals("Genre cannot be null.", exception.getMessage());
    }

    @Test
    void givenValidReview_whenAddReviewCalled_thenAddReviewSucessfully() {
        this.media.addReview(this.reviewPersonOne);
        assertTrue(this.media.getReviews().contains(reviewPersonOne));
        Review receivedReview = this.media.getReviews().get(0);
        assertEquals(receivedReview.getWrittenBy(), this.writtenByPersonOne);
    }

    @Test
    void givenValidSecondPersonReview_whenAddReviewCalled_thenAddReviewSucessfully() {
        this.media.addReview(this.reviewPersonOne);
        this.media.addReview(this.reviewPersonTwo);
        assertTrue(this.media.getReviews().contains(reviewPersonOne));
        assertTrue(this.media.getReviews().contains(reviewPersonTwo));

        Review receivedReview = this.media.getReviews().get(0);
        assertEquals(receivedReview.getWrittenBy(), this.writtenByPersonOne);

        Review receivedReview2 = this.media.getReviews().get(1);
        assertEquals(receivedReview2.getWrittenBy(), this.writtenByPersonTwo);
    }

    @Test
    void givenValidDuplicatedReview_whenAddReviewCalled_thenThrowsIllegalArgumentException() {
        this.media.addReview(this.reviewPersonOne);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            media.addReview(reviewPersonOne);
        });
        assertEquals(this.media.getReviews().size(),1);
        assertEquals("User has already submitted a review for this media.", exception.getMessage());
    }

    @Test
    void givenValidReview_whenRemoveReviewByReviewCalled_thenRemoveSucessfully() {
        this.media.addReview(this.reviewPersonOne);
        this.media.removeReview(this.reviewPersonOne);
        assertTrue(this.media.getReviews().isEmpty());
    }

    @Test
    void givenNullReview_whenRemoveReviewByReviewCalled_thenThrowsIllegalArgumentException() {
        this.media.addReview(this.reviewPersonOne);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.media.removeReview((Review) null);
        });
        assertFalse(this.media.getReviews().isEmpty());
        assertEquals("Review not found.", exception.getMessage());
    }

    @Test
    void givenInvalidReview_whenRemoveReviewByReviewCalled_thenThrowsIllegalArgumentException() {
        this.media.addReview(this.reviewPersonOne);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.media.removeReview(reviewPersonTwo);
        });
        assertFalse(this.media.getReviews().isEmpty());
        assertEquals("Review not found.", exception.getMessage());
    }

    @Test
    void givenValidAuthor_whenRemoveReviewByAuthorCalled_thenRemoveSucessfully() {
        this.media.addReview(this.reviewPersonOne);
        this.media.removeReview(this.writtenByPersonOne);
        assertTrue(this.media.getReviews().isEmpty());
    }

    @Test
    void givenInvalidAuthor_whenRemoveReviewByAuthorCalled_thenThrowsIllegalArgumentException() {
        this.media.addReview(this.reviewPersonOne);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.media.removeReview(writtenByPersonTwo);
        });
        assertEquals("No review found for the given author.", exception.getMessage());
        assertFalse(this.media.getReviews().isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n"})
    void givenEmptyAuthor_whenRemoveReviewByAuthorCalled_thenThrowsIllegalArgumentException(String input) {
        this.media.addReview(this.reviewPersonOne);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.media.removeReview(input);
        });
        assertEquals("Author name cannot be null or empty.", exception.getMessage());
        assertFalse(this.media.getReviews().isEmpty());
    }

    @Test
    void givenNullAuthor_whenRemoveReviewByAuthorCalled_thenThrowsIllegalArgumentException() {
        this.media.addReview(this.reviewPersonOne);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.media.removeReview((String) null);
        });
        assertEquals("Author name cannot be null or empty.", exception.getMessage());
        assertFalse(this.media.getReviews().isEmpty());
    }

    @Test
    void givenValidTitle_whenSetTittleCalled_thenSetSucessfully() {
        this.media.addReview(this.reviewPersonOne);
        this.media.setTitle("The Movie");
        assertEquals(this.media.getTitle(), "The Movie");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n"})
    void givenEmptyTitle_whenSetTittleCalled_thenThrowsIllegalArgumentException(String input) {
        this.media.addReview(this.reviewPersonOne);
        String initialTitle = this.media.getTitle();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.media.setTitle(input);
        });
        assertEquals("Title cannot be null or empty.", exception.getMessage());
        assertEquals(this.media.getTitle(), initialTitle);
    }

    @Test
    void givenNullTitle_whenSetTittleCalled_thenThrowsIllegalArgumentException() {
        this.media.addReview(this.reviewPersonOne);
        String initialTitle = this.media.getTitle();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.media.setTitle(null);
        });
        assertEquals("Title cannot be null or empty.", exception.getMessage());
        assertEquals(this.media.getTitle(), initialTitle);
    }

    @Test
    void givenListWithValidGenres_whenSetGenresCalled_thenSetSucessfully() {
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ACTION);
        genres.add(Genre.COMEDY);
        genres.add(Genre.FANTASY);

        this.media.setGenres(genres);

        assertTrue(this.media.getGenres().contains(Genre.ACTION));
        assertTrue(this.media.getGenres().contains(Genre.COMEDY));
        assertTrue(this.media.getGenres().contains(Genre.FANTASY));

        assertEquals(this.media.getGenres(), genres);
    }

    @Test
    void givenListWithInvalidGenres_whenSetGenresCalled_thenThrowsIllegalArgumentException() {
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ACTION);
        genres.add(Genre.COMEDY);
        genres.add(Genre.ISEKAI);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.media.setGenres(genres);
        });
        assertEquals("Invalid genre for this type of media.", exception.getMessage());
        assertFalse(this.media.getGenres().contains(Genre.ISEKAI));

        genres.remove(Genre.ISEKAI);
        genres.add(Genre.SITCOM);;
        assertThrows(IllegalArgumentException.class, () -> {
            this.media.setGenres(genres);
        });
        assertEquals("Invalid genre for this type of media.", exception.getMessage());
        assertFalse(this.media.getGenres().contains(Genre.SITCOM));
    }

    @Test
    void givenNullGenreList_whenSetGenresCalled_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.media.setGenres(null);
        });
        assertEquals("At least one genre must be provided.", exception.getMessage());
    }

    @Test
    void givenEmptyGenreList_whenSetGenresCalled_thenThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.media.setGenres(new ArrayList<Genre>());
        });
        assertEquals("At least one genre must be provided.", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("provideValidReviewLists")
    void givenValidReviewList_whenSetReviews_thenSetSuccessfully(List<Review> reviews) {
        this.media.setReviews(reviews);
        assertEquals(this.media.getReviews(), reviews);
        assertEquals(this.media.getReviews().size(), reviews.size());
    }
    static Stream<Arguments> provideValidReviewLists() {
        Review reviewPersonOne = new Review("Bad", "Im shaking", "bonnie","\"\uD83D\uDE00\"", 5, 1);
        Review reviewPersonTwo = new Review("Excelent", "im bored", "clyde", "\"\uD83D\uDE00\"",1,1);
        return Stream.of(
                Arguments.of(Arrays.asList(reviewPersonOne)),
                Arguments.of(Arrays.asList(reviewPersonOne, reviewPersonTwo))
        );
    }

    @Test
    void givenReviewListWithSameAuthor_whenSetReviews_thenThrowsIllegalArgumentException() {
        List<Review> duplicateReviews = new ArrayList<>();
        duplicateReviews.add(this.reviewPersonOne);
        duplicateReviews.add(this.reviewPersonOne);
        duplicateReviews.add(this.reviewPersonTwo);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            media.setReviews(duplicateReviews);
        });

        assertEquals("User has already submitted a review for this media.", exception.getMessage());
        assertNotEquals(this.media.getReviews().size(), 3);
    }

    @Test
    void givenNullReviewList_whenSetReviews_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            media.setReviews(null);
        });
        assertEquals("At least one review must be provided.", exception.getMessage());
        assertEquals(this.media.getReviews().size(), 0);
    }

    @Test
    void givenEmptyReviewList_whenSetReviews_thenThrowsIllegalArgumentException() {
        List<Review> reviewList = new ArrayList<>();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            media.setReviews(reviewList);
        });
        assertEquals("At least one review must be provided.", exception.getMessage());
        assertEquals(this.media.getReviews().size(), 0);
    }

    @Test
    void givenDuplicatedGenre_whenSetGenreCalled_thenThrowsIllegalArgumentException() {
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ACTION);
        genres.add(Genre.ACTION);
        genres.add(Genre.COMEDY);
        genres.add(Genre.ISEKAI);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.media.setGenres(genres);
        });
        assertEquals("Genre already added.", exception.getMessage());
    }
    @Test
    void givenValidCouple_whenSettingCouple_thenSucceeds(){
        List<Genre> validGenres = new ArrayList<>();
        validGenres.add(Genre.ACTION);
        validGenres.add(Genre.COMEDY);
        this.media.setCouple(this.couple);
        assertEquals(this.couple, this.media.getCouple());
    }

    @Test
    void givenNullCouple_whenSettingCouple_thenThrowsIllegalException(){
        List<Genre> validGenres = new ArrayList<>();
        validGenres.add(Genre.ACTION);
        validGenres.add(Genre.COMEDY);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.media.setCouple(null);
        });
        assertEquals("Couple cannot be null.", exception.getMessage());
    }
    @Test
    void givenCoupleWithInvalidId_whenSettingCouple_thenThrowsIllegalException(){
        List<Genre> validGenres = new ArrayList<>();
        validGenres.add(Genre.ACTION);
        validGenres.add(Genre.COMEDY);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.couple.setId(-1);
            this.media.setCouple(this.couple);
        });
        assertEquals("ID must be greater than zero.", exception.getMessage());
    }

}