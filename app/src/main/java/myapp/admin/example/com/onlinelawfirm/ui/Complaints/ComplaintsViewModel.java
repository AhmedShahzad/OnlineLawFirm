package myapp.admin.example.com.onlinelawfirm.ui.Complaints;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ComplaintsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ComplaintsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}