package com.example.doan_android.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doan_android.Adapter.AdapterBanner;
import com.example.doan_android.Model.Banner;
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
    RecyclerView lvBanner;
    AdapterBanner adapterBanner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false);
          view = inflater.inflate(R.layout.fragment_home, container, false);
          lvBanner = view.findViewById(R.id.lvBanner);
          GetData();
          return view;
    }

    private void GetData() {
          Dataservice dataservice = APIService.getService();
          Call<List<Banner>> callback = dataservice.GetDataBanner() ;
          callback.enqueue(new Callback<List<Banner>>() {
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
    }


}
