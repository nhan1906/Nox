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
import zenlife.nox.nox.util.ItemNotificationRVAdapter;

public class NotificationsFragment extends Fragment {

    public static NotificationsFragment newInstance() {
        NotificationsFragment fragment = new NotificationsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);


        //Toobar setup
        Toolbar toolbarNotifications = (Toolbar) view.findViewById(R.id.toolbarNotifications);
        toolbarNotifications.setTitle("Nox");
        ((MainActivity) getActivity()).setSupportActionBar(toolbarNotifications);


        toolbarNotifications.findViewById(R.id.imgSearchNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext() , "Search comic ...", Toast.LENGTH_SHORT).show();
            }
        });

        toolbarNotifications.findViewById(R.id.imgThemeNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext() , "You just change theme ...", Toast.LENGTH_SHORT).show();
            }
        });

        //Recycler Notifications
        RecyclerView notificationsRecyclerView = (RecyclerView) view.findViewById(R.id.notificationsRecyclerview);
        notificationsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        notificationsRecyclerView.setAdapter(new ItemNotificationRVAdapter(getContext()));
        return view;
    }

}
