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

    //Context cung cấp quyền truy cập đến các layout, drawable, activity, fragment,...
    Context context;
    ArrayList<Banner> bannerArrayList;

    public AdapterBanner(Context context, ArrayList<Banner> bannerArrayList) {
      this.context = context;
      this.bannerArrayList = bannerArrayList;
    }

   @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      //LayoutInflater dùng để xml thành view của java
      LayoutInflater inflater = LayoutInflater.from(context);
      //Nạp layout cho View biểu diễn item_recycler_banner
      View view = inflater.inflate(R.layout.item_recycler_banner, parent, false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      Banner banner = bannerArrayList.get(position);

      //Lấy hình ảnh gánh vào itemimgBanner thông qua Picasso
      Picasso.get().load(banner.getHinhBaihat()).into(holder.itemimgBanner);

      //Lấy nội dung banner và tên bài hát gán vào itemtxtNoiDungBanner
      holder.itemtxtNoiDungBanner.setText(banner.getTenBaihat() + " - " + banner.getNoidungBanner());
    }

    //Mảng có bao nhiêu phần tử thì nó tự hiển thị ra bấy nhiêu
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
