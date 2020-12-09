package myapp.admin.example.com.onlinelawfirm.ui.Lawyers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LawyersViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LawyersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}