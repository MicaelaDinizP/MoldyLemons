package devandroid.micaela.moldylemons;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Media {
    private int id;
    private String title;
    private String description;
    private List<Genre> genres;
    private List<Review> reviews;
    protected final MediaType mediaType;

    public Media( int id, String title, String description, List<Genre> genres ){
        this.mediaType = defineMediaType();
        setId(id);
        setTitle(title);
        setDescription(description);
        setGenres(genres);
        this.reviews = new ArrayList<>();
    }
    public Media(String title, String description, List<Genre> genres ){
        this.mediaType = defineMediaType();
        setTitle(title);
        setDescription(description);
        setGenres(genres);
        this.reviews = new ArrayList<>();

    }
    protected abstract MediaType defineMediaType();
    protected boolean isValidGenre(Genre genre){
        return genre != null && (genre.getMediaType() == MediaType.ALL || genre.getMediaType() == this.getMediaType());
    }
    public void addGenre(Genre genre) {
        if(this.genres == null){
            this.genres = new ArrayList<>();
        }
        if (genre == null) {
            throw new IllegalArgumentException("Genre cannot be null.");
        }
        if (!isValidGenre(genre)) {
            throw new IllegalArgumentException("Invalid genre for this type of media.");
        }
        if (this.genres.contains(genre)) {
            throw new IllegalArgumentException("Genre already added.");
        }

        this.genres.add(genre);
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
        if (review == null) {
            throw new IllegalArgumentException("Review cannot be null.");
        }
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

        Iterator<Review> iterator = reviews.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getWrittenBy().equals(authorName)) {
                iterator.remove();
                return;
            }
        }

        throw new IllegalArgumentException("No review found for the given author.");
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
        this.genres = new ArrayList<>();
        if (genres == null || genres.isEmpty()) {
            throw new IllegalArgumentException("At least one genre must be provided.");
        }
        for (Genre genre : genres) {
                this.addGenre(genre);
        }
    }
    public List<Review> getReviews() {
        return this.reviews;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = new ArrayList<>();
        if (reviews == null || reviews.isEmpty()) {
            throw new IllegalArgumentException("At least one review must be provided.");
        }
        for (Review review : reviews) {
            this.addReview(review);
        }
    }
    public MediaType getMediaType() {
        return mediaType;
    }
}

