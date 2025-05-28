package devandroid.micaela.moldylemons.data.local;

import androidx.room.TypeConverter;
import devandroid.micaela.moldylemons.data.model.enums.Demographic;

public class DemographicConverter {

    @TypeConverter
    public static Demographic fromId(Integer id) {
        if (id == null || id < 0) return null;

        for (Demographic d : Demographic.values()) {
            if (d.getId() == id) {
                return d;
            }
        }

        return null;
    }

    @TypeConverter
    public static Integer toId(Demographic demographic) {
        return demographic != null ? demographic.getId() : null;
    }
}
