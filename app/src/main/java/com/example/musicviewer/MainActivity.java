package com.example.musicviewer;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    FrameLayout placeholder;
    private String currentFragment = null;
    private static final String CURRENT_FRAGMENT = "com.example.musicviewer.current_fragment";

    private static SongViewModel songViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            currentFragment = savedInstanceState.getString(CURRENT_FRAGMENT);
        }

        placeholder = findViewById(R.id.fragment_placeholder);
        tabLayout = findViewById(R.id.tab_layout_main);

        updateFragment(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).getText().toString());

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateFragment(tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    public SongViewModel getSongViewModel() {
        if (songViewModel == null) {
            songViewModel = ViewModelProviders.of(this).get(SongViewModel.class);
            /*songViewModel.getAllSongs().observe(this, new Observer<List<SongItem>>() {
                @Override
                public void onChanged(@Nullable List<SongItem> songItems) {
                    Toast.makeText(
                            MainActivity.this,
                            "Song Items Observed: " + songItems,
                            Toast.LENGTH_LONG).show();
                }
            });*/
        }
        return songViewModel;
    }

    private void updateFragment(String str) {
        if (currentFragment != null && !currentFragment.equals(str)) {
            destroyFragment();
            currentFragment = null;
        }
        if (currentFragment == null || !currentFragment.equals(str)) {
            makeFragment(str);
            currentFragment = str;
        }
    }

    private void makeFragment(String str) {
        MusicListFragment mlf = MusicListFragment.newInstance(str);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_placeholder, mlf).addToBackStack(null).commit();


    }

    private void destroyFragment() {
        FragmentManager fm = getFragmentManager();
        MusicListFragment mlf = (MusicListFragment)
                fm.findFragmentById(R.id.fragment_placeholder);
        if (fm != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(mlf).commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstance) {
        savedInstance.putString(CURRENT_FRAGMENT, currentFragment);
        super.onSaveInstanceState(savedInstance);
    }
}
