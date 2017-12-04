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
 * Created by Nhan on 11/14/2017.
 */

public class ItemListcomicRVAdapter extends RecyclerView.Adapter<ItemListcomicRVAdapter.ViewHolder> {

    private Context context;
    private int widthItem ,heighItem;
    private Random randomBackgroundColor;
    private int[] backgroundsColor;
    private AssetManager assetManager;
    public ItemListcomicRVAdapter(Context context){
        this.context = context;

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
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_item_listcomic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        lp.height = heighItem;
        lp.width = widthItem;
        lp.setMargins(7,7,7,7);
        holder.itemView.setLayoutParams(lp);

        ImageView imgItemComic = (ImageView) holder.itemView.findViewById(R.id.imgListComicItem);
        try {
            imgItemComic.setImageDrawable(Drawable.createFromStream(assetManager.open("images/popular/" + ListImage.IMAGES[position]), null));
        } catch (IOException e) {
            e.printStackTrace();
        }

        TextView nameListComic = (TextView) holder.itemView.findViewById(R.id.nameListComic);
        nameListComic.setText(StringApp.COMIC_NAMES[position + 22]);

        LinearLayout backgroundListComicItem = (LinearLayout) holder.itemView.findViewById(R.id.backgroundListComicItem);
        backgroundListComicItem.setBackgroundColor(backgroundsColor[randomBackgroundColor.nextInt(backgroundsColor.length)]);
    }

    @Override
    public int getItemCount() {
        return 20;
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