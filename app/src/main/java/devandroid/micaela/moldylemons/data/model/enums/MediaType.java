package devandroid.micaela.moldylemons.data.model.enums;

public enum MediaType {
    ALL("All"),
    ANIME("Anime"),
    SERIES("Series");

    private final String mediaTypeString;

    MediaType(String mediaTypeString) {
        this.mediaTypeString = mediaTypeString;
    }
    public String getMediaTypeString() {
        return mediaTypeString;
    }
}
