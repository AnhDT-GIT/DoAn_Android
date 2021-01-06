package com.example.doan_android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_android.Model.Baihat;
import com.example.doan_android.Model.Banner;
import com.example.doan_android.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterSong extends RecyclerView.Adapter<AdapterSong.ViewHolder>{

  //Context cung cấp quyền truy cập đến các layout, drawable, activity, fragment,...
  Context context;
  ArrayList<Baihat> baihatArrayList;

  public AdapterSong(Context context, ArrayList<Baihat> baihatArrayList) {
    this.context = context;
    this.baihatArrayList = baihatArrayList;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    //LayoutInflater dùng để xml thành view của java
    LayoutInflater inflater = LayoutInflater.from(context);
    //Nạp layout cho View biểu diễn item_recycler_banner
    View view = inflater.inflate(R.layout.item_list_baihat, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Baihat baihat = baihatArrayList.get(position);

    //Lấy hình ảnh gánh vào itemimgBanner thông qua Picasso
    Picasso.get().load(baihat.getHinhBaihat()).into(holder.itemimgBaihat);

    //Lấy nội dung banner và tên bài hát gán vào itemtxtNoiDungBanner
    holder.itemtxtTenBaiHat.setText(baihat.getTenBaihat());
    holder.itemtxtTenCaSi.setText(baihat.getTenCasi());
  }

  //Mảng có bao nhiêu phần tử thì nó tự hiển thị ra bấy nhiêu
  @Override
  public int getItemCount() {
    return baihatArrayList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder{

    ImageView itemimgBaihat;
    TextView itemtxtTenBaiHat, itemtxtTenCaSi;

    public ViewHolder(View itemView){
      super(itemView);
      itemimgBaihat = itemView.findViewById(R.id.itemimgBaihat);
      itemtxtTenBaiHat = itemView.findViewById(R.id.itemtxtTenBaiHat);
      itemtxtTenCaSi = itemView.findViewById(R.id.itemtxtTenCaSi);
    }
  }

}
