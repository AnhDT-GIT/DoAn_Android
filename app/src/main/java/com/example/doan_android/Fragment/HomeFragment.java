package com.example.doan_android.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doan_android.Adapter.AdapterAlbum;
import com.example.doan_android.Adapter.AdapterBanner;
import com.example.doan_android.Adapter.AdapterPlaylist;
import com.example.doan_android.Model.Album;
import com.example.doan_android.Model.Banner;
import com.example.doan_android.Model.Playlist;
import com.example.doan_android.R;
import com.example.doan_android.Service.APIService;
import com.example.doan_android.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    View view;
    RecyclerView rcvBanner, rcvPlaylist, rcvAlbum;
    AdapterBanner adapterBanner;
    AdapterPlaylist adapterPlaylist;
    AdapterAlbum adapterAlbum;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false);
        view = inflater.inflate(R.layout.fragment_home, container, false);
        rcvBanner = view.findViewById(R.id.rcvBanner);
        rcvPlaylist = view.findViewById(R.id.rcvPlaylist);
        rcvAlbum = view.findViewById(R.id.rcvAlbum);
        GetData();

        return view;
    }

    private void GetData() {
        //Gọi interface Dataservice
        DataService dataService = APIService.getService();
        //Gọi tất cả những thứ gì liên quan đến hiển thị trên recyclerview: banner
        Call<List<Banner>> callBackBanner = dataService.GetDataBanner();
        callBackBanner.enqueue(new Callback<List<Banner>>() {
            @Override
            public void onResponse(Call<List<Banner>> call, Response<List<Banner>> response) {
                ArrayList<Banner> listBanner = (ArrayList<Banner>) response.body();
                adapterBanner = new AdapterBanner(getActivity(), listBanner);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                rcvBanner.setLayoutManager(linearLayoutManager);
                rcvBanner.setAdapter(adapterBanner);
            }

            @Override
            public void onFailure(Call<List<Banner>> call, Throwable t) {

            }
        });

        //Gọi tất cả những thứ gì liên quan đến hiển thị trên recyclerview: playlist
        Call<List<Playlist>> callBackPlaylist = dataService.GetDataPlaylist();
        callBackPlaylist.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> listPlaylist = (ArrayList<Playlist>) response.body();
                adapterPlaylist = new AdapterPlaylist(getActivity(), listPlaylist);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                rcvPlaylist.setLayoutManager(linearLayoutManager);
                rcvPlaylist.setAdapter(adapterPlaylist);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });

        Call<List<Album>> callBackAlbum = dataService.GetDataAlbum();
        callBackAlbum.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> listAlbum = (ArrayList<Album>) response.body();
                adapterAlbum = new AdapterAlbum(getActivity(), listAlbum);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                rcvAlbum.setLayoutManager(linearLayoutManager);
                rcvAlbum.setAdapter(adapterAlbum);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });

    }


}
