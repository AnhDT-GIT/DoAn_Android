package com.example.doan_android.Fragment;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_android.R;


public class music_player extends Fragment {

      public music_player(){

      }

      View view;
      ImageView imgMusicPlayer;
      TextView txtTimeSong, txtTotalTimeSong;
      SeekBar barTime;
      MediaPlayer mediaPlayer;
      Handler handler = new Handler();
      ImageView btnPre, btnPlay, btnNext;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public music_player() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment music_player.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static music_player newInstance(String param1, String param2) {
//        music_player fragment = new music_player();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_music_player, container, false);
//        imgMusicPlayer = view.findViewById(R.id.imgMusicPlayer);
//        btnPlay = view.findViewById(R.id.btnPlay);
//        imgMusicPlayer = view.findViewById(R.id.imgMusicPlayer);
//        txtTimeSong = view.findViewById(R.id.txtTimeSong);
//        txtTotalTimeSong = view.findViewById(R.id.txtTotalTimeSong);
//        barTime = view.findViewById(R.id.barTime);
//        mediaPlayer = new MediaPlayer();
//        barTime.setMax(100);
//
//        btnPlay.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View view) {
//            if(mediaPlayer.isPlaying()){
//              handler.removeCallbacks(updater);
//              mediaPlayer.pause();
//              btnPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24);
//            }
//            else
//            {
//              mediaPlayer.start();
//              btnPlay.setImageResource(R.drawable.ic_baseline_pause_24);
//              updateTimeBar();
//            }
//          }
//        });
//
//        linkforMediaPlayer();
//        //return inflater.inflate(R.layout.fragment_music_player, container, false);
          return  view;
    }

//    private void linkforMediaPlayer(){
//      try {
//        mediaPlayer.setDataSource("https://tranquochung1711061818.000webhostapp.com/nhac/NeuMaiNayXaNhau-HoaMinzy-6891719.mp3");
//        mediaPlayer.prepare();
//        txtTotalTimeSong.setText(secondToTimer(mediaPlayer.getDuration()));
//      }
//      catch (Exception exception){
//
//      }
//    }
//
//    private Runnable updater = new Runnable() {
//      @Override
//      public void run() {
//        updateTimeBar();
//        long currentDuration = mediaPlayer.getCurrentPosition();
//        txtTimeSong.setText(secondToTimer(currentDuration));
//      }
//    };
//
//    private void updateTimeBar(){
//      if(mediaPlayer.isPlaying()){
//        barTime.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
//        handler.postDelayed(updater, 1000);
//      }
//    }
//
//    private String secondToTimer(long second){
//        String timerString = "";
//        String secondString;
//
//        int h = (int) (second / (1000 * 60 * 60));
//        int m = (int) (second % (1000 * 60 * 60)) / (1000 * 60);
//        int s = (int) ((second % (1000 * 60 * 60)) % (1000 * 60) / 1000);
//
//        if (h > 0){
//          timerString = h + ":";
//        }
//
//        if (s < 10){
//          secondString = "0" + s;
//        }
//        else
//        {
//          secondString = "" + s;
//        }
//
//        timerString = timerString + m + ":" + secondString;
//        return timerString;
//    }
}
