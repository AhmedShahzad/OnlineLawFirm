package myapp.admin.example.com.onlinelawfirm.ui.Payments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PaymentsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PaymentsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}