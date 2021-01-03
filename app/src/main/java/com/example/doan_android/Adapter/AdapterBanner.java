package com.example.doan_android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_android.Model.Banner;
import com.example.doan_android.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterBanner extends RecyclerView.Adapter<AdapterBanner.ViewHolder>{

    Context context;
    ArrayList<Banner> bannerArrayList;

    public AdapterBanner(Context context, ArrayList<Banner> bannerArrayList) {
      this.context = context;
      this.bannerArrayList = bannerArrayList;
    }

  @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      LayoutInflater inflater = LayoutInflater.from(context);
      View view = inflater.inflate(R.layout.item_recycler_banner, parent, false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      Banner banner = bannerArrayList.get(position);
      Picasso.get().load(banner.getHinhBaihat()).into(holder.itemimgBanner);
      holder.itemtxtNoiDungBanner.setText(banner.getTenBaihat() + " - " + banner.getNoidungBanner());
    }

    @Override
    public int getItemCount() {
      return bannerArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

          ImageView itemimgBanner;
          TextView itemtxtNoiDungBanner;
          public ViewHolder(View itemView){
              super(itemView);
              itemimgBanner = itemView.findViewById(R.id.itemimgBanner);
              itemtxtNoiDungBanner = itemView.findViewById(R.id.itemtxtNoiDungBanner);
          }
        }

}
