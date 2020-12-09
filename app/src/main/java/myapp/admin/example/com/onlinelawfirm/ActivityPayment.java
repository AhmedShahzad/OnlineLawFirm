package myapp.admin.example.com.onlinelawfirm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ActivityPayment extends AppCompatActivity {
EditText edttitle;
ImageView camera ;
TextView gallery;
Button btnpay;
private static final int SELECT_PICTURE = 1;
private static final int CAMERA_REQUEST = 1888;
int flagimg;
String created_date;
private static String URL_REGIST = "http://studentfyp.online/poonchuniversity/onlinelawfirm/payment.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        edttitle = findViewById(R.id.edttitle);
        btnpay = findViewById(R.id.btnpay);
        camera = findViewById(R.id.edtcam);
        gallery = findViewById(R.id.edtrec);
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

btnpay.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Register();
    }
});
    }
    public void fromgallery(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PICTURE);  // method 1, get from gallery
        flagimg = 0;

    }

    public void fromcamera(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST); // get from camera
        flagimg = 1;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(ActivityPayment.this, "You  picked Image", Toast.LENGTH_LONG).show();
        if (flagimg == 1) {
            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");

                camera.setImageBitmap(photo);
            }
        } // end of if

        else {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    camera.setImageBitmap(selectedImage);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(ActivityPayment.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(ActivityPayment.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        } // end of else
    }
    public void onBackPressed() {


        Intent intent = new Intent(ActivityPayment.this , MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
    private void Register() {

        final String title = this.edttitle.getText().toString().trim();
        final String receipt = Utility.bitmapToString(((BitmapDrawable) camera.getDrawable()).getBitmap());
        final SessionManager sessionManager = new SessionManager(ActivityPayment.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ActivityPayment.this, ""+response, Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("1")) {
                        Toast.makeText(ActivityPayment.this, "Paid Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ActivityPayment.this , ViewLawyer.class);
                        startActivity(intent);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ActivityPayment.this, " " + e.toString(), Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActivityPayment.this, "" + error.toString(), Toast.LENGTH_SHORT).show();


            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("pay_title", title);
                params.put("pay_receipt", receipt);
                params.put("paid_by",sessionManager.getKeyId() );
                params.put("paid_to" , getIntent().getExtras().getString("key"));
                params.put("created_date" , created_date);
                params.put("status" , "paid");


                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
