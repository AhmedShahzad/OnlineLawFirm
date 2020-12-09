package myapp.admin.example.com.onlinelawfirm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewPdf extends AppCompatActivity {
    ListView listView;

    //database reference to get uploads data
    DatabaseReference mDatabaseReference;

    //list to store uploads data
    List<Upload> uploadList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);
        uploadList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);


        //adding a clicklistener on listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the upload
                // try {
                    Upload upload = uploadList.get(i);
                    //Opening the upload file in browser using the upload url
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri file = Uri.parse(upload.getUrl());
                    intent.setData(file);
                    Log.d("url", upload.getUrl());
                    startActivity(intent);
                    intent.putExtra("pdf", uploadList.get(i).getUrl());
                Toast.makeText(ViewPdf.this, "path is"+uploadList.get(i).getUrl(), Toast.LENGTH_LONG).show();



            }
        });
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);


        //retrieving upload data from firebase database
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    uploadList.add(upload);

                }

                String[] uploads = new String[uploadList.size()];

                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = uploadList.get(i).getName();
                   // uploads[i] = uploadList.get(i).getUrl();
                    Toast.makeText(ViewPdf.this, ""+uploadList.get(i).getUrl(), Toast.LENGTH_SHORT).show();
                }


                //displaying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                listView.setAdapter(adapter);
            }


            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
