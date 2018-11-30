package obd.edu.pdx.mohak.ece558.object_detection;

import android.content.Intent;
import android.os.Handler;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        int secondsDelayed = 4;
        /**
         * Handler to handle the splash screen,
         *Start another activity after 3 seconds,
         *Finish the splash activity,
         */
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashActivity.this, loginActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);

    }
}
