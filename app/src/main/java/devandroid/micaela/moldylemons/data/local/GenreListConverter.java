package devandroid.micaela.moldylemons.data.local;

import androidx.room.TypeConverter;
import devandroid.micaela.moldylemons.data.model.enums.Genre;

import java.util.ArrayList;
import java.util.List;

public class GenreListConverter {

    @TypeConverter
    public static String fromGenreList(List<Genre> genres) {
        if (genres == null || genres.isEmpty()) return "";
        StringBuilder ids = new StringBuilder();
        for (Genre genre : genres) {
            ids.append(genre.getId()).append(",");
        }

        if (ids.length() > 0) {
            ids.setLength(ids.length() - 1);
        }
        return ids.toString();
    }

    @TypeConverter
    public static List<Genre> toGenreList(String data) {
        List<Genre> result = new ArrayList<>();
        if (data == null || data.trim().isEmpty()) return result;

        String[] parts = data.split(",");
        for (String part : parts) {
            try {
                int id = Integer.parseInt(part.trim());
                for (Genre genre : Genre.values()) {
                    if (genre.getId() == id) {
                        result.add(genre);
                        break;
                    }
                }
            } catch (NumberFormatException e) {
            }
        }
        return result;
    }
}