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
import com.example.doan_android.Fragment.MusicPlayerFragment;
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

    Song song;
    ArrayList<Song> listSong = new ArrayList<>();
    AdapterPlayerMusic adapterPlayerMusic = new AdapterPlayerMusic(getSupportFragmentManager(),
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

    TextView txtSongName, txtArtistName, txtTimeSong, txtTotalTimeSong;
    SeekBar barTime;
    ImageView imvPlayPre, imvPlay, imvPlayNext, imvMusicPlayer;
    Button btnBack;
    MusicPlayerFragment musicPlayerFragment;

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
        if (HomeActivity.mediaPlayer.isPlaying() && HomeActivity.mediaPlayer != null) {
            HomeActivity.mediaPlayer.stop();
            HomeActivity.mediaPlayer.release();
            HomeActivity.mediaPlayer = null;
        }
        //txtMusicTenBaiHat.setText(baihatArrayList.get(0).getTenBaihat());
        //System.out.print( "LOG: " + baihatArrayList.get(0).getTenBaihat());
        if (listSong.size() > 0) {
            //musicplayer.imageURL=baihatArrayList.get(0).getHinhBaihat();
            Picasso.get().load(listSong.get(0).getHinhBaihat()).into(imvMusicPlayer);
            txtSongName.setText(listSong.get(0).getTenBaihat());
            txtArtistName.setText(listSong.get(0).getTenCasi());
            new PlayMp3File().execute(listSong.get(0).getUrlBaihat());
            imvPlay.setImageResource(R.drawable.ic_baseline_pause_24);
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

    private void LinkViews() {
        //tabPlayerMusic = findViewById(R.id.tabPlayerMusic);
        //view_pager_music = findViewById(R.id.view_pager_music);
        //view_pager_music.setAdapter(adaptermusic);
        //tabPlayerMusic.setupWithViewPager(view_pager_music);
        btnBack = findViewById(R.id.btnBack);
        barTime = findViewById(R.id.barTime);
        musicPlayerFragment = (MusicPlayerFragment) adapterPlayerMusic.getItem(0);

        txtSongName = findViewById(R.id.txtSongName);
        txtArtistName = findViewById(R.id.txtArtistName);
        txtTimeSong = findViewById(R.id.txtTimeSong);
        txtTotalTimeSong = findViewById(R.id.txtTotalTimeSong);

        imvPlay = findViewById(R.id.imvPlay);
        imvPlayPre = findViewById(R.id.imvPlayPre);
        imvPlayNext = findViewById(R.id.imvPlayNext);
        imvMusicPlayer = findViewById(R.id.imgMusicPlayer);

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
            /*if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
                System.out.println("co du lieu");
            }
            else
            {
                System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkk");
            }*/
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

    private void UpdateTime() {
        Handler handlerFirst = new Handler();
        handlerFirst.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (HomeActivity.mediaPlayer != null) {
                    barTime.setProgress(HomeActivity.mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimeSong.setText(simpleDateFormat.format(HomeActivity.mediaPlayer.getCurrentPosition()));
                    handlerFirst.postDelayed(this, 1000);
                    HomeActivity.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                        }
                    });
                }
            }
        }, 1000);
        Handler handlerSecond = new Handler();
        handlerSecond.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (position < (listSong.size())) {
                        imvPlay.setImageResource(R.drawable.ic_baseline_pause_24);
                        position++;
                        if (position > (listSong.size() - 1)) {
                            position = 0;
                        }
                        new PlayMp3File().execute(listSong.get(position).getUrlBaihat());
                        //musicplayer.imageURL = (baihatArrayList.get(position).getHinhBaihat());
                        //NEXT LOG
                        Picasso.get().load(listSong.get(position).getHinhBaihat()).into(imvMusicPlayer);
                        System.out.println("NEXT LOG: " + listSong.get(position).getHinhBaihat());
                        txtSongName.setText(listSong.get(position).getTenBaihat());
                        txtArtistName.setText(listSong.get(position).getTenCasi());
                        //musicplayer.setRefreshing

                    }
                    imvPlayPre.setClickable(false);
                    imvPlayNext.setClickable(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imvPlayPre.setClickable(true);
                            imvPlayNext.setClickable(true);
                        }
                    }, 1000);
                    next = false;
                } else {
                    handlerSecond.postDelayed(this, 1000);
                }

            }
        }, 1000);
    }

    private void GetData() {
        Intent intent = getIntent();
        listSong.clear();
        if (intent != null) {
            if (intent.hasExtra("song")) {
                song = (Song) intent.getParcelableExtra("song");
                listSong.add(song);
            }
            if (intent.hasExtra("listSong")) {
                ArrayList<Song> songs = intent.getParcelableArrayListExtra("listSong");
                for (Song item : songs) {
                    // tất cả các bài hát được add vào bài hát array list
                    listSong.add(item);
                }
            }
        }
    }

    private void EventClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapterPlayerMusic.getItem(1) != null) {
                    if (listSong.size() > 0) {
                        //musicplayer.imageURL=baihatArrayList.get(0).getHinhBaihat();
                        Picasso.get().load(listSong.get(position).getHinhBaihat()).into(imvMusicPlayer);
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);
        imvPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (HomeActivity.mediaPlayer.isPlaying()) {
                    HomeActivity.mediaPlayer.pause();
                    imvPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                } else {
                    HomeActivity.mediaPlayer.start();
                    imvPlay.setImageResource(R.drawable.ic_baseline_pause_24);
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

        imvPlayNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listSong.size() > 0) {
                    if (HomeActivity.mediaPlayer.isPlaying() || HomeActivity.mediaPlayer != null) {
                        HomeActivity.mediaPlayer.stop();
                        HomeActivity.mediaPlayer.release();
                        HomeActivity.mediaPlayer = null;
                    }
                    if (position < (listSong.size())) {
                        imvPlay.setImageResource(R.drawable.ic_baseline_pause_24);
                        position++;
                        if (position > (listSong.size() - 1)) {
                            position = 0;
                        }
                        new PlayMp3File().execute(listSong.get(position).getUrlBaihat());
                        musicPlayerFragment.imageURL = (listSong.get(position).getHinhBaihat());
                        //NEXT LOG
                        //System.out.println("NEXT LOG: " + baihatArrayList.get(position).getHinhBaihat());
                        Picasso.get().load(listSong.get(position).getHinhBaihat()).into(imvMusicPlayer);
                        txtSongName.setText(listSong.get(position).getTenBaihat());
                        txtArtistName.setText(listSong.get(position).getTenCasi());
                        UpdateTime();
                        //musicplayer.setRefreshing
                    }
                }
                imvPlayPre.setClickable(false);
                imvPlayNext.setClickable(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imvPlayPre.setClickable(true);
                        imvPlayNext.setClickable(true);
                    }
                }, 1000);
            }
        });

        //PRE
        imvPlayPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listSong.size() > 0) {
                    if (HomeActivity.mediaPlayer.isPlaying() || HomeActivity.mediaPlayer != null) {
                        HomeActivity.mediaPlayer.stop();
                        HomeActivity.mediaPlayer.release();
                        HomeActivity.mediaPlayer = null;
                    }
                    if (position < (listSong.size())) {
                        imvPlay.setImageResource(R.drawable.ic_baseline_pause_24);
                        position--;

                        if (position < 0) {
                            position = listSong.size() - 1;
                        }

                        new PlayMp3File().execute(listSong.get(position).getUrlBaihat());
                        //musicplayer.imageURL = (baihatArrayList.get(position).getHinhBaihat());
                        Picasso.get().load(listSong.get(position).getHinhBaihat()).into(imvMusicPlayer);
                        txtSongName.setText(listSong.get(position).getTenBaihat());
                        txtArtistName.setText(listSong.get(position).getTenCasi());
                        UpdateTime();
                    }
                }
                imvPlayPre.setClickable(false);
                imvPlayNext.setClickable(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imvPlayPre.setClickable(true);
                        imvPlayNext.setClickable(true);
                    }
                }, 1000);

            }
        });

    }
}
