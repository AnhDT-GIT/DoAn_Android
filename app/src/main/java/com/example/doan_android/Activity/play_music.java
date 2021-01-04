package com.example.doan_android.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.loader.content.AsyncTaskLoader;
import androidx.viewpager.widget.ViewPager;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.doan_android.Adapter.AdapterPlayerMusic;
import com.example.doan_android.R;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class play_music extends AppCompatActivity {


    //Add view
    private TabLayout tabPlayerMusic;
    private ViewPager view_pager_music;

    TextView txtMusicTenBaiHat, txtMusicTenNgheSi, txtMusic, txtTimeSong, txtTotalTimeSong;
    SeekBar barTime;
    ImageButton btnPlayShuffle, btnPlayPre, btnPlay, btnPlayNext, btnPlayLoop;
    MediaPlayer mediaPlayer;

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
      tabPlayerMusic.getTabAt(0).setIcon(R.drawable.ic_baseline_list_alt_24);
      tabPlayerMusic.getTabAt(1).setIcon(R.drawable.ic_baseline_music_note_24).select();
      tabPlayerMusic.getTabAt(2).setIcon(R.drawable.ic_baseline_text_fields_24);

    }

    class PlayMp3 extends AsyncTask<String, Void, String> {

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
        }
        catch (IOException e) {
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

}
