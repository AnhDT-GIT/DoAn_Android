package com.example.doan_android.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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

    AdapterPlayerMusic adapterPlayerMusic = new AdapterPlayerMusic(getSupportFragmentManager(),
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

    TextView txtSongName, txtArtistName, txtTimeSong, txtTotalTimeSong;
    SeekBar barTime;
    ImageView imvPlayPre, imvPlay, imvPlayNext, imvMusicPlayer;
    Button btnBack;
    MusicPlayerFragment musicPlayerFragment;

    boolean next = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        //Init
        LinkViews();
        EventClick();
        if(BackgroundActivity.check == 0){
            BackgroundActivity.position=0;
            if (BackgroundActivity.mediaPlayer.isPlaying() && BackgroundActivity.mediaPlayer != null) {
                BackgroundActivity.mediaPlayer.stop();
                BackgroundActivity.mediaPlayer.release();
                BackgroundActivity.mediaPlayer = null;
            }
            GetData();
            if (BackgroundActivity.listSong.size() > 0) {

                //musicplayer.imageURL=baihatArrayList.get(0).getHinhBaihat();
                Picasso.get().load(BackgroundActivity.listSong.get(0).getHinhBaihat()).into(imvMusicPlayer);
                txtSongName.setText(BackgroundActivity.listSong.get(0).getTenBaihat());
                txtArtistName.setText(BackgroundActivity.listSong.get(0).getTenCasi());
                new PlayMp3File().execute(BackgroundActivity.listSong.get(0).getUrlBaihat());
                imvPlay.setImageResource(R.drawable.ic_baseline_pause_24);
            }
        } else{
            Picasso.get().load(BackgroundActivity.listSong.get(BackgroundActivity.position).getHinhBaihat()).into(imvMusicPlayer);
            txtSongName.setText(BackgroundActivity.listSong.get(BackgroundActivity.position).getTenBaihat());
            txtArtistName.setText(BackgroundActivity.listSong.get(BackgroundActivity.position).getTenCasi());;
            imvPlay.setImageResource(R.drawable.ic_baseline_pause_24);
        }

        //txtMusicTenBaiHat.setText(baihatArrayList.get(0).getTenBaihat());
        //System.out.print( "LOG: " + baihatArrayList.get(0).getTenBaihat());


        //BACK
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayMusicActivity.this, BackgroundActivity.class);
                startActivity(intent);
            }
        });

        BackgroundActivity.check = 0;

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


    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotalTimeSong.setText(simpleDateFormat.format(BackgroundActivity.mediaPlayer.getDuration()));
        barTime.setMax(BackgroundActivity.mediaPlayer.getDuration());
    }

    private void UpdateTime() {
        Handler handlerFirst = new Handler();
        handlerFirst.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (BackgroundActivity.mediaPlayer != null) {
                    barTime.setProgress(BackgroundActivity.mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimeSong.setText(simpleDateFormat.format(BackgroundActivity.mediaPlayer.getCurrentPosition()));
                    handlerFirst.postDelayed(this, 1000);
                    BackgroundActivity.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
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
                    if (BackgroundActivity.position < (BackgroundActivity.listSong.size())) {
                        imvPlay.setImageResource(R.drawable.ic_baseline_pause_24);
                        BackgroundActivity.position++;
                        if (BackgroundActivity.position > (BackgroundActivity.listSong.size() - 1)) {
                            BackgroundActivity.position = 0;
                        }
                        new PlayMp3File().execute(BackgroundActivity.listSong.get(BackgroundActivity.position).getUrlBaihat());
                        //musicplayer.imageURL = (baihatArrayList.get(position).getHinhBaihat());
                        //NEXT LOG
                        Picasso.get().load(BackgroundActivity.listSong.get(BackgroundActivity.position).getHinhBaihat()).into(imvMusicPlayer);
                        txtSongName.setText(BackgroundActivity.listSong.get(BackgroundActivity.position).getTenBaihat());
                        txtArtistName.setText(BackgroundActivity.listSong.get(BackgroundActivity.position).getTenCasi());
//                        musicplayer.setRefreshing

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
        BackgroundActivity.listSong.clear();
        if (intent != null) {
            if (intent.hasExtra("song")) {
                song = (Song) intent.getParcelableExtra("song");
                BackgroundActivity.listSong.add(song);
            }
            if (intent.hasExtra("listSong")) {
                ArrayList<Song> songs = intent.getParcelableArrayListExtra("listSong");
                for (Song item : songs) {
                    // tất cả các bài hát được add vào bài hát array list
                    BackgroundActivity.listSong.add(item);
                }
            }
        }
    }

    public class PlayMp3File extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String song) {
            super.onPostExecute(song);
            try {
                BackgroundActivity.mediaPlayer = new MediaPlayer();
                BackgroundActivity.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                BackgroundActivity.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        BackgroundActivity.mediaPlayer.stop();
                        BackgroundActivity.mediaPlayer.reset();
                    }
                });
                BackgroundActivity.mediaPlayer.setDataSource(song);
                BackgroundActivity.mediaPlayer.prepare();
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
            BackgroundActivity.mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    public static class PlayMp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String song) {
            super.onPostExecute(song);
            try {
                BackgroundActivity.mediaPlayer = new MediaPlayer();
                BackgroundActivity.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                BackgroundActivity.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        BackgroundActivity.mediaPlayer.stop();
                        BackgroundActivity.mediaPlayer.reset();
                    }
                });
                BackgroundActivity.mediaPlayer.setDataSource(song);
                BackgroundActivity.mediaPlayer.prepare();
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
            BackgroundActivity.mediaPlayer.start();

        }

    }

    private void EventClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapterPlayerMusic.getItem(1) != null) {
                    if (BackgroundActivity.listSong.size() > 0) {
                        //musicplayer.imageURL=baihatArrayList.get(0).getHinhBaihat();
                        Picasso.get().load(BackgroundActivity.listSong.get(BackgroundActivity.position).getHinhBaihat()).into(imvMusicPlayer);
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
                if (BackgroundActivity.mediaPlayer.isPlaying()) {
                    BackgroundActivity.mediaPlayer.pause();
                    imvPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                } else {
                    BackgroundActivity.mediaPlayer.start();
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
                BackgroundActivity.mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        imvPlayNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (BackgroundActivity.listSong.size() > 0) {
                    if (BackgroundActivity.mediaPlayer.isPlaying() || BackgroundActivity.mediaPlayer != null) {
                        BackgroundActivity.mediaPlayer.stop();
                        BackgroundActivity.mediaPlayer.release();
                        BackgroundActivity.mediaPlayer = null;
                    }
                    if (BackgroundActivity.position < (BackgroundActivity.listSong.size())) {
                        imvPlay.setImageResource(R.drawable.ic_baseline_pause_24);
                        BackgroundActivity.position++;
                        if (BackgroundActivity.position > (BackgroundActivity.listSong.size() - 1)) {
                            BackgroundActivity.position = 0;
                        }
                        new PlayMp3File().execute(BackgroundActivity.listSong.get(BackgroundActivity.position).getUrlBaihat());
                        musicPlayerFragment.imageURL = (BackgroundActivity.listSong.get(BackgroundActivity.position).getHinhBaihat());
                        //NEXT LOG
                        //System.out.println("NEXT LOG: " + baihatArrayList.get(position).getHinhBaihat());
                        Picasso.get().load(BackgroundActivity.listSong.get(BackgroundActivity.position).getHinhBaihat()).into(imvMusicPlayer);
                        txtSongName.setText(BackgroundActivity.listSong.get(BackgroundActivity.position).getTenBaihat());
                        txtArtistName.setText(BackgroundActivity.listSong.get(BackgroundActivity.position).getTenCasi());
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
                if (BackgroundActivity.listSong.size() > 0) {
                    if (BackgroundActivity.mediaPlayer.isPlaying() || BackgroundActivity.mediaPlayer != null) {
                        BackgroundActivity.mediaPlayer.stop();
                        BackgroundActivity.mediaPlayer.release();
                        BackgroundActivity.mediaPlayer = null;
                    }
                    if (BackgroundActivity.position < (BackgroundActivity.listSong.size())) {
                        imvPlay.setImageResource(R.drawable.ic_baseline_pause_24);
                        BackgroundActivity.position--;

                        if (BackgroundActivity.position < 0) {
                            BackgroundActivity.position = BackgroundActivity.listSong.size() - 1;
                        }

                        new PlayMp3File().execute(BackgroundActivity.listSong.get(BackgroundActivity.position).getUrlBaihat());
                        //musicplayer.imageURL = (baihatArrayList.get(position).getHinhBaihat());
                        Picasso.get().load(BackgroundActivity.listSong.get(BackgroundActivity.position).getHinhBaihat()).into(imvMusicPlayer);
                        txtSongName.setText(BackgroundActivity.listSong.get(BackgroundActivity.position).getTenBaihat());
                        txtArtistName.setText(BackgroundActivity.listSong.get(BackgroundActivity.position).getTenCasi());
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

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("PlayMusic", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("PlayMusic", "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("PlayMusic", "onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("PlayMusic", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("PlayMusic", "onStop");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("PlayMusic", "onRestart");
    }

}
