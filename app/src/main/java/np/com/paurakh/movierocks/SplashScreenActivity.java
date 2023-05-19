package np.com.paurakh.movierocks;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;

import np.com.paurakh.movierocks.features.onboarding.OnBoardingActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Token retrieval is successful
                        String token = task.getResult();
                        Log.d("Token:", token);

                        // Send the token to the server
                        sendTokenToServer(token);
                    } else {
                        // Token retrieval failed
                        String errorMessage = task.getException() != null ? task.getException().getMessage() : "Unknown error occurred.";
                        Log.e("Token Fail", errorMessage);
                    }
                });

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreenActivity.this, OnBoardingActivity.class));
            finish();
        }, 2000);

    }

    private void sendTokenToServer(String token) {
        // Perform an HTTP request to your server with the token
        // You can use libraries like OkHttp
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("token", token)
                .build();
        Request request = new Request.Builder()
//                .url("http://localhost/register")
                //.url("http://192.168.1.68")
                .url("http://100.64.193.18")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("Server Request", "Failed to send token to server: " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (response.isSuccessful()) {
                    Log.d("Server Request", "Token sent successfully");
                    Log.d("Token", "onResponse:" + token);
                } else {
                    Log.e("Server Request", "Failed to send token to server: " + response.code());
                }
                response.close();
            }
        });

    }


}
