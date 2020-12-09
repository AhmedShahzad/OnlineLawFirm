package myapp.admin.example.com.onlinelawfirm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Profile extends AppCompatActivity {
EditText name , email , contact , password;
Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.edtname);
        email = findViewById(R.id.edtemail);
        contact = findViewById(R.id.edtcontact);
        password = findViewById(R.id.edtpass);
        btnUpdate = findViewById(R.id.btnupdate);
        SessionManager sessionManager = new SessionManager(Profile.this);
        sessionManager.setLogin(true);
        name.setText(sessionManager.getKeyUsername());
        email.setText(sessionManager.getKeyEmail());
        contact.setText(sessionManager.getKeyContact());
        password.setText(sessionManager.getKeyPass());

       // sessionManager.setKeyContact(user_contact);
       // sessionManager.setKeyPass(user_password);
       // sessionManager.setKeyEmail(user_email);
      //  sessionManager.setKeyUsertype(user_type);
      //  sessionManager.setKeyUsername(user_name);

    }
}
