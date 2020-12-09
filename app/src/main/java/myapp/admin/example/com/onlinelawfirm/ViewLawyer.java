package myapp.admin.example.com.onlinelawfirm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewLawyer extends AppCompatActivity {
    ListView listview;
    int lawyer_id;
    SearchView searchView;
    FloatingActionButton fab;
    ArrayList<LawyerData> thedata;
    private static String URL_REGIST = "http://studentfyp.online/poonchuniversity/onlinelawfirm/getalllawyers.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lawyer);
        listview = findViewById(R.id.listview);
        searchView = findViewById(R.id.search);

        fetchtw();

    }
    public  void fetchtw()
    {

        thedata = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ViewLawyer.this , ""+response , Toast.LENGTH_LONG);
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("result");
                    for (int i = 0; i < array.length(); i++) {
                        String lawyer_id = array.getJSONObject(i).getString("lawyer_id");
                        String lawyer_name = array.getJSONObject(i).getString("lawyer_name");
                        String lawyer_email= array.getJSONObject(i).getString("lawyer_email");
                        String lawyer_contact = array.getJSONObject(i).getString("lawyer_contact");
                        String lawyer_type = array.getJSONObject(i).getString("lawyer_type");
                        String lawyer_designation = array.getJSONObject(i).getString("lawyer_position");
                        String lawyer_description = array.getJSONObject(i).getString("lawyer_description");
                        LawyerData lawyerData = new LawyerData(lawyer_id, lawyer_name, lawyer_email,lawyer_contact,lawyer_type ,lawyer_designation, lawyer_description);

                        thedata.add(lawyerData);
                        //  customlistview.add(new facebookData(facebook_name,facebook_phone,facebook_image, facebook_description , tid));

                    }

                    listview.setAdapter(new LawyerAdapter(ViewLawyer.this,thedata ));



                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ViewLawyer.this, "Register Error !" + e.toString(), Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewLawyer.this, "Register Error !" + error.getMessage().toString(), Toast.LENGTH_LONG).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(ViewLawyer.this);
        requestQueue.add(stringRequest);
    }


    public void onBackPressed() {

new AlertDialog.Builder(this)
        .setMessage("Are you sure you want to exit?")
        .setCancelable(false)
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ViewLawyer.super.onBackPressed();
            }
        })
        .setNegativeButton("No", null)
        .show();

    }
}
