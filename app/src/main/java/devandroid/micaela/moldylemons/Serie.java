package devandroid.micaela.moldylemons;

import java.util.List;

import devandroid.micaela.moldylemons.Genre;
import devandroid.micaela.moldylemons.Media;

public class Serie extends Media {
    private int seasons;
    private int totalEpisodes;

    public Serie(int id, String title, String description, List<Genre> genres, int seasons, int totalEpisodes){
        super(id, title, description,genres);
        this.setSeasons(seasons);
        this.setTotalEpisodes(totalEpisodes);
    }
    public Serie(String title, String description, List<Genre> genres, int seasons, int totalEpisodes){
        super(title, description, genres);
        this.setSeasons(seasons);
        this.setTotalEpisodes(totalEpisodes);
    }
    public Serie(String title, String description, List<Genre> genres){
        super(title, description, genres);
    }
    @Override
    protected MediaType defineMediaType() {
        return MediaType.SERIES;
    }
    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        if (seasons < 0) {
            throw new IllegalArgumentException("Seasons must be greater than or equal to zero.");
        }
        this.seasons = seasons;
    }

    public int getTotalEpisodes() {
        return totalEpisodes;
    }

    public void setTotalEpisodes(int totalEpisodes) {
        if (totalEpisodes < 0) {
            throw new IllegalArgumentException("Total episodes must be greater than or equal to zero.");
        }
        this.totalEpisodes = totalEpisodes;
    }
}
