package com.w2s.orhan.where2stay;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.w2s.orhan.where2stay.Sign.SignActivity;
import com.w2s.orhan.where2stay.Tabs.FourFragment;
import com.w2s.orhan.where2stay.Tabs.OneFragment;
import com.w2s.orhan.where2stay.Tabs.ThreeFragment;
import com.w2s.orhan.where2stay.Tabs.TwoFragment;

import java.security.SignatureSpi;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        /*
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogin", false);
        editor.commit();
        */

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "Kiralık Daire");
        adapter.addFragment(new TwoFragment(), "Satılık Daire");
        adapter.addFragment(new ThreeFragment(), "Günlük Kiralık Daire");
        adapter.addFragment(new FourFragment(), "Ev Arkadaşı");

        viewPager.setAdapter(adapter);
    }

    public void openChat(View view) {
        if(sharedPreferences.getBoolean("isLogin", false)){
            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), SignActivity.class);
            startActivity(intent);
        }
    }
    public void openProfile(View view) {
        if (sharedPreferences.getBoolean("isLogin", false)) {
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), SignActivity.class);
            startActivity(intent);
        }

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
