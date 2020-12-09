package myapp.admin.example.com.onlinelawfirm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.text.format.Time;
import android.text.format.Time;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    Spinner spinner;
    private static final int SELECT_PICTURE = 1;
    String userType;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    EditText edtname,edtemail,edtcnic,edtpass,edtcpass, edtcontact ;
    Button btnSignUp;
    private ProgressBar loading;
    String created_date;
    TextView txtlogin , tvdoc;
    int flagimg;
    private static String URL_REGIST = "http://studentfyp.online/poonchuniversity/onlinelawfirm/Registeruser.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        spinner = findViewById(R.id.spinner);

        edtname = findViewById(R.id.edtname);
        edtemail = findViewById(R.id.edtemail);
        edtcnic = findViewById(R.id.edtcnic);
        edtpass = findViewById(R.id.edtpass);
        edtcpass = findViewById(R.id.edtcpass);
        tvdoc = findViewById(R.id.tvdoc);
        txtlogin = findViewById(R.id.txtlogin);

        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        String year, month, day;
        year = today.year+"";
        int monthis;
        monthis = today.month + 1;
        month = monthis+"";
        day = today.monthDay+"";
         created_date = day+"-"+month+"-"+year+" ";
        Toast.makeText(this, ""+created_date, Toast.LENGTH_SHORT).show();
        String created_time = today.format("%k:%M:%S");
        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this , LoginActivity.class);
                startActivity(intent);
            }
        });
        btnSignUp = findViewById(R.id.btnsignup);
edtcontact = findViewById(R.id.edtcontact);
        loading = findViewById(R.id.loading);
        validate(edtname , edtemail, edtcnic , edtcontact, edtpass,edtcpass);
edtname.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        validate(edtname , edtemail, edtcnic , edtcontact, edtpass,edtcpass);
    }
});
edtemail.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        validate(edtname , edtemail, edtcnic , edtcontact, edtpass,edtcpass);
    }
});
edtpass.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        validate(edtname , edtemail, edtcnic , edtcontact, edtpass,edtcpass);
    }
});
edtcpass.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        validate(edtname , edtemail, edtcnic , edtcontact, edtpass,edtcpass);
    }
});
edtcnic.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        validate(edtname , edtemail, edtcnic , edtcontact, edtpass,edtcpass);
    }
});
edtcontact.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        validate(edtname , edtemail, edtcnic , edtcontact, edtpass,edtcpass);
    }
});
        arrayList = new ArrayList<>();
        arrayList.add(new String("Admin"));
        arrayList.add(new String("Client"));
        arrayList.add(new String("Lawyer"));

        adapter = new ArrayAdapter(SignUpActivity.this,android.R.layout.simple_spinner_item,arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 userType = arrayList.get(i).toString();
                Toast.makeText(SignUpActivity.this, ""+userType, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });

    }
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit registration ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SignUpActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }
    public static boolean validate(EditText edtname, EditText edtemail , EditText edtpass , EditText edtcpass ,EditText edtcnic , EditText edtcontact  )
    {
        String name1 = edtname.getText().toString().trim();
        String email1 = edtemail.getText().toString().trim();
        String password1 = edtpass.getText().toString().trim();
        String cpassword = edtcpass.getText().toString().trim();
        String cnic = edtcnic.getText().toString().trim();
        String contact = edtcontact.getText().toString().trim();



        if (name1.isEmpty()) {

            edtname.setError("Please Enter Name");
            edtname.requestFocus();
            return false;
        }
        if (name1.length() < 2) {
            edtname.setError("Please Enter Valid Name");

            edtname.requestFocus();
            return false;
        }
        if (email1.isEmpty()) {
            edtemail.setError("Please Enter email");
            edtemail.requestFocus();
            return false;
        }
        if (password1.isEmpty()) {
            edtpass.setError("Please Enter Password");
            edtpass.requestFocus();
            return false;
        }

        if (cpassword.isEmpty()) {
            edtcpass.setError("Please Enter Password again");

            edtcpass.requestFocus();
            return false;
        }
        if (cnic.isEmpty()) {
            edtcnic.setError("Please Enter cnic");

            edtcnic.requestFocus();
            return false;
        }
        if (cnic.length() < 11) {
            edtcnic.setError("Please Enter Valid CNIC");

            edtcnic.requestFocus();
            return false;
        }
        if (contact.isEmpty()) {
            edtcontact.setError("Please Enter contact number");
            edtcontact.requestFocus();
            return false;
        }



        return true;
    }

    private void Register()
    {
        loading.setVisibility(View.VISIBLE);
        btnSignUp.setVisibility(View.GONE);
        final String name = this.edtname.getText().toString().trim();
        final String email = this.edtemail.getText().toString().trim();
        final String contact = this.edtcontact.getText().toString().trim();
        final String cnic = this.edtcnic.getText().toString().trim();
        final String password = this.edtpass.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if(success.equals("1"))
                    {  Intent intent = new Intent(SignUpActivity.this , LoginActivity.class);
                        startActivity(intent);
                        SessionManager sessionManager = new SessionManager(SignUpActivity.this);

                        sessionManager.setKeyContact("user_contact");
                        sessionManager.setKeyPass("user_password");
                        sessionManager.setKeyEmail("user_email");
                        sessionManager.setKeyUsertype("user_type");
                        sessionManager.setKeyUsername("user_name");
                        sessionManager.setKeyPass("user_id");
                        Toast.makeText(SignUpActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(SignUpActivity.this, "Register Error !"+ e.toString(), Toast.LENGTH_SHORT).show();
                    loading.setVisibility(View.GONE);
                    btnSignUp.setVisibility(View.VISIBLE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignUpActivity.this, "Volley Error !"+ error.toString(), Toast.LENGTH_SHORT).show();
               loading.setVisibility(View.GONE);
                 btnSignUp.setVisibility(View.VISIBLE);

            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("user_name" , name);
                params.put("user_email" , email);
                params.put("user_password" , password);
                params.put("user_cnic" , cnic);
                params.put("user_contact" , contact);
                params.put("user_type", userType);
                params.put("created_date", created_date);


                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                //    facebook_image.setImageBitmap(selectedImage);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(SignUpActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(SignUpActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
            }

    }

}
