package com.example.doan_android.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.doan_android.Adapter.AdapterUI;
import com.example.doan_android.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    //Add view
    private TabLayout tabMain;
    private ViewPager view_Pager;
    private LinearLayout layoutThongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.background_layout);
        //setContentView(R.layout.bottom_player);


        tabMain = findViewById(R.id.tabMain);
        view_Pager = findViewById(R.id.view_pager);

        //ViewPager for display fragments
        AdapterUI adapterui = new AdapterUI(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        view_Pager.setAdapter(adapterui);
        tabMain.setupWithViewPager(view_Pager);

        //Set icon
        tabMain.getTabAt(0).setIcon(R.drawable.ic_baseline_home_24);
        tabMain.getTabAt(1).setIcon(R.drawable.ic_baseline_search_24);

        //Ấn vào player dưới màn hình để vào activity play music
        layoutThongTin = findViewById(R.id.layoutThongTin);
        layoutThongTin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivityPlayMusic();
          }
        });
    }

    public void startActivityPlayMusic() {
        Intent intent = new Intent(this, play_music.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
    }
}
