package devandroid.micaela.moldylemons.data.model;
public class Review {
    private int id;
    private String title;
    private String content;
    private String writtenBy;
    private String reactionEmoji;
    private int rating;

    public Review(int id, String title, String content, String writtenBy, String reactionEmoji, int rating) {
        this.id = id;
        this.title = title;
        this.content = content;
        setWrittenBy(writtenBy);
        setReactionEmoji(reactionEmoji);
        setRating(rating);
    }
    public Review(String title, String content, String writtenBy, String reactionEmoji, int rating) {
        this.title = title;
        this.content = content;
        setWrittenBy(writtenBy);
        setReactionEmoji(reactionEmoji);
        setRating(rating);
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
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWrittenBy() {
        return this.writtenBy;
    }

    public void setWrittenBy(String writtenBy) {
        if (writtenBy == null || writtenBy.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty.");
        }
        this.writtenBy = writtenBy;
    }

    public String getReactionEmoji() {
        return this.reactionEmoji;
    }

    public void setReactionEmoji(String reactionEmoji) {
        if (reactionEmoji == null || !containsEmoji(reactionEmoji)) {
            throw new IllegalArgumentException("Reaction must be a valid emoji.");
        }
        this.reactionEmoji = reactionEmoji;
    }
    public int getRating() {
        return this.rating;
    }
    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        this.rating = rating;
    }
    private boolean containsEmoji(String input) {
        return input.codePoints().anyMatch(codePoint ->
                (codePoint >= 0x1F600 && codePoint <= 0x1F64F) || // emoticons
                        (codePoint >= 0x1F300 && codePoint <= 0x1F5FF) || // symbols & pictographs
                        (codePoint >= 0x1F680 && codePoint <= 0x1F6FF) || // transport & map
                        (codePoint >= 0x1F700 && codePoint <= 0x1F77F) || // alchemical
                        (codePoint >= 0x1F780 && codePoint <= 0x1F7FF) || // geometric
                        (codePoint >= 0x1F800 && codePoint <= 0x1F8FF) || // supplemental arrows
                        (codePoint >= 0x1F900 && codePoint <= 0x1F9FF) || // supplemental symbols
                        (codePoint >= 0x1FA70 && codePoint <= 0x1FAFF) || // extended A
                        (codePoint >= 0x2600 && codePoint <= 0x26FF) ||   // misc symbols
                        (codePoint >= 0x2700 && codePoint <= 0x27BF) ||   // dingbats
                        (codePoint >= 0x1F1E6 && codePoint <= 0x1F1FF)     // regional indicator symbols (sao as bandeiras)
        );
    }

}