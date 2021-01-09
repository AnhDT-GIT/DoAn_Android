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
  ArrayList<Song> listSong;

  public AdapterSong(Context context, ArrayList<Song> listSong) {
    this.context = context;
    this.listSong = listSong;
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

    Song song = listSong.get(position);
    holder.txtItemArtistName.setText(song.getTenCasi());
    holder.txtItemSongName.setText(song.getTenBaihat());
    Picasso.get().load(song.getHinhBaihat()).into(holder.imvItemSong);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent= new Intent(context, PlayMusicActivity.class);
            intent.putExtra("baihats", listSong.get(position));
            context.startActivity(intent);
        }
    });

  }

  @Override
  public int getItemCount() { return listSong.size(); }

  public class ViewHolder extends RecyclerView.ViewHolder{
          TextView txtItemSongName, txtItemArtistName;
          ImageView imvItemSong;
          public ViewHolder(View itemView){
              super(itemView);
              txtItemArtistName = itemView.findViewById(R.id.txtItemArtistName);
              txtItemSongName = itemView.findViewById(R.id.txtItemSongName);
              imvItemSong = itemView.findViewById(R.id.imvItemSong);
          }
      }

}
