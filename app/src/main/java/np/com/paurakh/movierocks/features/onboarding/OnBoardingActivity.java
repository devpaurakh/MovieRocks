package np.com.paurakh.movierocks.features.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import np.com.paurakh.movierocks.R;
import np.com.paurakh.movierocks.features.loginandsignupscreen.LoginAndSignupActivity;
import np.com.paurakh.movierocks.features.onboarding.adapter.OnBoardPageAdapter;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private CardView next;

    public TextView[] dots;
    private LinearLayout dotsLayout;

    private SaveState saveState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        viewPager = findViewById(R.id.vpOnboardPhotos);
        OnBoardPageAdapter adapter = new OnBoardPageAdapter(this);


        next = findViewById(R.id.cvNextCard);

        dotsLayout = findViewById(R.id.llDotsLayout);


        saveState = new SaveState(this, "done");

        if (saveState.getState() == 1) {
            Intent intent = new Intent(OnBoardingActivity.this, LoginAndSignupActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        viewPager.setAdapter(adapter);
        next.setOnClickListener(v -> viewPager.setCurrentItem(1, true));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dotsFunction(position);
                next.setOnClickListener(v -> {
                    if (position < 3) {
                        viewPager.setCurrentItem(position + 1, true);
                    } else {
                        saveState.setSate(1);
                        startActivity(new Intent(OnBoardingActivity.this, LoginAndSignupActivity.class));
                        finish();
                    }
                });
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        dotsFunction(0);
    }
    private void dotsFunction(int pos){
        dots = new TextView[4];
        dotsLayout.removeAllViews();


        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("-", Html.FROM_HTML_MODE_LEGACY));
            dots[i].setTextColor(getColor(R.color.grey));  //Unselected Color
            dots[i].setTextSize(40); // This is for unselected size
            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0) {
            dots[pos].setTextColor(getColor(R.color.lightBlue)); //selected Color
            dots[pos].setTextSize(40); // This is for selected size
        }
    }

}