package zenlife.nox.nox.account;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zenlife.nox.nox.R;
import zenlife.nox.nox.util.BlurBuilder;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class SignInActivity extends AppCompatActivity {



    private TabLayout tabLayoutSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
//
//        ImageView imgBackgroundSignUpBlur = (ImageView) findViewById(R.id.imgBackgroundSignUpBlur);
//        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xmen);
//        Bitmap blurredBitmap = BlurBuilder.blur(this, originalBitmap );
//        imgBackgroundSignUpBlur.setImageDrawable(new BitmapDrawable(getResources(), blurredBitmap));

        ViewPager viewPageSignIn = (ViewPager) findViewById(R.id.viewPageSignIn);
        viewPageSignIn.setAdapter(new LogInViewPageAdapter(getSupportFragmentManager()));

        tabLayoutSignIn = (TabLayout) findViewById(R.id.tabLayoutSignIn);
        tabLayoutSignIn.setBackgroundColor(getResources().getColor(R.color.color_tabbar));
        tabLayoutSignIn.setTabTextColors(getResources().getColor(R.color.colorBottomNavDefault), getResources().getColor(R.color.white));
        tabLayoutSignIn.setDrawingCacheBackgroundColor(getResources().getColor(R.color.white));
        tabLayoutSignIn.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
        tabLayoutSignIn.setupWithViewPager(viewPageSignIn);

    }

    public static class LogInViewPageAdapter extends FragmentPagerAdapter {

        private static final int PAGE_COUNT = 2;
        private String[] titlesPage = {"Sign In", "Sign Up"};

        public LogInViewPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                default:
                case 0:
                    return SignInFragment.newInstance();
                case 1:
                    return SignUpFragment.newInstance();
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

