package com.example.doan_android.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.doan_android.Adapter.AdapterPlayerMusic;
import com.example.doan_android.Fragment.music_player;
import com.example.doan_android.Model.Song;
import com.example.doan_android.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlayMusicActivity extends AppCompatActivity {


    //Add view
    //private TabLayout tabPlayerMusic;
    //private ViewPager view_pager_music;
    //private AdapterSong adapterSong;
    //public static ArrayList<Baihat> baihatArrayList = new ArrayList<>();
    //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //AdapterPlayerMusic adapterPlayerMusic;

    ArrayList<Song> listSong = new ArrayList<>();
    AdapterPlayerMusic adapterPlayerMusic = new AdapterPlayerMusic(getSupportFragmentManager(),
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

    Song song;

    TextView txtSongName, txtArtistName, txtTimeSong, txtTotalTimeSong;
    SeekBar barTime;
    ImageView btnPlayPre, btnPlay, btnPlayNext, imgMusicPlayer;
    Button btnBack;
    music_player musicplayer;

    boolean next = false;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        //Init
        LinkViews();
        EventClick();
        GetData();
        if(HomeActivity.mediaPlayer.isPlaying() && HomeActivity.mediaPlayer!=null)
        {
            HomeActivity.mediaPlayer.stop();
            HomeActivity.mediaPlayer.release();
            HomeActivity.mediaPlayer=null;
        }

        //txtMusicTenBaiHat.setText(baihatArrayList.get(0).getTenBaihat());
        //System.out.print( "LOG: " + baihatArrayList.get(0).getTenBaihat());
        if (listSong.size() > 0){
            //musicplayer.imageURL=baihatArrayList.get(0).getHinhBaihat();
            Picasso.get().load(listSong.get(0).getHinhBaihat()).into(imgMusicPlayer);
            txtSongName.setText(listSong.get(0).getTenBaihat());
            txtArtistName.setText(listSong.get(0).getTenCasi());
            new PlayMp3File().execute(listSong.get(0).getUrlBaihat());
            btnPlay.setImageResource(R.drawable.ic_baseline_pause_24);
        }

        //BACK
        btnBack.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(PlayMusicActivity.this, HomeActivity.class);
              startActivity(intent);
          }
        });



    }

    private void LinkViews(){
        //tabPlayerMusic = findViewById(R.id.tabPlayerMusic);
        //view_pager_music = findViewById(R.id.view_pager_music);
        btnBack = findViewById(R.id.btnBack);
        txtSongName = findViewById(R.id.txtMusicTenBaiHat);
        txtArtistName = findViewById(R.id.txtMusicTenNgheSi);
        txtTimeSong = findViewById(R.id.txtTimeSong);
        txtTotalTimeSong = findViewById(R.id.txtTotalTimeSong);
        btnPlay = findViewById(R.id.btnPlay);
        btnPlayPre = findViewById(R.id.btnPlayPre);
        btnPlayNext = findViewById(R.id.btnPlayNext);
        barTime = findViewById(R.id.barTime);
        imgMusicPlayer = findViewById(R.id.imgMusicPlayer);
        //view_pager_music.setAdapter(adaptermusic);
        //tabPlayerMusic.setupWithViewPager(view_pager_music);
        musicplayer = (music_player) adapterPlayerMusic.getItem(0);

        //Set icon
        //tabPlayerMusic.getTabAt(0).setIcon(R.drawable.ic_baseline_music_note_24).select();
        //tabPlayerMusic.getTabAt(1).setIcon(R.drawable.ic_baseline_text_fields_24);
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
                  HomeActivity.mediaPlayer = new MediaPlayer();
                  HomeActivity.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                  HomeActivity.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                      @Override
                      public void onCompletion(MediaPlayer mp) {
                          HomeActivity.mediaPlayer.stop();
                          HomeActivity.mediaPlayer.reset();
                      }
                  });
                  HomeActivity.mediaPlayer.setDataSource(baihat);
                  HomeActivity.mediaPlayer.prepare();
              } catch (IOException e) {
                  e.printStackTrace();
              }
