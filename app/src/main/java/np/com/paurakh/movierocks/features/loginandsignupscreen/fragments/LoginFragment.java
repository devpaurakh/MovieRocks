package np.com.paurakh.movierocks.features.loginandsignupscreen.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import np.com.paurakh.movierocks.databinding.FragmentLoginBinding;
import np.com.paurakh.movierocks.features.dashboard.DashboardActivity;
import np.com.paurakh.movierocks.features.helper.roomdatabase.UserDao;
import np.com.paurakh.movierocks.features.helper.roomdatabase.UserDatabase;


public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }

    FragmentLoginBinding binding;
    UserDatabase userDatabase;
    UserDao userDao;

    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get a reference to the SharedPreferences object
        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Check if the user is already logged in
        String sessionToken = sharedPreferences.getString("session_token", null);
        if (sessionToken != null) {
            startActivity(new Intent(requireContext(), DashboardActivity.class));
            requireActivity().finish();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        userDatabase = Room.databaseBuilder(context, UserDatabase.class, "userTable")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        userDao = userDatabase.getDao();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnLogin.setOnClickListener(v -> {
            String userName = binding.etUserUserName.getText().toString();
            String password = binding.etUserPassword.getText().toString();
            if (userDao.login(userName, password)) {
                // Save the session token to SharedPreferences
                String sessionToken = "your_session_token_here";
                sharedPreferences.edit().putString("session_token", sessionToken).apply();

                startActivity(new Intent(requireContext(), DashboardActivity.class));
                requireActivity().finish();
            } else {
                Toast.makeText(requireContext(), "Invalid UserName or password", Toast.LENGTH_LONG).show();
            }
        });
    }
}

