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

import java.io.IOException;
import java.util.Random;

import zenlife.nox.nox.R;
import zenlife.nox.nox.detail.DetailActivity;

/**
 * Created by Nhan on 11/16/2017.
 */

public class ItemReadComicRVAdapter extends RecyclerView.Adapter<ItemReadComicRVAdapter.ViewHolder> {

    private Context context;
    private AssetManager assetManager;

    public ItemReadComicRVAdapter(Context context){
        this.context = context;

        assetManager = context.getAssets();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_item_readcomic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            holder.imgItemReadComic.setImageDrawable(Drawable.createFromStream(assetManager.open("images/readcomic/" + ListImage.READ[position]), null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return ListImage.READ.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgItemReadComic;

        public ViewHolder(View itemView) {
            super(itemView);
            imgItemReadComic = (ImageView) itemView.findViewById(R.id.imgItemReadComic);
        }
    }
}