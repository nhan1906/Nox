package zenlife.nox.nox.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import zenlife.nox.nox.R;
import zenlife.nox.nox.listcomic.ListComicActivity;

/**
 * Created by Nhan on 11/13/2017.
 */

public class ItemGenresRVAdapter extends RecyclerView.Adapter<ItemGenresRVAdapter.ViewHolder> {

    private Context context;
    private int widthItem ,heighItem;

    private static final String[] GENRES = {
            "Action", "Comedy", "Drama" , "Fantasy" ,"Gaming", "Horror", "Mystery", "Romance", "Sience Fiction", "Slice of Life"
    };

    public ItemGenresRVAdapter(Context context){
        this.context = context;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthScreen = displayMetrics.widthPixels;

        widthItem = widthScreen - 56;
        heighItem = widthItem  / 3;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_item_library_genres, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        lp.height = heighItem;
        lp.width = widthItem;
        lp.setMargins(28, 10, 28, 10);
        holder.itemView.setLayoutParams(lp);


        ImageView imgGenreBlur = (ImageView) holder.itemView.findViewById(R.id.imgGenreBlur);
        Bitmap originalBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.xmen);
        Bitmap blurredBitmap = BlurBuilder.blur(context, originalBitmap );
        imgGenreBlur.setImageDrawable(new BitmapDrawable(context.getResources(), blurredBitmap));


        TextView txtGenre = (TextView) holder.itemView.findViewById(R.id.txtGenre);
        txtGenre.setText(GENRES[position]);

    }

    @Override
    public int getItemCount() {
        return GENRES.length;
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