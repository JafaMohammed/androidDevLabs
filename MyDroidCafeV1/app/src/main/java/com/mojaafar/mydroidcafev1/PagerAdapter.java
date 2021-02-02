package com.mojaafar.mydroidcafev1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs;

    public PagerAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm, numOfTabs);
        this.mNumOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0 : return new DessertRecipeFragment();
            case 1 : return new PastriesRecipeFragment();
            case 2 : return new StoresFragment();
            default : return null;
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
