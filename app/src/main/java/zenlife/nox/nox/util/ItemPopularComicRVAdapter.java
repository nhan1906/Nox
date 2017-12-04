package zenlife.nox.nox.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

import zenlife.nox.nox.R;
import zenlife.nox.nox.detail.DetailActivity;


/**
 * Created by Nhan on 11/12/2017.
 */

public class ItemPopularComicRVAdapter extends RecyclerView.Adapter<ItemPopularComicRVAdapter.ViewHolder> {

    private Context context;
    private int widthItem ,heighItem;
    private ImageFromResource im;
    private AssetManager assetManager;
    private Random randomBackgroundColor;
    private int[] backgroundsColor;
    private String[][] titles = {{"Sleepwalk ", "Beepocherri ", "Comedy"}, {"Dragon Hunt!", "DaydreamNoise ", "Fantasy"}
                                , {"Sarota Springs", "Joanne Kwan", "Horror" }
                                , {"I hate you", "Lucazu ","Romance"} , {"Crash 'n' Burn", "Mikiko ", "Romance"} , {"HJ-Story", "nJoo ", "Romance" }
                                , {"Demons can't be pretty", "Coolchic " , "Comedy"} , {"Flourishing Flower", "RoseNose ", "Romance"}
                                , {"Quiet Brain", "Samantha Davies", "Slice of Life"} , {"Unfamiliar ", "Haley Mewsome" , "Fantasy"}};
    public ItemPopularComicRVAdapter(Context context){
        this.context = context;

        im = new ImageFromResource(context);
        assetManager = context.getAssets();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthScreen = displayMetrics.widthPixels;
        widthItem = (widthScreen - 80) / 3;
        heighItem = widthItem * 7 / 5;
        assetManager = context.getAssets();

        randomBackgroundColor = new Random();
        backgroundsColor = context.getResources().getIntArray(R.array.backgroundsItemColor);

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_item_comic_popular, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        lp.height = heighItem;
        lp.width = widthItem;
        if(position == 0 ){
            lp.setMargins(28, 0, 7, 0);
            holder.itemView.setLayoutParams(lp);
        } else if (position == 9) {
            lp.setMargins(7, 0, 28, 0);
            holder.itemView.setLayoutParams(lp);
        } else {
            lp.setMargins(7, 0, 7, 0);
            holder.itemView.setLayoutParams(lp);
        }

        ImageView imgItemComic = (ImageView) holder.itemView.findViewById(R.id.imgPopularItem);
        try {
            imgItemComic.setImageDrawable(Drawable.createFromStream(assetManager.open("images/popular/" + ListImage.IMAGES[position]), null));
        } catch (IOException e) {
            e.printStackTrace();
        }

        TextView namePopularComic = (TextView) holder.itemView.findViewById(R.id.namePopularComic);
        namePopularComic.setText(StringApp.COMIC_NAMES[position + 10]);

        LinearLayout backgroundPopularItem = (LinearLayout) holder.itemView.findViewById(R.id.backgroundPopularItem);
        backgroundPopularItem.setBackgroundColor(backgroundsColor[randomBackgroundColor.nextInt(backgroundsColor.length)]);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailActivity.class);
            context.startActivity(intent);
        }
    }
}