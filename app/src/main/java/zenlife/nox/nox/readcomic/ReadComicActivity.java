package zenlife.nox.nox.readcomic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import zenlife.nox.nox.R;
import zenlife.nox.nox.util.ItemReadComicRVAdapter;
import zenlife.nox.nox.util.RecyclerItemClickListener;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ReadComicActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    private boolean isLove = false;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private Toolbar toolbarReadcomic;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_read_comic);
        toolbarReadcomic = (Toolbar) findViewById(R.id.toolbarReadcomic);
        setSupportActionBar(toolbarReadcomic);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        ViewPager viewPager = (ViewPager) findViewById(R.id.fullscreen_content) ;
        viewPager.setAdapter(new SliderReadComicVPAdapter(this));
        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.bottomBarReadComic).setOnTouchListener(mDelayHideTouchListener);
        toolbarReadcomic.setOnTouchListener(mDelayHideTouchListener);
        final ImageView imgLove =(ImageView) findViewById(R.id.icon_love_read);
        imgLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLove = !isLove;
                if(isLove){
                    imgLove.setImageDrawable(getResources().getDrawable(R.drawable.icon_heart));
                }
                else {
                    imgLove.setImageDrawable(getResources().getDrawable(R.drawable.icon_love_unselected));
                }
            }
        });

        findViewById(R.id.icon_comment_read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ReadComicActivity.this, "You just comment about this episode", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.icon_share_read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out my app at: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        ImageView backDetail = (ImageView) toolbarReadcomic.findViewById(R.id.backReadComic);
        backDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    public static class SliderReadComicVPAdapter extends PagerAdapter {

        private LayoutInflater inflater;
        private ReadComicActivity context;
        private static int i = 0;

        public SliderReadComicVPAdapter(ReadComicActivity context){
            this.context = context;
            inflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            View readComicView = inflater.inflate(R.layout.content_read_comic, view, false);
            view.addView(readComicView, 0);

            RecyclerView readComicRecyclerView = (RecyclerView) view.findViewById(R.id.readComicRecyclerView);
            readComicRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            readComicRecyclerView.setAdapter(new ItemReadComicRVAdapter(context));
            readComicRecyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(context, readComicRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                        @Override public void onItemClick(View view, int position) {
                            context.toggle();
                        }

                        @Override public void onLongItemClick(View view, int position) {
                            // do whatever
                        }
                    })
            );
            return readComicView;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }
}
