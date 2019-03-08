package com.example.newsapp.adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.newsapp.fragments.BussinessFragment;
import com.example.newsapp.fragments.EntertainmentFragment;
import com.example.newsapp.fragments.HealthFragment;
import com.example.newsapp.fragments.HomeFragment;
import com.example.newsapp.fragments.ScienceFragment;
import com.example.newsapp.fragments.SportsFragments;
import com.example.newsapp.fragments.TechnologyFragment;

import java.util.ArrayList;
import java.util.List;

public class TabViewAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;
    public TabViewAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new BussinessFragment());
        fragmentList.add(new EntertainmentFragment());
        fragmentList.add(new HealthFragment());
        fragmentList.add(new ScienceFragment());
        fragmentList.add(new SportsFragments());
        fragmentList.add(new TechnologyFragment());
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Home";
            case 1:
                return "Bussiness";
            case 2:
                return "Entertainment";
            case 3:
                return "Health";
            case 4:
                return "Science";
            case 5:
                return "Sports";
            case 6:
                return "Technology";
        }
        return null;
    }
}
