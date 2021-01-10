package com.example.doan_android.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.doan_android.Adapter.AdapterUI;
import com.example.doan_android.Model.Song;
import com.example.doan_android.R;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BackgroundActivity extends AppCompatActivity {

    //Add view
    private TabLayout tabMain;
    private ViewPager viewPagerMain;
    LinearLayout layoutInformation;


    public static MediaPlayer mediaPlayer = new MediaPlayer();
    public static ArrayList<Song> listSong = new ArrayList<>();
    public static int position = 0;
    public static int check = 0;
    ImageView image;
    //private LinearLayout layoutThongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.background_layout);
        //setContentView(R.layout.bottom_player);


        tabMain = findViewById(R.id.tabMain);
        viewPagerMain = findViewById(R.id.view_pager);

        image =findViewById(R.id.imgbottomPlay);
        //ViewPager for display fragments
        AdapterUI adapterUI = new AdapterUI(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerMain.setAdapter(adapterUI);
        tabMain.setupWithViewPager(viewPagerMain);

        //Set icon
        tabMain.getTabAt(0).setIcon(R.drawable.ic_baseline_home_24);
        tabMain.getTabAt(1).setIcon(R.drawable.ic_baseline_search_24);

//        Ấn vào player dưới màn hình để vào activity play music
        layoutInformation = findViewById(R.id.bottomPlayer);
        layoutInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityPlayMusic();
            }
        });
        findViewById(R.id.imgbottomPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (BackgroundActivity.mediaPlayer.isPlaying()) {
                    BackgroundActivity.mediaPlayer.pause();
                    image.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                } else {
                    BackgroundActivity.mediaPlayer.start();
                    image.setImageResource(R.drawable.ic_baseline_pause_24);
                }
            }
        });
        findViewById(R.id.imgbottomNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (BackgroundActivity.listSong.size() > 0) {
                    if (BackgroundActivity.mediaPlayer.isPlaying() || BackgroundActivity.mediaPlayer != null) {
                        BackgroundActivity.mediaPlayer.stop();
                        BackgroundActivity.mediaPlayer.release();
                        BackgroundActivity.mediaPlayer = null;
                    }
                    if (BackgroundActivity.position < (BackgroundActivity.listSong.size())) {
                        image.setImageResource(R.drawable.ic_baseline_pause_24);
                        BackgroundActivity.position++;
                        if (BackgroundActivity.position > (BackgroundActivity.listSong.size() - 1)) {
                            BackgroundActivity.position = 0;
                        }
                        new PlayMusicActivity.PlayMp3().execute(BackgroundActivity.listSong.get(BackgroundActivity.position).getUrlBaihat());
                    }
                }

            }
        });
        findViewById(R.id.imgbottomPre).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (BackgroundActivity.listSong.size() > 0) {
                    if (BackgroundActivity.mediaPlayer.isPlaying() || BackgroundActivity.mediaPlayer != null) {
                        BackgroundActivity.mediaPlayer.stop();
                        BackgroundActivity.mediaPlayer.release();
                        BackgroundActivity.mediaPlayer = null;
                    }
                    if (BackgroundActivity.position < (BackgroundActivity.listSong.size())) {
                        image.setImageResource(R.drawable.ic_baseline_pause_24);
                        BackgroundActivity.position--;

                        if (BackgroundActivity.position < 0) {
                            BackgroundActivity.position = BackgroundActivity.listSong.size() - 1;
                        }

                        new PlayMusicActivity.PlayMp3().execute(BackgroundActivity.listSong.get(BackgroundActivity.position).getUrlBaihat());
                    }
                }
            }
        });
    }


    public void startActivityPlayMusic() {
        check = 1;
        Intent intent = new Intent(this, PlayMusicActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
    }
}
