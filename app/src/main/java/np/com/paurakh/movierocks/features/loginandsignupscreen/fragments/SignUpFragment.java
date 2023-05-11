package np.com.paurakh.movierocks.features.loginandsignupscreen.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import np.com.paurakh.movierocks.databinding.FragmentSignUpBinding;
import np.com.paurakh.movierocks.features.helper.roomdatabase.UserDao;
import np.com.paurakh.movierocks.features.helper.roomdatabase.UserDatabase;
import np.com.paurakh.movierocks.features.helper.roomdatabase.UserTable;
import np.com.paurakh.movierocks.features.loginandsignupscreen.fragments.halper.LoginAndSignupContract;

public class SignUpFragment extends Fragment implements LoginAndSignupContract.View {

    public SignUpFragment() {
        // Required empty public constructor
    }
    FragmentSignUpBinding binding;
    UserDatabase userDatabase;
    UserDao userDao;
    public static boolean isAllowed = false;

    private LoginAndSignupContract.Presenter presenter;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.etSignUpUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String userName = editable.toString();
                if (userDao.isUserNameTaken(userName)) {
                    isAllowed = false;
                    Toast.makeText(getContext(), "Username already taken", Toast.LENGTH_SHORT).show();
                } else {
                    isAllowed = true;
                }
            }

        });
        binding.btnSignUp.setOnClickListener(v -> {
            String userName = binding.etSignUpUserName.getText().toString().trim();
            String email = binding.etSignUpUserEmail.getText().toString().trim();
            String phoneNumber = binding.etSignUpUserPhoneNo.getText().toString().trim();
            String password = binding.etSignUpUserPassword.getText().toString().trim();
            String confirmPassword = binding.etSignUpUserConfirmPassword.getText().toString().trim();
            if (isAllowed) {

                    UserTable userTable = new UserTable(0, userName, email, phoneNumber, password);
                    userDao.insertUser(userTable);

            } else {
                Toast.makeText(getContext(), "Already Taken", Toast.LENGTH_SHORT).show();
                presenter.validateForm(userName, email, phoneNumber, password, confirmPassword);
            }
        });



    }

    @Override
    public void showUserNameError(String message) {

    }

    @Override
    public void showEmailError(String message) {

    }

    @Override
    public void showPhoneNumberError(String message) {

    }

    @Override
    public void showPasswordError(String message) {

    }

    @Override
    public void showConfirmPasswordError(String message) {

    }

    @Override
    public void showAccountCreatedMessage(String message) {

    }
}
