package zenlife.nox.nox;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Nhan on 11/13/2017.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_NUMBER = 2;
    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            default:
            case 0:
                return HomeFragment.newInstance();
            case 1:
                return MyPageFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }
}
