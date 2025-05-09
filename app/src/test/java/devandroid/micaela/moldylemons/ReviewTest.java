package devandroid.micaela.moldylemons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import devandroid.micaela.moldylemons.data.model.Review;

public class ReviewTest{
    private Review review;
    private String title;
    private String content;
    private String writtenBy;

    @BeforeEach
    void setUp() {
        this.title = "Best movie ever!";
        this.content = "This movie is very funny";
        this.writtenBy = "Mariah";
        this.review = new Review(1,title ,content , writtenBy, "😊",5, 1,1);
    }

    @Test
    void givenAllFields_whenCreatingReview_thenReviewIsCreatedSuccessfully() {
        assertNotNull( this.review);
        assertEquals(1,  this.review.getId());
        assertEquals( this.title,  this.review.getTitle());
        assertEquals( this.content,  this.review.getContent());
        assertEquals( this.writtenBy,  this.review.getWrittenBy());
        assertEquals(5,  this.review.getRating());
        assertEquals("😊",  this.review.getReactionEmoji());
        assertEquals(1, this.review.getMediaId());
    }

    @Test
    void givenOnlyRequiredFields_whenCreatingReview_thenReviewIsCreatedSuccessfully() {
        Review review = new Review(1, null, null, "John", "👍", 4, 1,1);

        assertEquals(1, review.getId());
        assertNull(review.getTitle());
        assertNull(review.getContent());
        assertEquals("John", review.getWrittenBy());
        assertEquals(4, review.getRating());
        assertEquals("👍", review.getReactionEmoji());
        assertEquals(1, this.review.getMediaId());
    }

    @Test
    void givenNullRequiredFields_whenCreatingReview_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Review review = new Review(0, null, null, null, null, 0, 1,0);
        });
    }

    @Test
    void givenEmptyWrittenBy_whenCreatingReview_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Review(2, "Title", "Description", "", "😐", 3, 1,1);
        });

        assertEquals("Author cannot be null or empty.", exception.getMessage());
    }

    @Test
    void givenNullWrittenBy_whenCreatingReview_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Review(2, "Title", "Description", null, "😐", 3, 1,1);
        });

        assertEquals("Author cannot be null or empty.", exception.getMessage());
    }

    @Test
    void givenNullReactionEmoji_whenCreatingReview_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Review(2, "Title", "Description", "Carla", null, 5, 1,1);
        });

        assertEquals("Reaction must be a valid emoji.", exception.getMessage());
    }
    @Test
    void givenInvalidReactionEmoji_whenCreatingReview_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Review(2, "Title", "Description", "Carla", "abc123", 5,1,1);
        });

        assertEquals("Reaction must be a valid emoji.", exception.getMessage());
    }

    @Test
    void givenZeroRating_whenCreatingReview_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Review(2, "Title", "Description", "Meggie", "😐", 0, 1,1);
        });

        assertEquals("Rating must be between 1 and 5.", exception.getMessage());
    }
    @Test
    void givenInvalidRating_whenCreatingReview_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Review(2, "Title", "Description", "Meggie", "😐", 15, 1,1);
        });

        assertEquals("Rating must be between 1 and 5.", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(ints = { 0, -1 })
    void givenInvalidMediaId_whenCreatingReview_thenThrowsIllegalArgumentException(int invalidMediaId) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Review(2, "Title", "Description", "Meggie", "😐", 5, 1,invalidMediaId);
        });

        assertEquals("Must be a valid ID.", exception.getMessage());
    }

    @Test
    void givenInvalidRating_whenSettingRating_thenThrowsIllegalArgumentException(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            review.setRating(-1);
        });
        assertEquals("Rating must be between 1 and 5.", exception.getMessage());
    }

    @Test
    void givenInvalidReactionEmoji_whenUpdatingReview_thenThrowsIllegalArgumentException(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            review.setReactionEmoji("abc123");
        });
        assertEquals("Reaction must be a valid emoji.", exception.getMessage());
    }

    @Test
    void givenNullWrittenBy_whenUpdatingReview_thenThrowsIllegalArgumentException(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            review.setWrittenBy(null);
        });
        assertEquals("Author cannot be null or empty.", exception.getMessage());
    }

    @Test
    void givenEmptyWrittenBy_whenUpdatingReview_thenThrowsIllegalArgumentException(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            review.setWrittenBy(" ");
        });
        assertEquals("Author cannot be null or empty.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"😀", "🌍", "🚀", "⚗️", "🟠", "🠔", "🤖", "🫠", "☀️", "✂️","🇧🇷"})
    void givenValidEmojis_whenSettingReactionEmoji_thenDoesNotThrow(String emoji) {
        Review review = new Review(1, "A", "B", "C", "😊", 5, 1,1);
        assertDoesNotThrow(() -> this.review.setReactionEmoji(emoji));
    }

    @Test
    void givenMinRating_whenCreatingReview_thenSucceeds() {
        this.review.setRating(1);
        assertEquals(1, this.review.getRating());
    }

    @Test
    void givenMaxRating_whenCreatingReview_thenSucceeds() {
        this.review.setRating(5);
        assertEquals(5, this.review.getRating());
    }

    @Test
    void givenNullOrEmptyTitleAndContent_whenCreatingReview_thenSucceeds() {
        this.review.setTitle(null);
        this.review.setContent(null);
        assertEquals(null, this.review.getTitle());
        assertEquals(null, this.review.getContent());

        this.review.setTitle(" ");
        this.review.setContent(" ");
        assertEquals(" ", this.review.getTitle());
        assertEquals(" ", this.review.getContent());

    }
    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void givenInvalidId_whenSettingId_thenThrowsIllegalArgumentException(int invalidId){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            review.setId(invalidId);
        });
        assertEquals("Must be a valid ID.", exception.getMessage());
    }
    @Test
    void givenValidId_whenSettingId_thenSucceeds(){
        this.review.setId(4);
        assertEquals(4, review.getId());
    }
    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void givenInvalidCoupleId_whenSettingCoupleId_thenThrowsIllegalArgumentException(int invalidCoupleId){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            review.setCoupleId((invalidCoupleId));
        });
        assertEquals("Must be a valid ID.", exception.getMessage());
    }
    @Test
    void givenValidCoupleId_whenSettingCoupleId_thenSucceeds(){
        this.review.setCoupleId(4);
        assertEquals(4, review.getCoupleId());
    }
    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void givenInvalidMediaId_whenSettingMediaId_thenThrowsIllegalArgumentException(int invalidMediaId){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            review.setMediaId(invalidMediaId);
        });
        assertEquals("Must be a valid ID.", exception.getMessage());
    }
    @Test
    void givenValidMediaId_whenSettingMediaId_thenSucceeds(){
        this.review.setMediaId(4);
        assertEquals(4, review.getMediaId());
    }
}
