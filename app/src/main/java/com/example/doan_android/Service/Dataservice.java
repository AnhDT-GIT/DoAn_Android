package com.example.doan_android.Service;

import android.util.Log;

import com.example.doan_android.Model.Album;
import com.example.doan_android.Model.Baihat;
import com.example.doan_android.Model.Banner;
import com.example.doan_android.Model.Playlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET("banner.php")
    Call<List<Banner>> GetDataBanner();

    @GET("playlist.php")
    Call<List<Playlist>> GetDataPlaylist();

    @GET("album.php")
    Call<List<Album>> GetDataAlbum();

    @FormUrlEncoded
    @POST("baihat.php")
    Call<List<Baihat>> GetDataBaihat(@Field("id_playlists") String id_playlists);
    @FormUrlEncoded
    @POST("baihat.php")
    Call<List<Baihat>> GetDataAlbum(@Field("id_albums") String id_albums);
    @FormUrlEncoded
    @POST("baihat.php")
    Call<List<Baihat>>GetDataBanner(@Field("id_banners") String id_banners);

}
