package devandroid.micaela.moldylemons.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import devandroid.micaela.moldylemons.data.model.Couple;

@Dao
public interface CoupleDao {
    @Insert
    void insert(Couple couple);

    @Update
    void update(Couple couple);

    @Delete
    void delete(Couple couple);

    @Query("SELECT * FROM couples WHERE id = :id LIMIT 1")
    LiveData<Couple> getCoupleById(int id);

    @Query("SELECT * FROM couples")
    List<Couple> getAllCouples();


    @Query("SELECT * FROM couples WHERE login = :login LIMIT 1")
    LiveData<Couple> getCoupleByLogin(String login);

    @Query("UPDATE couples SET password = :password WHERE id = :id")
    void updatePassword(int id, String password);

    @Query("SELECT * FROM couples WHERE login = :login AND password = :password LIMIT 1")
    LiveData<Couple> getCoupleByLoginAndPassword(String login, String password);
}