package com.kodilan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kodilan.adapters.ViewPagerAdapter;
import com.kodilan.fragments.Home;
import com.kodilan.fragments.Search;
import com.kodilan.fragments.Subscribe;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(getString(R.string.lastest_jobs));

        viewPager = findViewById(R.id.viewPager);
        viewPager.setUserInputEnabled(false);
        viewPager.setOffscreenPageLimit(3);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());

        adapter.addFragment(new Home());
        adapter.addFragment(new Search());
        adapter.addFragment(new Subscribe());
        viewPager.setAdapter(adapter);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavBar);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportActionBar().setTitle(getString(R.string.lastest_jobs));
                    viewPager.setCurrentItem(0, false);
                    break;
                case R.id.search:
                    getSupportActionBar().setTitle(getString(R.string.search_jobs));
                    viewPager.setCurrentItem(1, false);
                    break;
                case R.id.bell:
                    getSupportActionBar().setTitle(getString(R.string.subscribe));
                    viewPager.setCurrentItem(2, false);
                    break;
            }
            return true;
        }
    };
}