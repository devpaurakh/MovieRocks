package np.com.paurakh.movierocks;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

import np.com.paurakh.movierocks.features.onboarding.OnBoardingActivity;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().getToken()
               .addOnCompleteListener(task -> {
                   if(!task.isSuccessful()){
                       Log.e("Token Fail", Objects.requireNonNull(task.getException()).getLocalizedMessage());
                   }
                   String token = task.getResult();
                   Log.d("Token:",token);
               });
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreenActivity.this, OnBoardingActivity.class));
            finish();
        },2000);

    }



}
