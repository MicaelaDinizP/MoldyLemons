package devandroid.micaela.moldylemons;

import java.util.List;

import devandroid.micaela.moldylemons.Genre;
import devandroid.micaela.moldylemons.Media;

public class Movie extends Media{
    private int duration;
    public Movie(int id, String title, String description, List<Genre> genres, int duration){
        super(id, title, description,genres);
        this.setDuration(duration);
    }
    public Movie(String title, String description, List<Genre> genres, int duration){
        super(title, description, genres);
        this.setDuration(duration);
    }
    @Override
    protected MediaType defineMediaType() {
        return MediaType.ALL;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be a positive number.");
        }
        if (duration > 1000 * 60) {
            throw new IllegalArgumentException("Duration is unreasonably long.");
        }
        this.duration = duration;
    }

}
