package com.bowen.contact.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

public class FragmentAdapter extends FragmentPagerAdapter {
    SparseArray<Fragment> myPager = new SparseArray<>();
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = myPager.get(position);
        if(fragment == null){
            switch (position){
                case 0 :
                    fragment =  T9Fragment.newInstance(mTitleArray[position],String.valueOf(position));
                    break;
                default:
                    fragment =  ContactFragment.newInstance(mTitleArray[position],String.valueOf(position));
                    break;
            }

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitleArray.length;
    }
    private String[] mTitleArray = {"拨号", "联系人", "通话记录"};
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleArray[position];
    }
}
