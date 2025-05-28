package devandroid.micaela.moldylemons.data.local;

import androidx.room.TypeConverter;
import devandroid.micaela.moldylemons.data.model.enums.MediaType;

public class MediaTypeConverter {

    @TypeConverter
    public static MediaType fromString(String value) {
        if (value == null || value.isEmpty()) {
            return MediaType.ALL;
        }

        for (MediaType type : MediaType.values()) {
            if (type.getMediaTypeString().equals(value)) {
                return type;
            }
        }

        return MediaType.ALL;
    }

    @TypeConverter
    public static String toString(MediaType type) {
        return type != null ? type.getMediaTypeString() : MediaType.ALL.getMediaTypeString();
    }
}
