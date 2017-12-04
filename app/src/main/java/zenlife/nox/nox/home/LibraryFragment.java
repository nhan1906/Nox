package zenlife.nox.nox.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import zenlife.nox.nox.MainActivity;
import zenlife.nox.nox.R;
import zenlife.nox.nox.util.ItemGenresRVAdapter;

public class LibraryFragment extends Fragment {

    public static LibraryFragment newInstance() {
        LibraryFragment fragment = new LibraryFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);


        //Toobar setup
        Toolbar toolbarLibrary = (Toolbar) view.findViewById(R.id.toolbarLibrary);
        toolbarLibrary.setTitle("Nox");
        ((MainActivity) getActivity()).setSupportActionBar(toolbarLibrary);


        toolbarLibrary.findViewById(R.id.imgSearchLibrary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext() , "Search comic ...", Toast.LENGTH_SHORT).show();
            }
        });

        toolbarLibrary.findViewById(R.id.imgThemeLibrary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext() , "You just change theme ...", Toast.LENGTH_SHORT).show();
            }
        });

        //Genres
        initGenresRecyclerViewAdapter(view);

        return view;
    }

    private void initGenresRecyclerViewAdapter(View view) {
        RecyclerView genresRecyclerView = (RecyclerView) view.findViewById(R.id.genresRecyclerView);
        genresRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        genresRecyclerView.setAdapter(new ItemGenresRVAdapter(getContext()));
    }

}
