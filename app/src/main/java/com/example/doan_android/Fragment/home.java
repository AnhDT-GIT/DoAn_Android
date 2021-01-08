package com.example.doan_android.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.doan_android.Adapter.AdapterAlbum;
import com.example.doan_android.Adapter.AdapterBanner;
import com.example.doan_android.Adapter.AdapterPlaylist;
import com.example.doan_android.Model.Album;
import com.example.doan_android.Model.Banner;
import com.example.doan_android.Model.Playlist;
import com.example.doan_android.R;
import com.example.doan_android.Service.APIService;
import com.example.doan_android.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class home extends Fragment {

    public home() {
        // Required empty public constructor
    }

    View view;

    RecyclerView lvBanner, lvPlaylist, lvAlbum;

    AdapterBanner adapterBanner;
    AdapterPlaylist adapterPlaylist;
    AdapterAlbum adapterAlbum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false);
          view = inflater.inflate(R.layout.fragment_home, container, false);

          lvBanner = view.findViewById(R.id.lvBanner);
          lvPlaylist = view.findViewById(R.id.lvPlaylist);
          lvAlbum = view.findViewById(R.id.lvAlbum);
          GetData();

          return view;
    }

    private void GetData() {
          //Gọi interface Dataservice
          Dataservice dataservice = APIService.getService();

          //Gọi tất cả những thứ gì liên quan đến hiển thị trên recyclerview: banner
          Call<List<Banner>> callbackbanner = dataservice.GetDataBanner();
          callbackbanner.enqueue(new Callback<List<Banner>>() {
              @Override
            public void onResponse(Call<List<Banner>> call, Response<List<Banner>> response) {
              ArrayList<Banner> bannerArrayList = (ArrayList<Banner>) response.body();
              adapterBanner = new AdapterBanner(getActivity(), bannerArrayList);
              LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
              linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
              lvBanner.setLayoutManager(linearLayoutManager);
              lvBanner.setAdapter(adapterBanner);
            }

            @Override
            public void onFailure(Call<List<Banner>> call, Throwable t) {

            }
          });

          //Gọi tất cả những thứ gì liên quan đến hiển thị trên recyclerview: playlist
          Call<List<Playlist>> callbackplaylist = dataservice.GetDataPlaylist();
          callbackplaylist.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
              ArrayList<Playlist> playlistArrayList = (ArrayList<Playlist>) response.body();
              adapterPlaylist = new AdapterPlaylist(getActivity(), playlistArrayList);
              LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
              linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
              lvPlaylist.setLayoutManager(linearLayoutManager);
              lvPlaylist.setAdapter(adapterPlaylist);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
          });

          Call<List<Album>> callbackalbum = dataservice.GetDataAlbum();
          callbackalbum.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
              ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
              adapterAlbum = new AdapterAlbum(getActivity(), albumArrayList);
              LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
              linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
              lvAlbum.setLayoutManager(linearLayoutManager);
              lvAlbum.setAdapter(adapterAlbum);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
          });

    }


}
