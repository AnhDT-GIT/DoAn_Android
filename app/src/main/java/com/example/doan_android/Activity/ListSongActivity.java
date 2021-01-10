package com.example.doan_android.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.doan_android.Adapter.AdapterSong;
import com.example.doan_android.Model.Album;
import com.example.doan_android.Model.Song;
import com.example.doan_android.Model.Banner;
import com.example.doan_android.Model.Playlist;
import com.example.doan_android.R;
import com.example.doan_android.Service.APIService;
import com.example.doan_android.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSongActivity extends AppCompatActivity {

    Playlist playlist;
    Album album;
    Banner banner;
    Button btnPlayAll;

    ArrayList<Song> listSong;
    AdapterSong adapterSong;
    RecyclerView rcvListSong;

    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);
        getData();

        rcvListSong = findViewById(R.id.rcvListSong);
        btnPlayAll = findViewById(R.id.btnPlayAll);

        if (playlist != null) {
            getDataPlaylist(playlist.getIdPlaylist());
        }
        if (album != null) {
            getDataAlbum(album.getIdAlbum());
        }
        if (banner != null) {
            getDataBanner(banner.getIdBanner());
        }
        if (text != null) {
            getDataSearch(text);
        }
        btnPlayAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListSongActivity.this,
                        PlayMusicActivity.class);
                intent.putParcelableArrayListExtra("listSong", listSong);
                startActivity(intent);
            }
        });
    }

    private void getDataSearch(String search) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callBack = dataService.GetTimkiem(search);
        callBack.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                listSong = (ArrayList<Song>) response.body();
                adapterSong = new AdapterSong(ListSongActivity.this, listSong);
                rcvListSong.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                rcvListSong.setAdapter(adapterSong);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void getDataPlaylist(String playlistId) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callBack = dataService.GetDataBaihat(playlistId);
        callBack.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                listSong = (ArrayList<Song>) response.body();
                adapterSong = new AdapterSong(ListSongActivity.this, listSong);
                rcvListSong.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                rcvListSong.setAdapter(adapterSong);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void getDataAlbum(String albumId) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callBack = dataService.GetDataAlbum(albumId);
        callBack.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                listSong = (ArrayList<Song>) response.body();
                adapterSong = new AdapterSong(ListSongActivity.this, listSong);
                rcvListSong.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                rcvListSong.setAdapter(adapterSong);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void getDataBanner(String bannerId) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callBack = dataService.GetDataBanner(bannerId);
        callBack.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                listSong = (ArrayList<Song>) response.body();
                adapterSong = new AdapterSong(ListSongActivity.this, listSong);
                rcvListSong.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                rcvListSong.setAdapter(adapterSong);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void getData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("playlist")) {
                playlist = (Playlist) intent.getSerializableExtra("playlist");
            } else if (intent.hasExtra("album")) {
                album = (Album) intent.getSerializableExtra("album");
            } else {
                banner = (Banner) intent.getSerializableExtra("banner");
            }
            text = intent.getStringExtra("text");
        }
    }
}
