package zenlife.nox.nox.detail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.provider.ContactsContract;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import me.samthompson.bubbleactions.BubbleActions;
import me.samthompson.bubbleactions.MenuCallback;
import zenlife.nox.nox.R;
import zenlife.nox.nox.readcomic.ReadComicActivity;
import zenlife.nox.nox.util.BlurBuilder;

public class DetailActivity extends AppCompatActivity implements View.OnLongClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPagerComicDetail);
        viewPager.setAdapter(new ComicViewPageAdapter(getSupportFragmentManager()));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayoutComicDetail);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            //noinspection ConstantConditions
            TextView tv=(TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_font,null);
            Typeface face=Typeface.createFromAsset(this.getAssets(), "fonts/noyhr.otf");
            tv.setTypeface(face);
            tabLayout.getTabAt(i).setCustomView(tv);

        }
        //
        FloatingActionButton readComicFloatBtn = (FloatingActionButton) findViewById(R.id.readComicFloatBtn);
        readComicFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movetoReadComicActivity = new Intent(DetailActivity.this, ReadComicActivity.class);
                startActivity(movetoReadComicActivity);
            }
        });

        //blur backgrorund
        initBlurBackground();

        findViewById(R.id.linearCollapse).setOnLongClickListener(this);

        initCollapseAppBar();

    }

    private void initCollapseAppBar() {

        final Toolbar toolbarDetail = (Toolbar) findViewById(R.id.toolbarDetail);
        final TextView toolbarDetailTitle = (TextView) findViewById(R.id.toolbarDetailTitle);
        final LinearLayout linearCollapse = (LinearLayout) findViewById(R.id.linearCollapse);

        ImageView backDetail = (ImageView) toolbarDetail.findViewById(R.id.backDetail);
        backDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        AppBarLayout appBarDetail = (AppBarLayout) findViewById(R.id.appbarDetail);
        appBarDetail.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0)
                {
                    //  Collapsed
                    toolbarDetailTitle.setVisibility(View.VISIBLE);
                    linearCollapse.setVisibility(View.GONE);
                }
                else
                {
                    //Expanded
                    toolbarDetailTitle.setVisibility(View.GONE);
                    linearCollapse.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initBlurBackground() {
        ImageView imgBackgroundDetail = (ImageView) findViewById(R.id.imgBackgroundDetail);
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.item_comic);
        Bitmap blurredBitmap = BlurBuilder.blur(this, originalBitmap );
        imgBackgroundDetail.setImageDrawable(new BitmapDrawable(getResources(), blurredBitmap));

    }

    @Override
    public boolean onLongClick(final View v) {
        BubbleActions.on(v)
                .fromMenu(R.menu.menu_actions, new MenuCallback() {
                    @Override
                    public void doAction(int itemId) {
                        switch (itemId) {
                            case R.id.action_star:
                                Toast.makeText(v.getContext(), "Star pressed!", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.action_share:
                                Toast.makeText(v.getContext(), "Share pressed!", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.action_hide:
                                Toast.makeText(v.getContext(), "Hide pressed!", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                })
                .show();
        return true;
    }


    public static class ComicViewPageAdapter extends FragmentPagerAdapter {

        private static final int PAGE_COUNT = 2;
        private String[] titlesPage = {"Episode", "Review"};

        public ComicViewPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                default:
                case 0:
                    return EpisodeFragment.newInstance();
                case 1:
                    return ReviewFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titlesPage[position];
        }
    }
}
