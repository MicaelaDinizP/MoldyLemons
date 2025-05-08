package devandroid.micaela.moldylemons.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import devandroid.micaela.moldylemons.data.local.AppDatabase;
import devandroid.micaela.moldylemons.data.local.CoupleDao;
import devandroid.micaela.moldylemons.data.local.ReviewDao;
import devandroid.micaela.moldylemons.data.model.Couple;
import devandroid.micaela.moldylemons.data.model.Review;

public class ReviewRepository {

    private final ReviewDao reviewDao;

    public ReviewRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        this.reviewDao = db.reviewDao();
    }

    public ReviewDao getReviewDao() {
        return this.reviewDao;
    }

    public void insert(Review review) {
        new Thread(() -> reviewDao.insert(review)).start();
    }

    public void update(Review review) {
        new Thread(() -> reviewDao.update(review)).start();
    }

    public void delete(Review review) {
        new Thread(() -> reviewDao.delete(review)).start();
    }
    public LiveData<Review> getReviewById(int id) {
        return reviewDao.getReviewById(id);
    }
    public LiveData<List<Review>> getAllReviews(){
        return reviewDao.getAllReviews();
    }
    public LiveData<List<Review>> getReviewsByRating(int rating){
        return reviewDao.getReviewsByRating(rating);
    }
    public LiveData<List<Review>> getReviewsByAuthorAndCouple(String author, int coupleId){
        return reviewDao.getReviewsByAuthorAndCouple(author, coupleId);
    }
    public LiveData<List<Review>> getReviewsByCouple(int coupleId){
        return reviewDao.getReviewsByCouple(coupleId);
    }
}