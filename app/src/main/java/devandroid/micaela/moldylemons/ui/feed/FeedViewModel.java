package devandroid.micaela.moldylemons.ui.feed;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FeedViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FeedViewModel() {
        this.mText = new MutableLiveData<>();
        this.mText.setValue("This is Feed fragment");
    }

    public LiveData<String> getText() {
        return this.mText;
    }
}