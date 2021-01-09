package com.example.doan_android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_android.Activity.PlayMusicActivity;
import com.example.doan_android.Model.Song;
import com.example.doan_android.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterSong extends RecyclerView.Adapter<AdapterSong.ViewHolder>{

  Context context;
  ArrayList<Song> songArrayList;

  public AdapterSong(Context context, ArrayList<Song> songArrayList) {
    this.context = context;
    this.songArrayList = songArrayList;
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

    Song song = songArrayList.get(position);
    holder.itemtxtTenCaSi.setText(song.getTenCasi());
    holder.itemtxtTenBaiHat.setText(song.getTenBaihat());
    Picasso.get().load(song.getHinhBaihat()).into(holder.itemimgBaiHat);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent= new Intent(context, PlayMusicActivity.class);
            intent.putExtra("baihats", songArrayList.get(position));
            context.startActivity(intent);
        }
    });

  }

  @Override
  public int getItemCount() { return songArrayList.size(); }

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
