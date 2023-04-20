package np.com.paurakh.movierocks.features.loginandsignupscreen.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import np.com.paurakh.movierocks.features.loginandsignupscreen.fragments.LoginFragment;
import np.com.paurakh.movierocks.features.loginandsignupscreen.fragments.SignUpFragment;


public class ViewPagerLoginOrSignUpAdapter extends FragmentPagerAdapter {


    public ViewPagerLoginOrSignUpAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new LoginFragment();
        } else {
            return new SignUpFragment(); // for the position 1 index
        }
    }

    @Override
    public int getCount() {
        return 2;//numbers of tabs for login and signup
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "Log-In";
        }
        else {
            return "Sign-Up";
        }
    }
}
