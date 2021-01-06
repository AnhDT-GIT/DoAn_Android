package com.example.doan_android.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_android.Model.Baihat;
import com.example.doan_android.Model.Banner;
import com.example.doan_android.Model.Playlist;
import com.example.doan_android.R;
import com.example.doan_android.Service.APIService;
import com.example.doan_android.Service.Dataservice;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DsBaihat extends AppCompatActivity {
    Playlist playlist;
    ArrayList<Baihat> baihatArrayList;
    ArrayAdapter<Baihat> arrayAdapter;
    ListView listView= this.findViewById(R.id.list_item);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_baihat);
        Getdata();
        if(playlist !=null )
        {
            GetdataPlaylist(playlist.getIdPlaylist());
        }
    }

    private void GetdataPlaylist(String id_playlist) {
        Dataservice dataservice= APIService.getService();
        Call<List<Baihat>> callback= dataservice.GetDataBaihat(id_playlist);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                 baihatArrayList= (ArrayList<Baihat>) response.body();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void Getdata() {
        Intent intent=getIntent();
        if(intent !=null)
        {
            if(intent.hasExtra("playlist"))
            {
                playlist= (Playlist) intent.getSerializableExtra("playlist");
            }
        }
    }
}
