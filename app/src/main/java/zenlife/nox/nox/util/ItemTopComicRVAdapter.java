package zenlife.nox.nox.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import zenlife.nox.nox.R;
import zenlife.nox.nox.listcomic.ListComicActivity;

/**
 * Created by Nhan on 11/14/2017.
 */

public class ItemTopComicRVAdapter extends RecyclerView.Adapter<ItemTopComicRVAdapter.ViewHolder> {

    private Context context;
    private int widthItem ,heighItem;

    public ItemTopComicRVAdapter(Context context){
        this.context = context;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthScreen = displayMetrics.widthPixels;
        widthItem = (widthScreen - 68) / 2;
        heighItem = widthItem / 2;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_item_topcomic, parent, false);
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

        if(position == 1)
            ((ImageView) holder.itemView.findViewById(R.id.topComicImg)).setImageResource(R.drawable.top10comics);
    }

    @Override
    public int getItemCount() {
        return 2;
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