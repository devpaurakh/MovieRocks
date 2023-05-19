package np.com.paurakh.movierocks.features.dashboard.dashboardpages;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

import np.com.paurakh.movierocks.R;
import np.com.paurakh.movierocks.features.dashboard.DashboardActivity;
import np.com.paurakh.movierocks.features.loginandsignupscreen.LoginAndSignupActivity;

public class ProfileFragment extends Fragment {

    Button logoutBtn;

    SharedPreferences sharedPreferences;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logoutBtn = view.findViewById(R.id.blogOut);
        logoutBtn.setOnClickListener(v -> {
            sharedPreferences = requireContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
            // Remove the session token
            sharedPreferences.edit().remove("session_token")
                    .remove("your_session_token_here").apply();            // Redirect the user to the login screen
            Intent intent = new Intent(getContext(), LoginAndSignupActivity.class);
            startActivity(intent);

            // Close the current activity
            requireActivity().finish();
        });
    }
}
