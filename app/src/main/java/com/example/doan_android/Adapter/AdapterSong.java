package com.example.doan_android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_android.Activity.DsBaihat;
import com.example.doan_android.Activity.play_music;
import com.example.doan_android.Model.Baihat;
import com.example.doan_android.Model.Banner;
import com.example.doan_android.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterSong extends RecyclerView.Adapter<AdapterSong.ViewHolder>{

  Context context;
  ArrayList<Baihat> baihatArrayList;

  public AdapterSong(Context context, ArrayList<Baihat> baihatArrayList) {
    this.context = context;
    this.baihatArrayList = baihatArrayList;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.item_list_baihat, parent, false);
    return new ViewHolder(view);

  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    Baihat baihat = baihatArrayList.get(position);
    holder.itemtxtTenCaSi.setText(baihat.getTenCasi());
    holder.itemtxtTenBaiHat.setText(baihat.getTenBaihat());
    Picasso.get().load(baihat.getHinhBaihat()).into(holder.itemimgBaiHat);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent= new Intent(context, play_music.class);
            intent.putExtra("baihats",baihatArrayList.get(position));
            context.startActivity(intent);
        }
    });

  }

  @Override
  public int getItemCount() { return baihatArrayList.size(); }

  public class ViewHolder extends RecyclerView.ViewHolder{
          TextView itemtxtTenBaiHat, itemtxtTenCaSi;
          ImageView itemimgBaiHat;
          public ViewHolder(View itemView){
              super(itemView);
              itemtxtTenCaSi = itemView.findViewById(R.id.itemtxtTenCaSi);
              itemtxtTenBaiHat = itemView.findViewById(R.id.itemtxtTenBaiHat);
              itemimgBaiHat = itemView.findViewById(R.id.itemimgBaihat);
          }
      }

}
