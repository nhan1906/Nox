package zenlife.nox.nox.detail;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import me.samthompson.bubbleactions.BubbleActions;
import me.samthompson.bubbleactions.Callback;
import zenlife.nox.nox.R;
import zenlife.nox.nox.util.ItemEpisodeRVAdapter;
import zenlife.nox.nox.util.ItemGenresRVAdapter;

public class EpisodeFragment extends Fragment {

    private boolean sort = false;

    public static EpisodeFragment newInstance() {
        EpisodeFragment fragment = new EpisodeFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_episode, container, false);

        //episode
        final RecyclerView episodeRecyclerView = (RecyclerView) view.findViewById(R.id.episodeRecyclerView);
        episodeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        episodeRecyclerView.setAdapter(new ItemEpisodeRVAdapter(getContext() , 0));


        final ImageView imgSort = (ImageView) view.findViewById(R.id.imgSort);
        FrameLayout frameSort = (FrameLayout) view.findViewById(R.id.frameSort);
        frameSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort = !sort;
                if(sort){
                    imgSort.setImageDrawable(getContext().getResources().getDrawable(R.drawable.icon_sort1));
                    episodeRecyclerView.setAdapter(new ItemEpisodeRVAdapter(getContext() , 0));
                }
                else {
                    imgSort.setImageDrawable(getContext().getResources().getDrawable(R.drawable.icon_sort2));
                    episodeRecyclerView.setAdapter(new ItemEpisodeRVAdapter(getContext() , 1));
                }
            }
        });
        return view;
    }

}
