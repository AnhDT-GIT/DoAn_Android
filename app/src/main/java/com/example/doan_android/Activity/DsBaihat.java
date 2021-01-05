package com.example.doan_android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.doan_android.Model.Playlist;
import com.example.doan_android.R;

public class DsBaihat extends AppCompatActivity {
    Playlist playlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_baihat);
        Getdata();
    }

    private void Getdata() {
        Intent intent=getIntent();
        if(intent !=null)
        {
            if(intent.hasExtra("playlist"))
            {
                playlist= (Playlist) intent.getSerializableExtra("playlist");
                Toast.makeText(this, playlist.getTenPlaylist(),Toast.LENGTH_LONG).show();
            }
        }
    }

}