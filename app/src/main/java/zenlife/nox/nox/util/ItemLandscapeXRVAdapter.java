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

import java.io.IOException;

import zenlife.nox.nox.R;
import zenlife.nox.nox.listcomic.ListComicActivity;

/**
 * Created by Nhan on 11/13/2017.
 */

public class ItemLandscapeXRVAdapter extends RecyclerView.Adapter<ItemLandscapeXRVAdapter.ViewHolder> {

    private Context context;
    private int widthItem ,heighItem;
    private AssetManager assetManager;
    public ItemLandscapeXRVAdapter(Context context){
        this.context = context;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthScreen = displayMetrics.widthPixels;
        widthItem = (widthScreen - 68) / 2;
        heighItem = widthItem  * 3 / 5;
        assetManager = context.getAssets();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_landscape_x, parent, false);
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


        ImageView imgItemComic = (ImageView) holder.itemView.findViewById(R.id.landscapeItem);
        try {
            imgItemComic.setImageDrawable(Drawable.createFromStream(assetManager.open("images/listsmall/" + ListImage.SMALL[position%3]), null));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            context.startActivity(new Intent(context, ListComicActivity.class));
        }
    }
}