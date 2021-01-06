package com.example.doan_android.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Banner implements Serializable {

  @SerializedName("id_banner")
  @Expose
  private String idBanner;
  @SerializedName("hinh_banner")
  @Expose
  private String hinhBanner;
  @SerializedName("noidung_banner")
  @Expose
  private String noidungBanner;
  @SerializedName("id_baihat")
  @Expose
  private String idBaihat;
  @SerializedName("ten_baihat")
  @Expose
  private String tenBaihat;
  @SerializedName("hinh_baihat")
  @Expose
  private String hinhBaihat;

  public String getIdBanner() {
    return idBanner;
  }

  public void setIdBanner(String idBanner) {
    this.idBanner = idBanner;
  }

  public String getHinhBanner() {
    return hinhBanner;
  }

  public void setHinhBanner(String hinhBanner) {
    this.hinhBanner = hinhBanner;
  }

  public String getNoidungBanner() {
    return noidungBanner;
  }

  public void setNoidungBanner(String noidungBanner) {
    this.noidungBanner = noidungBanner;
  }

  public String getIdBaihat() {
    return idBaihat;
  }

  public void setIdBaihat(String idBaihat) {
    this.idBaihat = idBaihat;
  }

  public String getTenBaihat() {
    return tenBaihat;
  }

  public void setTenBaihat(String tenBaihat) {
    this.tenBaihat = tenBaihat;
  }

  public String getHinhBaihat() {
    return hinhBaihat;
  }

  public void setHinhBaihat(String hinhBaihat) {
    this.hinhBaihat = hinhBaihat;
  }

}
