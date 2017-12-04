package zenlife.nox.nox.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zenlife.nox.nox.R;
import zenlife.nox.nox.detail.DetailActivity;

/**
 * Created by Nhan on 11/13/2017.
 */

public class ItemNotificationRVAdapter extends RecyclerView.Adapter<ItemNotificationRVAdapter.ViewHolder> {

    private Context context;

    public ItemNotificationRVAdapter(Context context){
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_item_notifications, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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