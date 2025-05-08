package devandroid.micaela.moldylemons.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import devandroid.micaela.moldylemons.data.model.Couple;
import devandroid.micaela.moldylemons.data.model.Review;

@Database(entities = {Couple.class, Review.class}, version = 2)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract CoupleDao coupleDao();
    public abstract ReviewDao reviewDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "moldy_lemons_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}