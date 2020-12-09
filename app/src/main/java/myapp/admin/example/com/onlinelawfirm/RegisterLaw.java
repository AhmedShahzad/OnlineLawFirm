package myapp.admin.example.com.onlinelawfirm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import org.w3c.dom.Document;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class RegisterLaw extends AppCompatActivity {

    private int REQUEST_CODE_DOC;
TextView tvpdf;
EditText edttitle , edtdes;
    String created_date;
    String docFilePath;
    String filepath ;
    Button btnreg;
    private static String URL_REGIST = "http://studentfyp.online/poonchuniversity/onlinelawfirm/registerlaw.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_law);
        tvpdf = findViewById(R.id.tvdoc);
        edttitle = findViewById(R.id.edttitle);
        edtdes = findViewById(R.id.edtdes);
        btnreg = findViewById(R.id.btnreg);
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
        tvpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDocument();
            }
        });
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });

    }
    private void getDocument()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/msword,application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        // Only the system receives the ACTION_OPEN_DOCUMENT, so no need to test.
        startActivityForResult(intent, REQUEST_CODE_DOC);
    }



    @Override
    protected void onActivityResult(int req, int result, Intent data)
    {
        // TODO Auto-generated method stub
        super.onActivityResult(req, result, data);
        if (result == RESULT_OK)
        {
            Uri fileuri = data.getData();
             docFilePath = getFileNameByUri(this, fileuri);
            Toast.makeText(this, "doc file is"+docFilePath, Toast.LENGTH_SHORT).show();
        }
    }

// get file path

    private String getFileNameByUri(Context context, Uri uri)
    {
         filepath = "C://Users//Admin//Documents/arooj.doc";//default fileName
        //Uri filePathUri = uri;
        File file;
        if (uri.getScheme().toString().compareTo("content") == 0)
        {
            Cursor cursor = context.getContentResolver().query(uri, new String[] { android.provider.MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.ORIENTATION }, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            cursor.moveToFirst();

            String mImagePath = cursor.getString(column_index);
            cursor.close();
            filepath = mImagePath;

        }
        else
        if (uri.getScheme().compareTo("file") == 0)
        {
            try
            {
                file = new File(new URI(uri.toString()));
                if (file.exists())
                    filepath = file.getAbsolutePath();
            //    Toast.makeText(context, ""+filepath, Toast.LENGTH_SHORT).show();

            }
            catch (URISyntaxException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else
        {
            filepath = uri.getPath();
        }
        Toast.makeText(context, "final result is"+filepath, Toast.LENGTH_SHORT).show();
        return filepath;

    }
    private void Register() {

        final String title = this.edttitle.getText().toString().trim();
     final String des = this.edtdes.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(RegisterLaw.this, ""+response, Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("1")) {
                        Toast.makeText(RegisterLaw.this, "Law added Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterLaw.this , ViewLawyer.class);
                        startActivity(intent);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterLaw.this, " " + e.toString(), Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterLaw.this, "" + error.toString(), Toast.LENGTH_SHORT).show();


            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("law_title", title);
                params.put("law_des", des);
                params.put("law_document", filepath );
                params.put("created_date" , created_date);


                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
