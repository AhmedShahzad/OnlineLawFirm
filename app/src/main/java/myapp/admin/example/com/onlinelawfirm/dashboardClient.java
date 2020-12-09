package myapp.admin.example.com.onlinelawfirm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class dashboardClient extends AppCompatActivity {
    CardView cardViewLawyer , cardViewLaw , cardViewProf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_client);
        cardViewLawyer = findViewById(R.id.cardview);
        cardViewLaw = findViewById(R.id.cardview2);
        cardViewProf = findViewById(R.id.cardview3);
        cardViewLawyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashboardClient.this , ViewLawyer.class);
                startActivity(intent);
            }
        });
        cardViewLaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashboardClient.this , ViewLaw.class);
                startActivity(intent);
            }
        });
        cardViewProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashboardClient.this , Profile.class);
                startActivity(intent);
            }
        });


    }
}
