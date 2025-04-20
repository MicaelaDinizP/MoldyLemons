package devandroid.micaela.moldylemons;

import java.util.ArrayList;
import java.util.List;

public abstract class Media {
    private int id;
    private String title;
    private String description;
    private List<Genre> genres;
    private List<Review> reviews;
    private final MediaType mediaType;

    public Media( int id, String title, String description, List<Genre> genres ){
        setId(id);
        setTitle(title);
        setDescription(description);
        setGenres(genres);
        this.reviews = new ArrayList<>();
        this.mediaType = defineMediaType();
    }
    public Media(String title, String description, List<Genre> genres ){
        setTitle(title);
        setDescription(description);
        setGenres(genres);
        this.reviews = new ArrayList<>();
        this.mediaType = defineMediaType();
    }
    protected abstract MediaType defineMediaType();
    protected boolean isValidGenre(Genre genre){
        return genre != null && (genre.getMediaType() == MediaType.ALL || genre.getMediaType() == this.getMediaType());
    }
    public void addGenre(Genre genre) {
        if (genre == null) return;
        if (!isValidGenre(genre)) {
            throw new IllegalArgumentException("Invalid genre for this type of media.");
        }
        genres.add(genre);
    }

    public void removeGenre(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Genre cannot be null.");
        }

        if (!genres.contains(genre)) {
            throw new IllegalArgumentException("Genre not found in the list.");
        }

        this.genres.remove(genre);
    }

    public void addReview(Review review) {
        if (review == null) return;
        for (Review r : reviews) {
            if (r.getWrittenBy().equals(review.getWrittenBy())) {
                throw new IllegalArgumentException("User has already submitted a review for this media.");
            }
        }
        this.reviews.add(review);
    }
    public void removeReview(Review review) {
        if (review == null || !reviews.contains(review)) {
            throw new IllegalArgumentException("Review not found.");
        }
        reviews.remove(review);
    }
    public void removeReview(String authorName) {
        if (authorName == null || authorName.trim().isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be null or empty.");
        }

        boolean removed = reviews.removeIf(r -> r.getWrittenBy().equals(authorName));

        if (!removed) {
            throw new IllegalArgumentException("No review found for the given author.");
        }
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.title = title;
    }
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Genre> getGenres() {
        return this.genres;
    }
    public void setGenres(List<Genre> genres) {
        List<Genre> newGenres = new ArrayList<Genre>();

        if (genres == null || genres.isEmpty()) {
            throw new IllegalArgumentException("At least one genre must be provided.");
        }
        for (Genre genre : genres) {
            if(isValidGenre(genre)){
                newGenres.add(genre);
            }
        }
        this.genres = newGenres;
    }

    public List<Review> getReviews() {
        return this.reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    public MediaType getMediaType() {
        return mediaType;
    }
}

