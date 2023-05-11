package np.com.paurakh.movierocks.features.loginandsignupscreen.fragments.halper;

import android.util.Patterns;

public class LoginValidationPresenter implements LoginAndSignupContract.Presenter {

    private final LoginAndSignupContract.View view;

    public LoginValidationPresenter(LoginAndSignupContract.View view) {
        if (view == null) {
            throw new IllegalArgumentException("View cannot be null");
        }
        this.view = view;
    }

    @Override
    public void validateForm(String userName, String email, String phoneNumber, String password, String confirmPassword) {
        // Validate username
        if (userName.isEmpty()) {
            view.showUserNameError("Username cannot be empty or already taken");
            return;
        }

        // Validate email address
        if (email.isEmpty()) {
            view.showEmailError("Email cannot be empty");
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.showEmailError("Invalid email address");
            return;
        }

        // Validate phone number
        if (phoneNumber.isEmpty()) {
            view.showPhoneNumberError("Phone number cannot be empty");
            return;
        } else if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
            view.showPhoneNumberError("Invalid phone number");
            return;
        }

        // Validate password
        if (password.isEmpty()) {
            view.showPasswordError("Password cannot be empty");
            return;
        } else if (password.length() < 6) {
            view.showPasswordError("Password must be at least 6 characters long");
            return;
        }

        // Validate confirm password
        if (!confirmPassword.equals(password)) {
            view.showConfirmPasswordError("Passwords do not match");
            return;
        }

        // All validation checks passed, show success message
        view.showAccountCreatedMessage("Your Account is Created");
    }
}
