package com.example.doan_android.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_android.Adapter.AdapterBanner;
import com.example.doan_android.Adapter.AdapterSong;
import com.example.doan_android.Model.Album;
import com.example.doan_android.Model.Baihat;
import com.example.doan_android.Model.Banner;
import com.example.doan_android.Model.Playlist;
import com.example.doan_android.R;
import com.example.doan_android.Service.APIService;
import com.example.doan_android.Service.Dataservice;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DsBaihat extends AppCompatActivity {

  Playlist playlist;
  Album album;
  Banner banner;
  ArrayList<Baihat> baihatArrayList;
  ArrayList<Album> albumArrayList;
  ArrayList<Banner> bannerArrayList;
  ArrayAdapter<Baihat> arrayAdapter;
  RecyclerView lvListSongs;
  AdapterSong adapterSong;
  Button playall;
    String text;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ds_baihat);
    Getdata();
    lvListSongs = findViewById(R.id.lvListSongs);
    playall= findViewById(R.id.btnPlayAll);
    if(playlist != null )
    {
      GetdataPlaylist(playlist.getIdPlaylist());
    }
      if(album != null )
      {
         GetdataAlbum(album.getIdAlbum());
      }
      if(banner != null )
      {
          GetdataBanner(banner.getIdBanner());
      }
      if( text !=null)
      {
          GetdataTimkiem(text);
      }
     playall.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent= new Intent(DsBaihat.this, play_music.class);
             intent.putParcelableArrayListExtra("danhsach",baihatArrayList);
             startActivity(intent);
         }
     });
  }

    private void GetdataTimkiem(String timkiem) {
        Dataservice dataservice= APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetTimkiem(timkiem);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                baihatArrayList=(ArrayList<Baihat>) response.body();
                adapterSong = new AdapterSong(DsBaihat.this, baihatArrayList);
                lvListSongs.setLayoutManager(new LinearLayoutManager(DsBaihat.this));
                lvListSongs.setAdapter(adapterSong);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void GetdataPlaylist(String id_playlist) {
    Dataservice dataservice= APIService.getService();
    Call<List<Baihat>> callback = dataservice.GetDataBaihat(id_playlist);
    callback.enqueue(new Callback<List<Baihat>>() {
      @Override
      public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
        baihatArrayList=(ArrayList<Baihat>) response.body();
        adapterSong = new AdapterSong(DsBaihat.this, baihatArrayList);
        lvListSongs.setLayoutManager(new LinearLayoutManager(DsBaihat.this));
        lvListSongs.setAdapter(adapterSong);
      }

      @Override
      public void onFailure(Call<List<Baihat>> call, Throwable t) {

      }
    });
  }
  private void GetdataAlbum(String id_albums) {
    Dataservice dataservice= APIService.getService();
    Call<List<Baihat>> callback= dataservice.GetDataAlbum(id_albums);
    callback.enqueue(new Callback<List<Baihat>>() {
      @Override
      public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
       baihatArrayList= (ArrayList<Baihat>) response.body();
          adapterSong = new AdapterSong(DsBaihat.this, baihatArrayList);
          lvListSongs.setLayoutManager(new LinearLayoutManager(DsBaihat.this));
          lvListSongs.setAdapter(adapterSong);
     }

      @Override
      public void onFailure(Call<List<Baihat>> call, Throwable t) {

     }
   });
  }
  private void GetdataBanner(String id_banners) {
    Dataservice dataservice= APIService.getService();
    Call<List<Baihat>> callback= dataservice.GetDataBanner(id_banners);
        callback.enqueue(new Callback<List<Baihat>>() {
      @Override
     public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
          baihatArrayList= (ArrayList<Baihat>) response.body();
          adapterSong = new AdapterSong(DsBaihat.this, baihatArrayList);
          lvListSongs.setLayoutManager(new LinearLayoutManager(DsBaihat.this));
          lvListSongs.setAdapter(adapterSong);
      }

      @Override
     public void onFailure(Call<List<Baihat>> call, Throwable t) {

     }
    });
  }

  private void Getdata() {
    Intent intent=getIntent();
    if(intent !=null)
    {
      if(intent.hasExtra("playlist"))
      {
        playlist= (Playlist) intent.getSerializableExtra("playlist");
      }
      else if (intent.hasExtra("album"))
      {
        album= (Album)  intent.getSerializableExtra("album");
      }
      else
      {
        banner= (Banner)  intent.getSerializableExtra("banner");
      }
        text=intent.getStringExtra("text");
    }
  }
}
