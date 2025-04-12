package devandroid.micaela.moldylemons.ui.reviewList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReviewListViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ReviewListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Review List fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}