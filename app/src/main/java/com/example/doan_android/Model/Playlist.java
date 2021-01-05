package com.example.doan_android.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Playlist implements Serializable {

    @SerializedName("id_playlist")
    @Expose
    private String idPlaylist;
    @SerializedName("ten_playlist")
    @Expose
    private String tenPlaylist;
    @SerializedName("hinh_playlist")
    @Expose
    private String hinhPlaylist;
    @SerializedName("icon_playlist")
    @Expose
    private String iconPlaylist;

    public String getIdPlaylist() {
      return idPlaylist;
    }

    public void setIdPlaylist(String idPlaylist) {
      this.idPlaylist = idPlaylist;
    }

    public String getTenPlaylist() {
      return tenPlaylist;
    }

    public void setTenPlaylist(String tenPlaylist) {
      this.tenPlaylist = tenPlaylist;
    }

    public String getHinhPlaylist() {
      return hinhPlaylist;
    }

    public void setHinhPlaylist(String hinhPlaylist) {
      this.hinhPlaylist = hinhPlaylist;
    }

    public String getIconPlaylist() {
      return iconPlaylist;
    }

    public void setIconPlaylist(String iconPlaylist) {
      this.iconPlaylist = iconPlaylist;
    }

}
