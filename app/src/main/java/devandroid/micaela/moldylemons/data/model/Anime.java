package devandroid.micaela.moldylemons.data.model;

import java.util.List;

import devandroid.micaela.moldylemons.data.model.enums.Demographic;
import devandroid.micaela.moldylemons.data.model.enums.Genre;
import devandroid.micaela.moldylemons.data.model.enums.MediaType;

public class Anime extends Serie {
    private String studio;
    private Demographic demographic;

    public Anime(int id, String title, String description, List<Genre> genres, int seasons, int totalEpisodes, String studio,
                 Demographic demographic, Couple couple){

        super(id, title, description,genres, seasons, totalEpisodes, couple);
        this.setStudio(studio);
        this.setDemographic(demographic);
    }
    public Anime(String title, String description, List<Genre> genres, int seasons, int totalEpisodes, String studio,
                 Demographic demographic, Couple couple){

        super(title, description, genres, seasons,totalEpisodes, couple);
        this.setStudio(studio);
        this.setDemographic(demographic);
    }

    public Anime(String title, String description, List<Genre> genres, Couple couple){
        super(title, description, genres, couple);
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
