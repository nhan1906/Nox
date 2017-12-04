package zenlife.nox.nox.home;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import zenlife.nox.nox.R;
import zenlife.nox.nox.detail.DetailActivity;


/**
 * Created by Nhan on 11/2/2017.
 */

public class SliderImageAdapter extends PagerAdapter {

    private LayoutInflater inflater;
    private Context context;

    public SliderImageAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.card_slide_image, view, false);
        imageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailActivity.class));
            }
        });
        ImageView imgslider = (ImageView) imageLayout.findViewById(R.id.imageSlider);
        switch (position%3){
            default:
            case 0:
                imgslider.setImageDrawable(context.getResources().getDrawable(R.drawable.cloudboy));
                break;
            case 1:
                imgslider.setImageDrawable(context.getResources().getDrawable(R.drawable.cloudyboy02));
                break;
            case 2:
                imgslider.setImageDrawable(context.getResources().getDrawable(R.drawable.cloudyboy03));
                break;
        }
        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
