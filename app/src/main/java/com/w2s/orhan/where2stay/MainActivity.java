package com.w2s.orhan.where2stay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.google.firebase.database.FirebaseDatabase;
import com.w2s.orhan.where2stay.Advert.add_typeOne;
import com.w2s.orhan.where2stay.Advert.add_typeTwo;
import com.w2s.orhan.where2stay.Sign.SignActivity;
import com.w2s.orhan.where2stay.Tabs.FiveFragment;
import com.w2s.orhan.where2stay.Tabs.FourFragment;
import com.w2s.orhan.where2stay.Tabs.OneFragment;
import com.w2s.orhan.where2stay.Tabs.SixFragment;
import com.w2s.orhan.where2stay.Tabs.ThreeFragment;
import com.w2s.orhan.where2stay.Tabs.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static SharedPreferences sharedPreferences;
    private String c0 = "kiralık daire",
                   c1 = "satılık daire",
                   c2 = "günlük kiralık daire",
                   c3 = "kiralık oda",
                   c4 = "ev arkadaşı",
                   c5 = "grup";

    static {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

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



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = viewPager.getCurrentItem();
                Intent intent = null;
                switch (i) {
                    case 0 : intent = new Intent(getApplicationContext(), add_typeOne.class); break;
                    case 1 : intent = new Intent(getApplicationContext(), add_typeTwo.class); break;
                }
                startActivity(intent);
            }
        });

    // bottom navigation
        final BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_sort:
                        showPopup(bottomNav);
                        break;
                }
                return true;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), c0);
        adapter.addFragment(new TwoFragment(), c1);
        adapter.addFragment(new ThreeFragment(), c2);
        adapter.addFragment(new FourFragment(), c3);
        adapter.addFragment(new FiveFragment(), c4);
        adapter.addFragment(new SixFragment(), c5);

        viewPager.setAdapter(adapter);
    }

    // viewpager adapter
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

    // sorting
    public void showPopup (View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });

        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }


}
