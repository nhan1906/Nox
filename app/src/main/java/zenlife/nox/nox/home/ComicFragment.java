package zenlife.nox.nox.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import zenlife.nox.nox.MainActivity;
import zenlife.nox.nox.R;
import zenlife.nox.nox.detail.DetailActivity;
import zenlife.nox.nox.listcomic.ListComicActivity;
import zenlife.nox.nox.util.BlurBuilder;
import zenlife.nox.nox.util.ItemComingSoonRVAdapter;
import zenlife.nox.nox.util.ItemLandscape2XRVAdapter;
import zenlife.nox.nox.util.ItemLandscapeXRVAdapter;
import zenlife.nox.nox.util.ItemNewestComicRVAdapter;
import zenlife.nox.nox.util.ItemPopularComicRVAdapter;
import zenlife.nox.nox.util.ItemRecentComicRVAdapter;
import zenlife.nox.nox.util.ItemTopComicRVAdapter;
import zenlife.nox.nox.util.WrapContentViewPager;

public class ComicFragment extends Fragment {

    private static int currentPager = 0;
    public static ComicFragment newInstance(String param1, String param2) {
        ComicFragment fragment = new ComicFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comic, container, false);

        //Slider Image
        initSliderImageViewPager(view);

        //Toobar setup
        Toolbar toolbarComic = (Toolbar) view.findViewById(R.id.toolBarComic);
        toolbarComic.setTitle("Nox");
        ((MainActivity) getActivity()).setSupportActionBar(toolbarComic);


