package devandroid.micaela.moldylemons;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel extends AndroidViewModel {

    private final CoupleRepository repository;
    private final MutableLiveData<Couple> loginResult = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.repository = new CoupleRepository(application);
    }

    public LiveData<Couple> getLoginResult() {
        return this.loginResult;
    }

    public void login(String login, String password) {
        repository.getCoupleByLoginAndPassword(login, password)
                .observeForever(couple -> loginResult.postValue(couple));
    }

}