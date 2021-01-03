package com.example.doan_android.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.doan_android.Adapter.AdapterPlayerMusic;
import com.example.doan_android.R;
import com.google.android.material.tabs.TabLayout;

public class play_music extends AppCompatActivity {


    //Add view
    private TabLayout tabPlayerMusic;
    private ViewPager view_pager_music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

      tabPlayerMusic = findViewById(R.id.tabPlayerMusic);
      view_pager_music = findViewById(R.id.view_pager_music);

      //ViewPager for display fragments
      AdapterPlayerMusic adaptermusic = new AdapterPlayerMusic(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
      view_pager_music.setAdapter(adaptermusic);
      tabPlayerMusic.setupWithViewPager(view_pager_music);

      //Set icon
      tabPlayerMusic.getTabAt(0).setIcon(R.drawable.ic_baseline_music_note_24);
      tabPlayerMusic.getTabAt(1).setIcon(R.drawable.ic_baseline_list_alt_24);

    }
}