        toolbarComic.findViewById(R.id.imgSearchComic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext() , "Search comic ...", Toast.LENGTH_SHORT).show();
            }
        });

        toolbarComic.findViewById(R.id.imgThemeComic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext() , "You just change theme ...", Toast.LENGTH_SHORT).show();
            }
        });

        initTopComics(view);

        //Popular Comics
        initPopularComics(view);

        //Comingsoon Comics

        initComingSoonComics(view);

        //Recent Comics
        initRecentComics(view);

        //Newest Comics
        initNewestComics(view);

        //Landscape X Comics
        initLandscapedX(view);

        //Landscape 2X Comics
        initLandscaped2X(view);

        initMostComic(view);

        (view.findViewById(R.id.frameMostComic)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

    private void initMostComic(View view) {
        ImageView imgBackgroundMost = (ImageView) view.findViewById(R.id.imgBackgroundMost);
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xmen);
        Bitmap blurredBitmap = BlurBuilder.blur(getContext(), originalBitmap );
        imgBackgroundMost.setImageDrawable(new BitmapDrawable(getResources(), blurredBitmap));
    }

    private void initComingSoonComics(View view) {
        final RecyclerView comingSoonRecyclerView = (RecyclerView) view.findViewById(R.id.comingSoonRecyclerView);
        comingSoonRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2 ,LinearLayoutManager.HORIZONTAL, false));
        comingSoonRecyclerView.setAdapter(new ItemComingSoonRVAdapter(getContext() , 1));

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(comingSoonRecyclerView);

        (view.findViewById(R.id.comingSoonSeeAll)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListComicActivity.class);
                startActivity(intent);
            }
        });

        final FrameLayout day01 = (FrameLayout) view.findViewById(R.id.day01);
        final FrameLayout day02 = (FrameLayout) view.findViewById(R.id.day02);
        final FrameLayout day03 = (FrameLayout) view.findViewById(R.id.day03);
        final FrameLayout day04 = (FrameLayout) view.findViewById(R.id.day04);
        final FrameLayout day05 = (FrameLayout) view.findViewById(R.id.day05);
        final FrameLayout day06 = (FrameLayout) view.findViewById(R.id.day06);
        final FrameLayout day07 = (FrameLayout) view.findViewById(R.id.day07);

        final FrameLayout cr01 = (FrameLayout) view.findViewById(R.id.cr01);
        final FrameLayout cr02 = (FrameLayout) view.findViewById(R.id.cr02);
        final FrameLayout cr03 = (FrameLayout) view.findViewById(R.id.cr03);
        final FrameLayout cr04 = (FrameLayout) view.findViewById(R.id.cr04);
        final FrameLayout cr05 = (FrameLayout) view.findViewById(R.id.cr05);
        final FrameLayout cr06 = (FrameLayout) view.findViewById(R.id.cr06);
        final FrameLayout cr07 = (FrameLayout) view.findViewById(R.id.cr07);

        day01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                day01.setBackgroundColor(getResources().getColor(R.color.backgroundSelectedItem));
                cr01.setVisibility(View.VISIBLE);
                comingSoonRecyclerView.setAdapter(new ItemComingSoonRVAdapter(getContext(), 3));
                day02.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day03.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day04.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day05.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day06.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day07.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));

                cr02.setVisibility(View.INVISIBLE);
                cr03.setVisibility(View.INVISIBLE);
                cr04.setVisibility(View.INVISIBLE);
                cr05.setVisibility(View.INVISIBLE);
                cr06.setVisibility(View.INVISIBLE);
                cr07.setVisibility(View.INVISIBLE);



            }
        });

        day02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                day02.setBackgroundColor(getResources().getColor(R.color.backgroundSelectedItem));
                cr02.setVisibility(View.VISIBLE);
                comingSoonRecyclerView.setAdapter(new ItemComingSoonRVAdapter(getContext(),6));
                day01.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day03.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day04.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day05.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day06.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day07.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));

                cr01.setVisibility(View.INVISIBLE);
                cr03.setVisibility(View.INVISIBLE);
                cr04.setVisibility(View.INVISIBLE);
                cr05.setVisibility(View.INVISIBLE);
                cr06.setVisibility(View.INVISIBLE);
                cr07.setVisibility(View.INVISIBLE);



            }
        });

        day03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                day03.setBackgroundColor(getResources().getColor(R.color.backgroundSelectedItem));
                cr03.setVisibility(View.VISIBLE);
                comingSoonRecyclerView.setAdapter(new ItemComingSoonRVAdapter(getContext(), 10));
                day01.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day02.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day04.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day05.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day06.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day07.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));

                cr01.setVisibility(View.INVISIBLE);
                cr02.setVisibility(View.INVISIBLE);
                cr04.setVisibility(View.INVISIBLE);
                cr05.setVisibility(View.INVISIBLE);
                cr06.setVisibility(View.INVISIBLE);
                cr07.setVisibility(View.INVISIBLE);



            }
        });

        day04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                day04.setBackgroundColor(getResources().getColor(R.color.backgroundSelectedItem));
                cr04.setVisibility(View.VISIBLE);
                comingSoonRecyclerView.setAdapter(new ItemComingSoonRVAdapter(getContext() , 14));
                day02.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day03.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day01.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day05.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day06.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day07.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));

                cr02.setVisibility(View.INVISIBLE);
                cr03.setVisibility(View.INVISIBLE);
                cr01.setVisibility(View.INVISIBLE);
                cr05.setVisibility(View.INVISIBLE);
                cr06.setVisibility(View.INVISIBLE);
                cr07.setVisibility(View.INVISIBLE);



            }
        });

        day05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                day05.setBackgroundColor(getResources().getColor(R.color.backgroundSelectedItem));
                cr05.setVisibility(View.VISIBLE);
                comingSoonRecyclerView.setAdapter(new ItemComingSoonRVAdapter(getContext() , 7));
                day02.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day03.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day04.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day01.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day06.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day07.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));

                cr02.setVisibility(View.INVISIBLE);
                cr03.setVisibility(View.INVISIBLE);
                cr04.setVisibility(View.INVISIBLE);
                cr01.setVisibility(View.INVISIBLE);
                cr06.setVisibility(View.INVISIBLE);
                cr07.setVisibility(View.INVISIBLE);



            }
        });

        day06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                day06.setBackgroundColor(getResources().getColor(R.color.backgroundSelectedItem));
                cr06.setVisibility(View.VISIBLE);
                comingSoonRecyclerView.setAdapter(new ItemComingSoonRVAdapter(getContext() ,8));
                day02.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day03.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day04.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day05.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day01.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day07.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));

                cr02.setVisibility(View.INVISIBLE);
                cr03.setVisibility(View.INVISIBLE);
                cr04.setVisibility(View.INVISIBLE);
                cr05.setVisibility(View.INVISIBLE);
                cr01.setVisibility(View.INVISIBLE);
                cr07.setVisibility(View.INVISIBLE);



            }
        });

        day07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                day07.setBackgroundColor(getResources().getColor(R.color.backgroundSelectedItem));
                cr07.setVisibility(View.VISIBLE);

                day02.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day03.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day04.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day05.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day06.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));
                day01.setBackgroundColor(getResources().getColor(R.color.background_comingsoon));

                cr02.setVisibility(View.INVISIBLE);
                cr03.setVisibility(View.INVISIBLE);
                cr04.setVisibility(View.INVISIBLE);
                cr05.setVisibility(View.INVISIBLE);
                cr06.setVisibility(View.INVISIBLE);
                cr01.setVisibility(View.INVISIBLE);



            }
        });

    }

    private void initTopComics(View view) {
        RecyclerView topComicRecyclerView = (RecyclerView) view.findViewById(R.id.topComicRecyclerView);
        topComicRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        topComicRecyclerView.setAdapter(new ItemTopComicRVAdapter(getContext()));

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(topComicRecyclerView);
    }


    private void initLandscaped2X(View view) {
        RecyclerView landscape2XRecyclerView = (RecyclerView) view.findViewById(R.id.landscape2XRecyclerView);
        landscape2XRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        landscape2XRecyclerView.setAdapter(new ItemLandscape2XRVAdapter(getContext()));

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(landscape2XRecyclerView);
    }

    private void initLandscapedX(View view) {
        RecyclerView landscapeXRecyclerView = (RecyclerView) view.findViewById(R.id.landscapeXRecyclerView);
        landscapeXRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        landscapeXRecyclerView.setAdapter(new ItemLandscapeXRVAdapter(getContext()));

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(landscapeXRecyclerView);
    }

    private void initNewestComics(View view) {
        RecyclerView newestRecyclerView = (RecyclerView) view.findViewById(R.id.newestRecyclerView);
        newestRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        newestRecyclerView.setAdapter(new ItemNewestComicRVAdapter(getContext()));

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(newestRecyclerView);

        (view.findViewById(R.id.newestSeeAll)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListComicActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRecentComics(View view) {
        RecyclerView recentRecyclerView = (RecyclerView) view.findViewById(R.id.recentRecyclerView);
        recentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recentRecyclerView.setAdapter(new ItemRecentComicRVAdapter(getContext()));

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recentRecyclerView);

        (view.findViewById(R.id.recentSeeAll)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListComicActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initPopularComics(View view) {
        RecyclerView popularRecyclerView = (RecyclerView) view.findViewById(R.id.popularRecyclerView);
        popularRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        popularRecyclerView.setAdapter(new ItemPopularComicRVAdapter(getContext()));

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(popularRecyclerView);

        (view.findViewById(R.id.popularSeeAll)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListComicActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initSliderImageViewPager(View view) {
        final WrapContentViewPager sliderImageViewPager = (WrapContentViewPager)  view.findViewById(R.id.slideImageViewPager);
        sliderImageViewPager.setAdapter(new SliderImageAdapter(getContext()));
        CircleIndicator indicator = (CircleIndicator)  view.findViewById(R.id.indicator);
        indicator.setViewPager(sliderImageViewPager);

        //Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if (currentPager == 5){
                    currentPager = 0;
                }
                sliderImageViewPager.setCurrentItem(currentPager++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }
}
