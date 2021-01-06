package com.example.doan_android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_android.Activity.DsBaihat;
import com.example.doan_android.Model.Banner;
import com.example.doan_android.Model.Playlist;
import com.example.doan_android.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterPlaylist extends RecyclerView.Adapter<AdapterPlaylist.ViewHolder>{

  Context context;
  ArrayList<Playlist> playlistArrayList;

  public AdapterPlaylist(Context context, ArrayList<Playlist> playlistArrayList) {
    this.context = context;
    this.playlistArrayList = playlistArrayList;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.item_recycler_playlist, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Playlist playlist = playlistArrayList.get(position);
    Picasso.get().load(playlist.getHinhPlaylist()).into(holder.itemimgPlaylist);
    holder.itemtxtTenPlaylist.setText(playlist.getTenPlaylist());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent= new Intent(context, DsBaihat.class);
        intent.putExtra("playlist",playlistArrayList.get(position));
        context.startActivity(intent);
      }
    });
  }

  @Override
  public int getItemCount() {
    return playlistArrayList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder{

    ImageView itemimgPlaylist;
    TextView itemtxtTenPlaylist;
    public ViewHolder(View itemView){
      super(itemView);
      itemimgPlaylist = itemView.findViewById(R.id.itemimgPlaylist);
      itemtxtTenPlaylist = itemView.findViewById(R.id.itemtxtTenPlaylist);
    }
  }
}
