package com.ardnn.movieapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.ardnn.movieapp.R;
import com.ardnn.movieapp.fragments.MovieFragment;
import com.ardnn.movieapp.fragments.ProfileFragment;
import com.ardnn.movieapp.fragments.TvShowFragment;
import com.ardnn.movieapp.utils.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    // widgets
    private BottomNavigationView bnvMain;

    // attributes
    public static final String EXTRA_STRING = "extra_string";
    private Map<Integer, Fragment> fragmentMap;
    private boolean isFirstLaunch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialization
        bnvMain = findViewById(R.id.bnv_main);
        fragmentMap = new HashMap<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // put all fragments to map
        fragmentMap.put(R.id.menu_item_movie, MovieFragment.newInstance());
        fragmentMap.put(R.id.menu_item_tv_show, TvShowFragment.newInstance());
        fragmentMap.put(R.id.menu_item_profile, ProfileFragment.newInstance());

        bnvMain.setOnNavigationItemSelectedListener(this);
        bnvMain.setSelectedItemId(R.id.menu_item_movie);

        // change action bar's title color
        Util.changeActionBarTitle(this, MovieFragment.newInstance().getArguments().getString(EXTRA_STRING));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = fragmentMap.get(item.getItemId());
        if (fragment != null) {
            assert fragment.getArguments() != null || getActionBar() != null : "Null Pointer Exception oi!";
            String titleActionBar = fragment.getArguments().getString(EXTRA_STRING);
            Util.changeActionBarTitle(this, titleActionBar);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_main, fragment)
                    .commit();
        }
        return true;
    }
}