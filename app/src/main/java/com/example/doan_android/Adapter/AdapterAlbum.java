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

import com.example.doan_android.Activity.ListSongActivity;
import com.example.doan_android.Model.Album;
import com.example.doan_android.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterAlbum extends RecyclerView.Adapter<AdapterAlbum.ViewHolder>{

    Context context;
    ArrayList<Album> albumArrayList;

    public AdapterAlbum(Context context, ArrayList<Album> albumArrayList) {
      this.context = context;
      this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      LayoutInflater inflater = LayoutInflater.from(context);
      View view = inflater.inflate(R.layout.item_recycler_album, parent, false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAlbum.ViewHolder holder, int position) {
      Album album = albumArrayList.get(position);
      Picasso.get().load(album.getHinhAlbum()).into(holder.itemimgAlbum);
      holder.itemtxtTenAlbum.setText(album.getTenAlbum());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, ListSongActivity.class);
                intent.putExtra("album",albumArrayList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
      return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

      ImageView itemimgAlbum;
      TextView itemtxtTenAlbum;
      public ViewHolder(View itemView){
        super(itemView);
        itemimgAlbum = itemView.findViewById(R.id.itemimgAlbum);
        itemtxtTenAlbum = itemView.findViewById(R.id.itemtxtTenAlbum);
      }
    }
}
