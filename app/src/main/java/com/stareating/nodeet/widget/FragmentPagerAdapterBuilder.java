package com.stareating.nodeet.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 婷 on 2017/9/29.
 */

public class FragmentPagerAdapterBuilder {

    private FragmentManager mFragmentManager;
    private List<CharSequence> mTitles = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();

    public FragmentPagerAdapterBuilder(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    public FragmentPagerAdapterBuilder add(CharSequence title, Fragment fragment){
        mTitles.add(title);
        mFragments.add(fragment);
        return this;
    }

    public FragmentPagerAdapter build(){
        return new FragmentPagerAdapter(mFragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles.get(position);
            }
        };
    }
}
