package com.example.doan_android.Service;

import com.example.doan_android.Model.Album;
import com.example.doan_android.Model.Banner;
import com.example.doan_android.Model.Playlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Dataservice {
    @GET("banner.php")
    Call<List<Banner>> GetDataBanner();

    @GET("playlist.php")
    Call<List<Playlist>> GetDataPlaylist();

    @GET("album.php")
    Call<List<Album>> GetDataAlbum();
}
