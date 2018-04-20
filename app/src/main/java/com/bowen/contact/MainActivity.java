package com.bowen.contact;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.bowen.contact.adapter.FragmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.contentViewPager)
    ViewPager mContentViewPager;
    @BindView(R.id.tl_tab)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //不需要标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        mContentViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        mContentViewPager.setCurrentItem(0, false);

        // TabLayout
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);    // 默认模式，可以不设置
        mTabLayout.setupWithViewPager(mContentViewPager);      // ViewPager <---> TabLayout

    }

}
