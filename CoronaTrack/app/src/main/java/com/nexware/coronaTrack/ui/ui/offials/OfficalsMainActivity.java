package com.nexware.coronaTrack.ui.ui.offials;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.nexware.coronaTrack.R;
import com.nexware.coronaTrack.ui.ui.adminActivity.UserActivity;
import com.nexware.coronaTrack.ui.ui.autho.OTPAuthentication;
import com.nexware.coronaTrack.ui.ui.autho.WebViewActivity;

public class OfficalsMainActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    FirebaseAuth mAuth;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officals_main);
        mAuth = FirebaseAuth.getInstance();
        pref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        BottomNavigationView navView = findViewById(R.id.nav_view_officals);
        Log.e("offical", "Enter");
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        String user = pref.getString(getString(R.string.userType), "user");

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_officals);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Menu_AboutUs:
                Intent intent = new Intent(OfficalsMainActivity.this, WebViewActivity.class);
                intent.putExtra("title", "About Us");
                intent.putExtra("url", "https://www.nexware-global.com/");
                startActivity(intent);
                break;

            case R.id.Menu_LogOutMenu:
                //Do Logout
                mAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), OTPAuthentication.class));
                break;

            case R.id.Menu_AboutUser:
                //Do Logout
                Intent intent1 = new Intent(OfficalsMainActivity.this, UserActivity.class);
                intent1.putExtra("value", "officials");
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
