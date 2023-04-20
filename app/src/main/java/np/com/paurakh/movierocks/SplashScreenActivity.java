package np.com.paurakh.movierocks;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import np.com.paurakh.movierocks.features.dashboard.DashboardActivity;
import np.com.paurakh.movierocks.features.onboarding.OnBoardingActivity;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreenActivity.this, OnBoardingActivity.class));
            finish();
        },2000);

    }

}
