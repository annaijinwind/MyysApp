package com.maoyan.fengjianqi.myysapp.movie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.maoyan.fengjianqi.myysapp.fragements.BaseFragement;

import java.util.List;

/**
 * Created by fengjianqi on 2016/8/4.
 */
public class MovePagerAdapter extends FragmentPagerAdapter{
    private List<BaseFragement> fragments;

    public MovePagerAdapter(FragmentManager fm, List<BaseFragement> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
