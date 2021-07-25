package com.example.fruit_sabzi_mandi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private  final List<Fragment> ListFragment=new ArrayList<>();
    private  final List<String> ListTitle =new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ListFragment.get(position);
    }

    @Override
    public int getCount() {
        return ListTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }


    public void AddFragment(Fragment fragment,String title){
        ListFragment.add(fragment);
        ListTitle.add(title);
    }
}
