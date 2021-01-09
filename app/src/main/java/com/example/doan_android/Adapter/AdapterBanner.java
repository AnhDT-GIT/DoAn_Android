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
import com.example.doan_android.Model.Banner;
import com.example.doan_android.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterBanner extends RecyclerView.Adapter<AdapterBanner.ViewHolder> {

    //Context cung cấp quyền truy cập đến các layout, drawable, activity, fragment,...
    Context context;
    ArrayList<Banner> listBanner;

    public AdapterBanner(Context context, ArrayList<Banner> listBanner) {
        this.context = context;
        this.listBanner = listBanner;
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
        Banner banner = listBanner.get(position);
        //Lấy hình ảnh gánh vào itemimgBanner thông qua Picasso
        Picasso.get().load(banner.getHinhBanner()).into(holder.imvItemBanner);
        //Lấy nội dung banner và tên bài hát gán vào itemtxtNoiDungBanner
        holder.txtItemBannerContent.setText(banner.getTenBaihat() + " - " + banner.getNoidungBanner());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListSongActivity.class);
                intent.putExtra("banner", listBanner.get(position));
                context.startActivity(intent);
            }
        });
    }

    //Mảng có bao nhiêu phần tử thì nó tự hiển thị ra bấy nhiêu
    @Override
    public int getItemCount() {
        return listBanner.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imvItemBanner;
        TextView txtItemBannerContent;

        public ViewHolder(View itemView) {
            super(itemView);
            imvItemBanner = itemView.findViewById(R.id.imvItemBanner);
            txtItemBannerContent = itemView.findViewById(R.id.txtItemBannerContent);
        }
    }

}
