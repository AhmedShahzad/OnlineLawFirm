package myapp.admin.example.com.onlinelawfirm;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;

public class NavAdmin extends AppCompatActivity {

ImageView imgmenu;
NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_admin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imgmenu = findViewById(R.id.menubtn);
        navigationView = findViewById(R.id.nav_view);

        imgmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDraw();
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_law) {
                    Intent intent = new Intent(NavAdmin.this , ViewLaw.class );
                    startActivity(intent);
                } else if (id == R.id.nav_lawyer) {
                    Intent intent = new Intent(NavAdmin.this , ViewLawyer.class );
                    startActivity(intent);

                } else if (id == R.id.nav_client) {


                } else if (id == R.id.nav_payment) {


                } else if (id == R.id.complains) {
                    Intent intent = new Intent(NavAdmin.this , ViewComplains.class );
                    startActivity(intent);
                }
                else if (id == R.id.feedback) {

                }
                else if (id == R.id.profile) {
                    Intent intent = new Intent(NavAdmin.this , Profile.class );
                    startActivity(intent);
                }
                else if (id == R.id.nav_share) {
                    Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Insert Subject here");
                    String app_url = " https://play.google.com/store/apps/details?id=my.example.javatpoint";
                    shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,app_url);
                    startActivity(Intent.createChooser(shareIntent, "Share via"));
                }else if (id == R.id.nav_logout) {
                    Intent intent = new Intent(NavAdmin.this , LoginActivity.class );
                    startActivity(intent);
                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;

            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        OpenDraw();


    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_nav_admin_drawer, menu);
        return true;
    }
    public  boolean OpenDraw()
    {  DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.openDrawer(GravityCompat.START);
        return true;
    }
}
