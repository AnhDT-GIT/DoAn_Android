package com.example.doan_android.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.doan_android.R;
import com.squareup.picasso.Picasso;


public class MusicPlayerFragment extends Fragment {

    public MusicPlayerFragment() {
    }

    View view;
    ImageView imgMusicPlayer;
    public static String imageURL;
    /*public void loadImage(String urlImage) {
        Picasso.get().load(urlImage).into(imgMusicPlayer);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_music_player, container, false);
        imgMusicPlayer = view.findViewById(R.id.imgMusicPlayer);
        //loadImage();
        Picasso.get().load(imageURL).into(imgMusicPlayer);
        return view;
    }

}
