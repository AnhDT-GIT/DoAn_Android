package com.example.doan_android.Fragment;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_android.Adapter.AdapterSong;
import com.example.doan_android.Model.Baihat;
import com.example.doan_android.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class music_player extends Fragment {

      public music_player(){
        // Required empty public constructor
      }

      View view;
      ImageView imgMusicPlayer;
      public static String imageURL;
//      public void loadImage(String urlImage) {
//        Picasso.get().load(urlImage).into(imgMusicPlayer);
//      }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_music_player, container, false);
        imgMusicPlayer = view.findViewById(R.id.imgMusicPlayer);
        //loadImage();
        Picasso.get().load(imageURL).into(imgMusicPlayer);
        System.out.println("LOG IMAGE 2: " + imageURL);
        return  view;
    }

}
