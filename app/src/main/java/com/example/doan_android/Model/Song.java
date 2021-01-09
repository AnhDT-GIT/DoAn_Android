package com.example.doan_android.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Song implements Parcelable {

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

    protected Song(Parcel in) {
        idBaihat = in.readString();
        tenBaihat = in.readString();
        hinhBaihat = in.readString();
        urlBaihat = in.readString();
        tenCasi = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Baihat{");
        sb.append("tenBaihat='").append(tenBaihat).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idBaihat);
        parcel.writeString(tenBaihat);
        parcel.writeString(hinhBaihat);
        parcel.writeString(urlBaihat);
        parcel.writeString(tenCasi);
    }
}