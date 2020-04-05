package com.nexware.coronaTrack.ui.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.nexware.coronaTrack.R;
import com.nexware.coronaTrack.ui.ui.adminActivity.AdminMainActivity;
import com.nexware.coronaTrack.ui.ui.adminActivity.MainActivity;
import com.nexware.coronaTrack.ui.ui.offials.OfficalsMainActivity;
import com.nexware.coronaTrack.ui.ui.register.RegisterMainActivity;


public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_TIME = 1000;

    FirebaseAuth mAuth;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        mAuth = FirebaseAuth.getInstance();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if (mAuth.getCurrentUser() != null) {
            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, new OnSuccessListener<InstanceIdResult>() {
                @Override
                public void onSuccess(InstanceIdResult instanceIdResult) {
                    String newToken = instanceIdResult.getToken();
                    Log.e("newToken", newToken);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("AccessToken", newToken);
                    editor.apply();
                }
            });
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mAuth.getCurrentUser() != null) {
                    String userState = sharedpreferences.getString(getString(R.string.userType), "");
                    Log.e("User", userState);
                    if (userState.equals("user")) {
                        Intent intent = new Intent();
                        intent.setClass(SplashActivity.this,
                                MainActivity.class);
                        SplashActivity.this.startActivity(intent);
                        SplashActivity.this.finish();
                    } else if (userState.equals("officals")) {
                        Intent intent = new Intent();
                        intent.setClass(SplashActivity.this,
                                OfficalsMainActivity.class);
                        SplashActivity.this.startActivity(intent);
                        SplashActivity.this.finish();
                    } else if (userState.equals("admin")) {
                        Intent intent = new Intent();
                        intent.setClass(SplashActivity.this,
                                AdminMainActivity.class);
                        SplashActivity.this.startActivity(intent);
                        SplashActivity.this.finish();
                    }

                } else {
                    boolean toutorial = sharedpreferences.getBoolean(getString(R.string.toutorial), false);
                    if (!toutorial) {
                        Intent intent = new Intent();
                        intent.setClass(SplashActivity.this,
                                FirstWalkthroughActivity.class);

                        SplashActivity.this.startActivity(intent);
                        SplashActivity.this.finish();
                    } else {
                        Intent intent = new Intent();
                        intent.setClass(SplashActivity.this,
                                RegisterMainActivity.class);

                        SplashActivity.this.startActivity(intent);
                        SplashActivity.this.finish();
                    }
                }

            }
        }, SPLASH_DISPLAY_TIME);
    }

}

