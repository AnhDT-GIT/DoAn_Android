package com.example.doan_android.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Album implements Serializable {
  @SerializedName("id_album")
  @Expose
  private String idAlbum;
  @SerializedName("ten_album")
  @Expose
  private String tenAlbum;
  @SerializedName("hinh_album")
  @Expose
  private String hinhAlbum;
  @SerializedName("id_casi")
  @Expose
  private String idCasi;

  public String getIdAlbum() {
    return idAlbum;
  }

  public void setIdAlbum(String idAlbum) {
    this.idAlbum = idAlbum;
  }

  public String getTenAlbum() {
    return tenAlbum;
  }

  public void setTenAlbum(String tenAlbum) {
    this.tenAlbum = tenAlbum;
  }

  public String getHinhAlbum() {
    return hinhAlbum;
  }

  public void setHinhAlbum(String hinhAlbum) {
    this.hinhAlbum = hinhAlbum;
  }

  public String getIdCasi() {
    return idCasi;
  }

  public void setIdCasi(String idCasi) {
    this.idCasi = idCasi;
  }

}
