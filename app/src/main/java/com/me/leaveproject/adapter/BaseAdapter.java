package com.me.leaveproject.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.me.leaveproject.R;

import java.util.List;

public class BaseAdapter  extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder> {


    private List<String> list;

    public void setList(List<String> list) {
        this.list = list;
    }


    /* 在这里设置视图 复用viewHolder */
    @NonNull
    @Override
    public BaseAdapter.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_slide,parent,false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.BaseViewHolder viewHolder, int position) {
        viewHolder.surfaceTv.setText("表面 "+(position+1));
        viewHolder.deleteTv.setText("删除"+(position+1));
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class BaseViewHolder extends RecyclerView.ViewHolder{

        TextView deleteTv,surfaceTv;
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteTv = itemView.findViewById(R.id.deleteTv);
            surfaceTv = itemView.findViewById(R.id.surfaceTv);
        }



    }
}
