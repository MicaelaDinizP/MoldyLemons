package devandroid.micaela.moldylemons.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import devandroid.micaela.moldylemons.data.local.AppDatabase;
import devandroid.micaela.moldylemons.data.local.CoupleDao;
import devandroid.micaela.moldylemons.data.model.Couple;

public class CoupleRepository {

    private final CoupleDao coupleDao;

    public CoupleRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        coupleDao = db.coupleDao();
    }

    public CoupleDao getCoupleDao() {
        return this.coupleDao;
    }

    public void insert(Couple couple) {
        new Thread(() -> coupleDao.insert(couple)).start();
    }

    public void update(Couple couple) {
        new Thread(() -> coupleDao.update(couple)).start();
    }

    public void delete(Couple couple) {
        new Thread(() -> coupleDao.delete(couple)).start();
    }

    public void updatePassword(int id, String password) {
        new Thread(() -> coupleDao.updatePassword(id, password)).start();
    }
    public LiveData<Couple> getCoupleById(int id) {
        return coupleDao.getCoupleById(id);
    }

    public LiveData<Couple> getCoupleByLogin(String login) {
        return coupleDao.getCoupleByLogin(login);
    }

    public LiveData<Couple> getCoupleByLoginAndPassword(String login, String password) {
        return coupleDao.getCoupleByLoginAndPassword(login, password);
    }

}