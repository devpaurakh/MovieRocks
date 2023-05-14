package np.com.paurakh.movierocks.features.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationBarView;

import np.com.paurakh.movierocks.R;
import np.com.paurakh.movierocks.databinding.ActivityDashboardBinding;
import np.com.paurakh.movierocks.databinding.ActivityMainBinding;
import np.com.paurakh.movierocks.features.dashboard.dashboardpages.HomeFragment;
import np.com.paurakh.movierocks.features.dashboard.dashboardpages.ProfileFragment;
import np.com.paurakh.movierocks.features.dashboard.dashboardpages.UpcomingFragment;

public class DashboardActivity extends AppCompatActivity {
    ActivityDashboardBinding binding;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        binding.bnvButtons.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.iHome:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.iUpComing:
                    replaceFragment(new UpcomingFragment());
                    break;
                case R.id.iProfile:
                    replaceFragment(new ProfileFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flDashBoardPages,fragment);
        fragmentTransaction.commit();
    }
}