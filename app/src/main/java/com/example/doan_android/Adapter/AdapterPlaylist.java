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
import com.example.doan_android.Model.Playlist;
import com.example.doan_android.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterPlaylist extends RecyclerView.Adapter<AdapterPlaylist.ViewHolder> {

    Context context;
    ArrayList<Playlist> listPlaylist;

    public AdapterPlaylist(Context context, ArrayList<Playlist> listPlaylist) {
        this.context = context;
        this.listPlaylist = listPlaylist;
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
        Playlist playlist = listPlaylist.get(position);
        Picasso.get().load(playlist.getHinhPlaylist()).into(holder.imvItemPlaylist);
        holder.txtItemPlaylistName.setText(playlist.getTenPlaylist());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListSongActivity.class);
                intent.putExtra("playlist", listPlaylist.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPlaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imvItemPlaylist;
        TextView txtItemPlaylistName;

        public ViewHolder(View itemView) {
            super(itemView);
            imvItemPlaylist = itemView.findViewById(R.id.imvItemPlaylist);
            txtItemPlaylistName = itemView.findViewById(R.id.txtItemPlaylistName);
        }
    }
}
