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

    // Returns a new fragment instance depending on the tab position
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new LoginFragment();
        } else {
            return new SignUpFragment();
        }
    }

    // Returns the total number of tabs to display
    @Override
    public int getCount() {
        return 2;
    }

    // Returns the title of each tab based on the position
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Log-In";
        } else {
            return "Sign-Up";
        }
    }
}
