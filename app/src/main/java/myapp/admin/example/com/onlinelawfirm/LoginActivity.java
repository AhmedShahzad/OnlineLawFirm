package myapp.admin.example.com.onlinelawfirm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText edtemail;
    EditText edtpass;
    Button login;
    private static String URL_REGIST = "http://studentfyp.online/poonchuniversity/onlinelawfirm/Loginuser.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
 edtemail = findViewById(R.id.edtemail);
 edtpass = findViewById(R.id.edtpass);
 login = findViewById(R.id.btnsignin);
        if(!isConnected(LoginActivity.this)) buildDialog(LoginActivity.this).show();
login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Register();
    }
});
edtemail.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        validate(edtemail,edtpass);
    }
});

    }
    private void Register()
    {

        final String edtemail = this.edtemail.getText().toString().trim();
        final String edtpass = this.edtpass.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    String user_name = jsonObject.getString("user_name");
                    String user_password = jsonObject.getString("user_password");
                    String user_email = jsonObject.getString("user_email");
                    String user_id = jsonObject.getString("user_id");
                    String user_contact = jsonObject.getString("user_contact");
                    String user_type = jsonObject.getString("user_type");
                    Log.d("id", user_id);
                    if(success.equals("1"))
                    {
                        SessionManager sessionManager = new SessionManager(LoginActivity.this);
                        sessionManager.setLogin(true);
                        sessionManager.setKeyContact(user_contact);
                        sessionManager.setKeyPass(user_password);
                        sessionManager.setKeyEmail(user_email);
                        sessionManager.setKeyUsertype(user_type);
                        sessionManager.setKeyUsername(user_name);
                        sessionManager.setKeyId(user_id);
                        Intent intt = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intt);
                        Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Login Error !"+ e.toString(), Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



                Toast.makeText(LoginActivity.this, "Volley Error !"+ error.toString(), Toast.LENGTH_SHORT).show();

            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("user_email" , edtemail);

                params.put("user_password" , edtpass);

                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public  boolean isConnected(Context context)
    {

        ConnectivityManager cm =(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if(ni != null && ni.isConnectedOrConnecting())
        {

            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if((mobile!= null && ni.isConnectedOrConnecting()) || ( wifi!= null && ni.isConnectedOrConnecting()))
                return  true;
            else return false;

        }
        else
            return false;
    }
    public AlertDialog.Builder buildDialog (Context c){

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("connect wifi or mobile data");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        return builder;

    }

    @Override
    public void onBackPressed() {
        {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            LoginActivity.super.onBackPressed();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }

    }
    public static boolean validate(EditText edtemail,  EditText edtpass)
    {
        String name1 = edtemail.getText().toString().trim();

        String password1 = edtpass.getText().toString().trim();


        if (name1.isEmpty()) {

            edtemail.setError("Please Enter email ");

            edtemail.requestFocus();
            return false;
        }
        if (name1.length() < 1) {
            edtemail.setError("Please Enter registered email");

            edtemail.requestFocus();
            return false;
        }
        if (password1.isEmpty()) {
            edtpass.setError("Please Enter Password");

            edtpass.requestFocus();
            return false;
        }

        return true;
    }
}
