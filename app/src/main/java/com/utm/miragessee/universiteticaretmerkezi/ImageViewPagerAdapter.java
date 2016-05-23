package com.utm.miragessee.universiteticaretmerkezi;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by mirag on 23.05.2016.
 */
public class ImageViewPagerAdapter extends FragmentPagerAdapter {
    private Context _context;
    public static int totalPage = 3;
    public ImageViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        _context = context;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment f = new Fragment();
        switch (position) {
            case 0:
                f = new ImageOneFragment();
                break;
            case 1:
                f = new ImageTwoFragment();
                break;
            case 2:
                f = new ImageThreeFragment();
                break;
            case 3:
                f = new ImageThreeFragment();
                break;
            case 4:
                f = new ImageFourFragment();
                break;
            case 5:
                f = new ImageFiveFragment();
                break;
        }
        return f;
    }
    @Override
    public int getCount() {
        return totalPage;
    }
}
