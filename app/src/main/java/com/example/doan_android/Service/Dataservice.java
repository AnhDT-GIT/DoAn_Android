package com.example.doan_android.Service;

import com.example.doan_android.Model.Banner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Dataservice {
  @GET("banner.php")
  Call<List<Banner>> GetDataBanner();
}
