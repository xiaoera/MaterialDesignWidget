package com.wpp.study.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wpp.study.R;

import java.util.ArrayList;
import java.util.Collections;


public class MyGridAdapter extends RecyclerView.Adapter implements MyItemTouchHelpCallBack.IItemTouchMovedListener{
    private Context context;
    private LayoutInflater mLayoutInflater;
    private ArrayList<String> mData;
    private IOnClickListener mDragListener;
    public MyGridAdapter(Context context, ArrayList<String> data, IOnClickListener dragListener){
        mLayoutInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mDragListener = dragListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BodyViewHolder(mLayoutInflater.inflate(R.layout.grid_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BodyViewHolder)holder).tv_Msg.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_Msg;
        public BodyViewHolder(View itemView) {
            super(itemView);
            tv_Msg = (TextView) itemView.findViewById(R.id.msg);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDragListener.onViewOnClick(getAdapterPosition());
                }
            });
        }
    }

    public interface IOnClickListener{
        void onViewOnClick(int position);
    }

    @Override
    public boolean onItemMoved(int from, int to) {
        Collections.swap(mData, from, to);
        notifyItemMoved(from, to);
        return true;
    }

    @Override
    public boolean onItemDeleted(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        return true;
    }
}
