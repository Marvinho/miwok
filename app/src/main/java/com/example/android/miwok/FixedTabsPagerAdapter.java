package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Marvi on 09.05.2017.
 */

public class FixedTabsPagerAdapter extends FragmentPagerAdapter {

    public FixedTabsPagerAdapter(FragmentManager fn){
        super(fn);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new NumbersFragment();
            case 1:
                return new FamilyFragment();
            case 2:
                return new ColorsFragment();
            case 3:
                return new PhrasesFragment();
            default:
                return null;

        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "Phrases";
            case 1:
                return "Colors";
            case 2:
                return "Family";
            default:
                return "Numbers";
        }
    }
}
