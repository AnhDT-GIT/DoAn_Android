package com.example.doan_android.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.loader.content.AsyncTaskLoader;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.doan_android.Adapter.AdapterPlayerMusic;
import com.example.doan_android.Adapter.AdapterSong;
import com.example.doan_android.Fragment.music_player;
import com.example.doan_android.Model.Album;
import com.example.doan_android.Model.Baihat;
import com.example.doan_android.Model.Banner;
import com.example.doan_android.Model.Playlist;
import com.example.doan_android.R;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class play_music extends AppCompatActivity {


    //Add view
    private TabLayout tabPlayerMusic;
    private ViewPager view_pager_music;
    public static ArrayList<Baihat> baihatArrayList = new ArrayList<>();
    //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    AdapterSong adapterSong;
    AdapterPlayerMusic adapterPlayerMusic;

    Baihat baihat;

    TextView txtMusicTenBaiHat, txtMusicTenNgheSi, txtTimeSong, txtTotalTimeSong;
    SeekBar barTime;
    ImageView btnPlayShuffle, btnPlayPre, btnPlay, btnPlayNext, btnPlayLoop;
    MediaPlayer mediaPlayer;
    music_player musicplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        //Init
        tabPlayerMusic = findViewById(R.id.tabPlayerMusic);
        view_pager_music = findViewById(R.id.view_pager_music);
        txtMusicTenBaiHat = findViewById(R.id.txtMusicTenBaiHat);
        txtMusicTenNgheSi = findViewById(R.id.txtbtmTenNgheSi);
        txtTimeSong = findViewById(R.id.txtTimeSong);
        txtTotalTimeSong = findViewById(R.id.txtTotalTimeSong);
        btnPlay = findViewById(R.id.btnPlay);
        btnPlayShuffle = findViewById(R.id.btnPlayShuffle);
        btnPlayPre = findViewById(R.id.btnPlayPre);
        btnPlayNext = findViewById(R.id.btnPlayNext);
        btnPlayLoop = findViewById(R.id.btnPlayLoop);
        //ViewPager for display fragments
        AdapterPlayerMusic adaptermusic = new AdapterPlayerMusic(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        view_pager_music.setAdapter(adaptermusic);
        tabPlayerMusic.setupWithViewPager(view_pager_music);
        musicplayer = (music_player) adapterPlayerMusic.getItem(0);

        if (baihatArrayList.size() > 0){
            new PlayMp3File().execute(baihatArrayList.get(0).getUrlBaihat());
            btnPlay.setImageResource(R.drawable.ic_baseline_pause_24);
        }

        //Set icon
        tabPlayerMusic.getTabAt(0).setIcon(R.drawable.ic_baseline_list_alt_24);
        tabPlayerMusic.getTabAt(1).setIcon(R.drawable.ic_baseline_music_note_24).select();
        tabPlayerMusic.getTabAt(2).setIcon(R.drawable.ic_baseline_text_fields_24);

        GetDataFromItent();

        EventClick();

        Getdata();

    }

  class PlayMp3File extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotalTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        barTime.setMax(mediaPlayer.getDuration());
    }

    private void Getdata() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("baihats")) {
                baihat = (Baihat) intent.getParcelableExtra("baihats");
                Toast.makeText(play_music.this, baihat.getTenBaihat(),Toast.LENGTH_LONG).show();
            }
        }
    }

    private void EventClick(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
          @Override
          public void run() {
              if (adapterPlayerMusic.getItem(1) != null){
                if(baihatArrayList.size() > 0){
                    musicplayer.Playnhac(baihatArrayList.get(0).getHinhBaihat());
                    handler.removeCallbacks(this);
                }
                else{
                      handler.postDelayed(this, 300);
                }
              }
          }
        }, 500);
        btnPlay.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                btnPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24);
              }
              else
              {
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.ic_baseline_pause_24);
              }
          }
        });
    }

    private void GetDataFromItent() {
      Intent intent = getIntent();
      baihatArrayList.clear();
      if(intent != null){
        if(intent.hasExtra("cakhuc")){
          Baihat baihat = intent.getParcelableExtra("cakhuc");
          baihatArrayList.add(baihat);
        }
        if(intent.hasExtra("cacbaihat")){
          ArrayList<Baihat> baihatArr = intent.getParcelableExtra("cacbaihat");
          baihatArrayList = baihatArr;
        }
      }
    }


}
