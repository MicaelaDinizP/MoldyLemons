package devandroid.micaela.moldylemons;

public enum Genre {
    ACTION(1, "Action", MediaType.ALL),
    MYSTERY(2, "Mystery", MediaType.ALL),
    DRAMA(3, "Drama", MediaType.ALL),
    COMEDY(4, "Comedy", MediaType.ALL),
    ROMANCE(5, "Romance", MediaType.ALL),
    FANTASY(6, "Fantasy", MediaType.ALL),
    HORROR(7, "Horror", MediaType.ALL),
    THRILLER(8, "Thriller", MediaType.ALL),
    DOCUMENTARY(9, "Documentary", MediaType.ALL),
    SHOUNEN(10, "Shounen", MediaType.ANIME),
    SHOUJO(11, "Shoujo", MediaType.ANIME),
    MECHA(12, "Mecha", MediaType.ANIME),
    ISEKAI(13, "Isekai", MediaType.ANIME),
    YAOI(14, "Yaoi", MediaType.ANIME),
    YURI(15, "Yuri", MediaType.ANIME),
    SITCOM(16, "Sitcom", MediaType.SERIES),
    CRIME(17, "Crime", MediaType.SERIES);

    private final int id;
    private final String name;
    private final MediaType mediaType;

    Genre(int id, String name, MediaType mediaType) {
        this.id = id;
        this.name = name;
        this.mediaType = mediaType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public String getMediaTypeString() {
        return mediaType.getMediaTypeString();
    }

    public boolean isAnime() {
        return mediaType == MediaType.ANIME;
    }

    public boolean isSeries() {
        return mediaType == MediaType.SERIES;
    }

    public boolean isMovie() {
        return mediaType == MediaType.ALL;
    }
}
