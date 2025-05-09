package devandroid.micaela.moldylemons.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import devandroid.micaela.moldylemons.data.local.DemographicConverter;
import devandroid.micaela.moldylemons.data.local.GenreListConverter;
import devandroid.micaela.moldylemons.data.local.MediaTypeConverter;
import devandroid.micaela.moldylemons.data.model.enums.Demographic;
import devandroid.micaela.moldylemons.data.model.enums.Genre;
import devandroid.micaela.moldylemons.data.model.enums.MediaType;

import java.util.List;

@Entity(
        tableName = "media",
        foreignKeys = @ForeignKey(
                entity = Couple.class,
                parentColumns = "id",
                childColumns = "couple_id",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        )
)
public class MediaEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "genres")
    public List<Genre> genres;
    @ColumnInfo(name = "media_type")
    public MediaType mediaType;
    @ColumnInfo(name = "couple_id")
    public int coupleId;

    //--------------------------- MOVIE ----------------------------------
    @ColumnInfo(name = "duration")
    public int duration;

    //----------------------------SERIE----------------------------------
    @ColumnInfo(name = "seasons")
    public int seasons;
    @ColumnInfo(name = "total_episodes")
    public int totalEpisodes;

    //----------------------------ANIME----------------------------------
    @ColumnInfo(name = "studio")
    public String studio;

    @ColumnInfo(name = "demographic")
    public Demographic demographic;

    public MediaEntity() {}
}