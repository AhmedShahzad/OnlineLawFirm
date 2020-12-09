package myapp.admin.example.com.onlinelawfirm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
CardView cardViewadmin , cardViewClient , cardViewLaw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardViewadmin = findViewById(R.id.cardview);
        cardViewClient = findViewById(R.id.cardview2);
        cardViewadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , NavAdmin.class);
                startActivity(intent);
            }
        });
cardViewLaw = findViewById(R.id.cardview3);

cardViewLaw.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this , dasboardLawyer.class);
        startActivity(intent);
    }
});
        cardViewClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , dashboardClient.class);
                startActivity(intent);
            }
        });

    }


}
