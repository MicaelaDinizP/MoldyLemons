package devandroid.micaela.moldylemons;

import java.util.Arrays;
import java.util.List;
public class Anime extends Serie{
    private String studio;
    private Demographic demographic;

    public Anime(int id, String title, String description, List<Genre> genres, int seasons, int totalEpisodes, String studio,
                 Demographic demographic){

        super(id, title, description,genres, seasons, totalEpisodes);
        this.setStudio(studio);
        this.setDemographic(demographic);
    }
    public Anime(String title, String description, List<Genre> genres, int seasons, int totalEpisodes, String studio,
                 Demographic demographic){

        super(title, description, genres, seasons,totalEpisodes);
        this.setStudio(studio);
        this.setDemographic(demographic);
    }

    public Anime(String title, String description, List<Genre> genres){
        super(title, description, genres);
    }
    protected MediaType defineMediaType() {
        return MediaType.ANIME;
    }

    protected boolean isValidGenre(Genre genre){
        return genre != null && (genre.getMediaType() == MediaType.ALL || genre.getMediaType() == MediaType.ANIME);
    }
    public Demographic getDemographic() {
        return this.demographic;
    }

    public void setDemographic(Demographic demographic) {
        this.demographic = demographic;
    }
    public String getStudio() {
        return this.studio;
    }
    public void setStudio(String studio) {
        this.studio = studio;
    }
}
