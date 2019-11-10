package com.example.olderhouse.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.olderhouse.R;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    private Context context;

    public ServiceAdapter(Context context){
        this.context = context;
    }

    @Override
    public ServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.service_item,viewGroup,false);
        ServiceAdapter.ViewHolder viewHolder = new ServiceAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ServiceAdapter.ViewHolder viewHolder, int i) {
        viewHolder.content.setText("上门理发");
        viewHolder.price.setText("120");
        Glide.with(context).load("http://cnsf99.com/olduploadfiles/1534832798.png");
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView pic;
        private TextView content,price;

        public ViewHolder(View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.service_pic);
            content = itemView.findViewById(R.id.service_content);
            price = itemView.findViewById(R.id.service_price);
        }

    }
}
