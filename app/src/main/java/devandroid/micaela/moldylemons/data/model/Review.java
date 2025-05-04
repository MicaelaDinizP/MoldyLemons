package devandroid.micaela.moldylemons.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "review")
public class Review {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "content")
    private String content;
    @ColumnInfo(name = "written_by")
    private String writtenBy;
    @ColumnInfo(name = "reaction_emoji")
    private String reactionEmoji;
    @ColumnInfo(name = "rating")
    private int rating;
    @ColumnInfo(name = "couple_id") //é uma chave estrangeira, arrumar isso quando puder
    private int coupleId;

    public Review(int id, String title, String content, String writtenBy, String reactionEmoji, int rating, int coupleId) {
        this.setId(id);
        this.setTitle(title);
        this.setContent(content);
        this.setCoupleId(coupleId);
        this.setWrittenBy(writtenBy);
        this.setReactionEmoji(reactionEmoji);
        this.setRating(rating);
    }
    @Ignore
    public Review(String title, String content, String writtenBy, String reactionEmoji, int rating, int coupleId) {
        this.setTitle(title);
        this.setContent(content);
        this.setCoupleId(coupleId);
        this.setWrittenBy(writtenBy);
        this.setReactionEmoji(reactionEmoji);
        this.setRating(rating);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }
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
    public int getCoupleId() {
        return coupleId;
    }

    public void setCoupleId(int coupleId) {
        if (coupleId <= 0) {
            throw new IllegalArgumentException("Must be a valid ID.");
        }
        this.coupleId = coupleId;
    }
}