//              if(mediaPlayer.isPlaying()){
//                  mediaPlayer.stop();
//                  System.out.println("co du lieu");
//              }
//              else
//              {
//                  System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkk");
//              }
                HomeActivity.mediaPlayer.start();
                TimeSong();
                UpdateTime();
          }
      }

      private void TimeSong() {
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
          txtTotalTimeSong.setText(simpleDateFormat.format(HomeActivity.mediaPlayer.getDuration()));
          barTime.setMax(HomeActivity.mediaPlayer.getDuration());
      }

      private void UpdateTime(){
          Handler handler = new Handler();
          handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (HomeActivity.mediaPlayer != null){
                    barTime.setProgress(HomeActivity.mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimeSong.setText(simpleDateFormat.format(HomeActivity.mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 1000);
                    HomeActivity.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                      @Override
                      public void onCompletion(MediaPlayer mp) {
                          next = true;
                      }
                    });
                }
            }
          },1000);
          Handler handler1 = new Handler();
          handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
              if (next == true){
                if(position < (listSong.size())){
                  btnPlay.setImageResource(R.drawable.ic_baseline_pause_24);
                  position++;
                  if(position > (listSong.size() - 1)){
                    position = 0;
                  }
                  new PlayMp3File().execute(listSong.get(position).getUrlBaihat());
                  //musicplayer.imageURL = (baihatArrayList.get(position).getHinhBaihat());
                  //NEXT LOG
                  Picasso.get().load(listSong.get(position).getHinhBaihat()).into(imgMusicPlayer);
                  System.out.println("NEXT LOG: " + listSong.get(position).getHinhBaihat());
                  txtSongName.setText(listSong.get(position).getTenBaihat());
                  txtArtistName.setText(listSong.get(position).getTenCasi());
                  //musicplayer.setRefreshing

                }
              btnPlayPre.setClickable(false);
              btnPlayNext.setClickable(false);
              Handler handler1 = new Handler();
              handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                  btnPlayPre.setClickable(true);
                  btnPlayNext.setClickable(true);
                }
              }, 1000);
              next = false;
              }
              else{
                  handler1.postDelayed(this, 1000);
              }

            }
          }, 1000);
      }

    private void GetData() {
        Intent intent = getIntent();
        listSong.clear();
        if (intent != null) {
            if (intent.hasExtra("baihats")) {
                song = (Song) intent.getParcelableExtra("baihats");
                listSong.add(song);
            }
            if (intent.hasExtra("danhsach") ) {
                ArrayList<Song> mangbaihat= intent.getParcelableArrayListExtra("danhsach");
                for( Song item : mangbaihat)
                {
                    // tất cả các bài hát được aadd vào bài hát array liostK
                     listSong.add(item);
                }
            }
        }
    }

    private void EventClick(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
          @Override
          public void run() {
              if (adapterPlayerMusic.getItem(1) != null){
                if(listSong.size() > 0){
                    //musicplayer.imageURL=baihatArrayList.get(0).getHinhBaihat();
                    Picasso.get().load(listSong.get(position).getHinhBaihat()).into(imgMusicPlayer);
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
              if(HomeActivity.mediaPlayer.isPlaying()){
                  HomeActivity.mediaPlayer.pause();
                btnPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24);
              }
              else
              {
                  HomeActivity.mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.ic_baseline_pause_24);
              }
          }
        });
        barTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
          @Override
          public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

          }

          @Override
          public void onStartTrackingTouch(SeekBar seekBar) {

          }

          @Override
          public void onStopTrackingTouch(SeekBar seekBar) {
              HomeActivity.mediaPlayer.seekTo(seekBar.getProgress());
          }
        });

        btnPlayNext.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
                if(listSong.size() > 0){
                    if(HomeActivity.mediaPlayer.isPlaying() || HomeActivity.mediaPlayer != null){
                        HomeActivity.mediaPlayer.stop();
                        HomeActivity.mediaPlayer.release();
                        HomeActivity.mediaPlayer = null;
                    }
                    if(position < (listSong.size())){
                        btnPlay.setImageResource(R.drawable.ic_baseline_pause_24);
                        position++;
                        if(position > (listSong.size() - 1)){
                          position = 0;
                        }
                        new PlayMp3File().execute(listSong.get(position).getUrlBaihat());
                        musicplayer.imageURL = (listSong.get(position).getHinhBaihat());
                        //NEXT LOG
                        //System.out.println("NEXT LOG: " + baihatArrayList.get(position).getHinhBaihat());
                        Picasso.get().load(listSong.get(position).getHinhBaihat()).into(imgMusicPlayer);
                        txtSongName.setText(listSong.get(position).getTenBaihat());
                        txtArtistName.setText(listSong.get(position).getTenCasi());
                        UpdateTime();
                        //musicplayer.setRefreshing
                    }
                }
                btnPlayPre.setClickable(false);
                btnPlayNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                  @Override
                  public void run() {
                    btnPlayPre.setClickable(true);
                    btnPlayNext.setClickable(true);
                  }
                }, 1000);
          }
        });

        //PRE
        btnPlayPre.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            if(listSong.size() > 0){
              if(HomeActivity.mediaPlayer.isPlaying() || HomeActivity.mediaPlayer != null){
                  HomeActivity.mediaPlayer.stop();
                  HomeActivity.mediaPlayer.release();
                  HomeActivity.mediaPlayer = null;
              }
              if(position < (listSong.size())){
                btnPlay.setImageResource(R.drawable.ic_baseline_pause_24);
                position--;

                if(position < 0){
                  position = listSong.size() - 1;
                }

                new PlayMp3File().execute(listSong.get(position).getUrlBaihat());
                //musicplayer.imageURL = (baihatArrayList.get(position).getHinhBaihat());
                Picasso.get().load(listSong.get(position).getHinhBaihat()).into(imgMusicPlayer);
                txtSongName.setText(listSong.get(position).getTenBaihat());
                txtArtistName.setText(listSong.get(position).getTenCasi());
                UpdateTime();
              }
            }
            btnPlayPre.setClickable(false);
            btnPlayNext.setClickable(false);
            Handler handler1 = new Handler();
            handler1.postDelayed(new Runnable() {
              @Override
              public void run() {
                btnPlayPre.setClickable(true);
                btnPlayNext.setClickable(true);
              }
            }, 1000);

          }
        });

    }
}
