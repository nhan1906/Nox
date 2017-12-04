package zenlife.nox.nox.util;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import zenlife.nox.nox.R;
import zenlife.nox.nox.readcomic.ReadComicActivity;

/**
 * Created by Nhan on 11/14/2017.
 */

public class ItemEpisodeRVAdapter extends RecyclerView.Adapter<ItemEpisodeRVAdapter.ViewHolder> {

    private Context context;
    private int widthItem ,heighItem;
    private AssetManager assetManager;
    private int episode = 1;
    private int episodeDescent = 10;
    private int i;
    private static final String[] EPISODES_TITLES = {
      "Animal Spirits", "Myo-dan", "Getting Jung-min Back", "The Revenge of the fish", "Imamature Childe",
            "Are you ready?", "Winter Night", "Only if You Grand", "Red Bracelet", "All We Can Do is Waite"
    };
    private static final String[] EPIOSDES_DAY = {
            "July 30,2017", "July 30,2017","July 30,2017", "July 30,2017","July 30,2017",
            "August 05,2017", "August 05,2017","August 05,2017", "August 13,2017", "August 13,2017"
    };
    public ItemEpisodeRVAdapter(Context context , int i){
        this.context = context;
        assetManager = context.getAssets();
        this.i = i;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_item_episode, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(i == 0){
            try {
                holder.imgEpisode.setImageDrawable(Drawable.createFromStream(assetManager.open("images/detail/" + ListImage.EPIOSEDES[position]), null));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(episode == 1){
                holder.frameMark.setVisibility(View.VISIBLE);
            }

            holder.txtEpisodeTitle.setText(EPISODES_TITLES[position]);
            holder.txtDayEpisode.setText(EPIOSDES_DAY[position]);
            holder.txtEpisode.setText("Episode " + episode++);
        }
        else {
            try {
                holder.imgEpisode.setImageDrawable(Drawable.createFromStream(assetManager.open("images/detail/" + ListImage.EPIOSEDES[10 - position -1]), null));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(episodeDescent == 1){
                holder.frameMark.setVisibility(View.VISIBLE);
            }

            holder.txtEpisodeTitle.setText(EPISODES_TITLES[10 - position - 1]);
            holder.txtDayEpisode.setText(EPIOSDES_DAY[10 - position - 1]);
            holder.txtEpisode.setText("Episode " + episodeDescent--);
        }


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imgEpisode;
        public TextView txtEpisode;
        public TextView txtEpisodeTitle;
        public TextView txtDayEpisode;
        public FrameLayout frameMark;
        public ViewHolder(View itemView) {
            super(itemView);

            imgEpisode = (ImageView) itemView.findViewById(R.id.imgEpisode);
            txtEpisode = (TextView) itemView.findViewById(R.id.txtEpisode);
            txtEpisodeTitle = (TextView) itemView.findViewById(R.id.txtEpisodeTitle);
            txtDayEpisode = (TextView) itemView.findViewById(R.id.txtDayEpisode);
            frameMark = (FrameLayout) itemView.findViewById(R.id.frameMark);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, ReadComicActivity.class));
        }
    }
}