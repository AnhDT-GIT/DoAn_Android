package com.example.doan_android.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Baihat {

@SerializedName("id_baihat")
@Expose
private String idBaihat;
@SerializedName("ten_baihat")
@Expose
private String tenBaihat;
@SerializedName("hinh_baihat")
@Expose
private String hinhBaihat;
@SerializedName("url_baihat")
@Expose
private String urlBaihat;
@SerializedName("ten_casi")
@Expose
private String tenCasi;

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

public String getUrlBaihat() {
return urlBaihat;
}

public void setUrlBaihat(String urlBaihat) {
this.urlBaihat = urlBaihat;
}

public String getTenCasi() {
return tenCasi;
}

public void setTenCasi(String tenCasi) {
this.tenCasi = tenCasi;
}

}