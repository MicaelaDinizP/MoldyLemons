package devandroid.micaela.moldylemons.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import devandroid.micaela.moldylemons.data.model.Review;

@Dao
public interface ReviewDao {
    @Insert
    void insert(Review review);
    @Update
    void update(Review review);
    @Delete
    void delete(Review review);

    @Query("SELECT * FROM review WHERE id = :id LIMIT 1")
    LiveData<Review> getReviewById(int id);

    @Query("SELECT * FROM review")
    LiveData<List<Review>> getAllReviews();

    @Query("SELECT * FROM review WHERE rating = :rating")
    LiveData<List<Review>> getReviewsByRating(int rating);

    @Query("SELECT * FROM review WHERE written_by = :author AND couple_id = :coupleId")
    LiveData<List<Review>> getReviewsByAuthorAndCouple(String author, int coupleId);

    @Query("SELECT * FROM review WHERE couple_id = :coupleId")
    LiveData<List<Review>> getReviewsByCouple(int coupleId);
}
