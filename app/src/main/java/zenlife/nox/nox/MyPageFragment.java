package zenlife.nox.nox;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import zenlife.nox.nox.account.SignInActivity;
import zenlife.nox.nox.listcomic.ListComicActivity;
import zenlife.nox.nox.util.BlurBuilder;
import zenlife.nox.nox.util.ItemFavoriteRVAdapter;
import zenlife.nox.nox.util.ItemPopularComicRVAdapter;

public class MyPageFragment extends Fragment {

    private ImageView imgSignOut;
    public MyPageFragment() {
    }


    public static MyPageFragment newInstance() {
        MyPageFragment fragment = new MyPageFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);

        initLoveComics(view);
        initNotifyComics(view);
        initRecentComics(view);
        initSaveComics(view);

        ImageView imgBackgroundProfile = (ImageView) view.findViewById(R.id.imgBackgroundProfile);
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avatar);
        Bitmap blurredBitmap = BlurBuilder.blur(getContext(), originalBitmap );
        imgBackgroundProfile.setImageDrawable(new BitmapDrawable(getResources(), blurredBitmap));

        final ImageView imgSignOutImageView= (ImageView) view.findViewById(R.id.imgSignOut);
        imgBackgroundProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SignInActivity.class));
            }
        });

        final NestedScrollView days_list_5 =(NestedScrollView) view.findViewById(R.id.days_list_5);
        days_list_5.setVisibility(View.GONE);

        final LinearLayout linearProfile = (LinearLayout) view.findViewById(R.id.linearProfile);
        linearProfile.setVisibility(View.GONE);

        final FrameLayout frameSignIn = (FrameLayout) view.findViewById(R.id.frameSignIn);
        final TextView txtSignInMyPage = (TextView) view.findViewById(R.id.txtSignInMyPage);
        txtSignInMyPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToLogInActivity = new Intent(getActivity(), SignInActivity.class);
                startActivity(moveToLogInActivity);
                days_list_5.setVisibility(View.VISIBLE);
                frameSignIn.setVisibility(View.GONE);
                linearProfile.setVisibility(View.VISIBLE);
                imgSignOutImageView.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }


    private void initLoveComics(View view) {
        RecyclerView popularRecyclerView = (RecyclerView) view.findViewById(R.id.loveRCMyPage);
        popularRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        popularRecyclerView.setAdapter(new ItemFavoriteRVAdapter(getContext()));

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(popularRecyclerView);

    }


    private void initSaveComics(View view) {
        RecyclerView popularRecyclerView = (RecyclerView) view.findViewById(R.id.saveRCMyPage);
        popularRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        popularRecyclerView.setAdapter(new ItemFavoriteRVAdapter(getContext()));

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(popularRecyclerView);

    }


    private void initRecentComics(View view) {
        RecyclerView popularRecyclerView = (RecyclerView) view.findViewById(R.id.recentRCMyPage);
        popularRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        popularRecyclerView.setAdapter(new ItemFavoriteRVAdapter(getContext()));

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(popularRecyclerView);

    }


    private void initNotifyComics(View view) {
        RecyclerView popularRecyclerView = (RecyclerView) view.findViewById(R.id.notifyRCMyPage);
        popularRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        popularRecyclerView.setAdapter(new ItemFavoriteRVAdapter(getContext()));

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(popularRecyclerView);

    }

}
