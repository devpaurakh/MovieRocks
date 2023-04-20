package np.com.paurakh.movierocks.features.onboarding.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import np.com.paurakh.movierocks.R;

public class OnBoardPageAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;

    private final int[] titles = {
            R.string.titleOne,
            R.string.titleTwo,
            R.string.titleThree,
            R.string.titleFour
    };

    private final int[] descs ={
            R.string.descOne,
            R.string.descTwo,
            R.string.descThree,
            R.string.descFour
    };

    private final int[] images = {
            R.mipmap.ic_movieposterone,
            R.mipmap.ic_moviepostertwo,
            R.mipmap.ic_movieposterthree,
            R.mipmap.ic_movieposterfour
    };

    public OnBoardPageAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = layoutInflater.inflate(R.layout.on_boarding_layout,container,false);
        ImageView imageView = v.findViewById(R.id.ivFirstPhoto);
        TextView title, description;
        title = v.findViewById(R.id.tvTitle);
        description = v.findViewById(R.id.tvDescription);
        imageView.setImageResource(images[position]);
        title.setText(titles[position]);
        description.setText(descs[position]);
        container.addView(v);
        return  v;
    }
}
