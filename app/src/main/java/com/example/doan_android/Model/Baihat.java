package com.example.doan_android.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.ParcelableSpan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Baihat implements Parcelable {

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

    protected Baihat(Parcel in) {
        idBaihat = in.readString();
        tenBaihat = in.readString();
        hinhBaihat = in.readString();
        urlBaihat = in.readString();
        tenCasi = in.readString();
    }

    public static final Creator<Baihat> CREATOR = new Creator<Baihat>() {
        @Override
        public Baihat createFromParcel(Parcel in) {
            return new Baihat(in);
        }

        @Override
        public Baihat[] newArray(int size) {
            return new Baihat[size];
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