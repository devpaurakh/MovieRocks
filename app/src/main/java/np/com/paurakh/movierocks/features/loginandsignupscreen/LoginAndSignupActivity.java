package np.com.paurakh.movierocks.features.loginandsignupscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import np.com.paurakh.movierocks.R;
import np.com.paurakh.movierocks.features.loginandsignupscreen.adapter.ViewPagerLoginOrSignUpAdapter;

public class LoginAndSignupActivity extends AppCompatActivity {
    TabLayout loginOrSignUpTab;
    ViewPager loginOrSignUpViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_signup);

        loginOrSignUpTab = findViewById(R.id.tlTabLayout_for_login_signup);
        loginOrSignUpViewPager = findViewById(R.id.vpLoginViewPager);

        ViewPagerLoginOrSignUpAdapter loginOrSignUpAdapter = new ViewPagerLoginOrSignUpAdapter(getSupportFragmentManager());
        loginOrSignUpViewPager.setAdapter(loginOrSignUpAdapter);

        //for sync
        loginOrSignUpTab.setupWithViewPager(loginOrSignUpViewPager);
    }
}