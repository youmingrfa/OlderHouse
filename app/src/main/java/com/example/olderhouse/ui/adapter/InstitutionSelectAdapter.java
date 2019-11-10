package com.example.olderhouse.ui.adapter;

import android.content.ComponentName;
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
import com.example.olderhouse.ui.provided.OnItemClickListener;
import com.example.olderhouse.ui.utils.ImageUtil;

public class InstitutionSelectAdapter extends RecyclerView.Adapter<InstitutionSelectAdapter.ViewHolder> {

    private Context context;
    private OnItemClickListener mClickListener;

    public InstitutionSelectAdapter(Context context){
        this.context = context;
    }

    @Override
    public InstitutionSelectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.institution_item,viewGroup,false);
        InstitutionSelectAdapter.ViewHolder viewHolder = new InstitutionSelectAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(InstitutionSelectAdapter.ViewHolder viewHolder, final int i) {
        Glide.with(context).load(ImageUtil.imageUrls[0]).into(viewHolder.pic);
        viewHolder.name.setText("开封市祥符区九九福老年公寓");
        viewHolder.type.setText("民办");
        viewHolder.number.setText("30");
        viewHolder.location.setText("祥符区兴隆乡杨寨村");
        if(mClickListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView pic;
        private TextView name,type,number,location;

        public ViewHolder(View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.institution_pic);
            name = itemView.findViewById(R.id.institution_name);
            type = itemView.findViewById(R.id.institution_type);
            number = itemView.findViewById(R.id.institution_number);
            location = itemView.findViewById(R.id.institution_location);
        }

    }

}
