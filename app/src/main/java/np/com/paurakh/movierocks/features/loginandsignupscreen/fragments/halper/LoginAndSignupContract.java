package np.com.paurakh.movierocks.features.loginandsignupscreen.fragments.halper;

public interface LoginAndSignupContract {
    interface View{
        void showUserNameError(String message);
        void showEmailError(String message);
        void showPhoneNumberError(String message);
        void showPasswordError(String message);
        void showConfirmPasswordError(String message);
        void showAccountCreatedMessage(String message);
    }

    interface Presenter{
        void validateForm(String userName, String email, String phoneNumber, String password, String confirmPassword);

    }

}
