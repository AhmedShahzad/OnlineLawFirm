package myapp.admin.example.com.onlinelawfirm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

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

public class ViewComplains extends AppCompatActivity {
    ListView listview;
    int lawyer_id;
    SearchView searchView;
    FloatingActionButton fab;
    ArrayList<LawData> thedata;
    private static String URL_REGIST = "http://studentfyp.online/poonchuniversity/onlinelawfirm/getcomplains.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complains);
        listview = findViewById(R.id.listview);
        fetchtw();


    }
    public  void fetchtw()
    {

        thedata = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ViewComplains.this , ""+response , Toast.LENGTH_LONG);
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("result");
                    for (int i = 0; i < array.length(); i++) {

                        String complain_title = array.getJSONObject(i).getString("complain_title");
                        String complain_document= array.getJSONObject(i).getString("complain_document");

                        LawData lawData = new LawData(complain_title, "", complain_document);

                        thedata.add(lawData);
                        //  customlistview.add(new facebookData(facebook_name,facebook_phone,facebook_image, facebook_description , tid));

                    }

                    listview.setAdapter(new LawAdapter(ViewComplains.this,thedata));



                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ViewComplains.this, "Register Error !" + e.toString(), Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewComplains.this, "Register Error !" + error.getMessage().toString(), Toast.LENGTH_LONG).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(ViewComplains.this);
        requestQueue.add(stringRequest);
    }


    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ViewComplains.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }
}
