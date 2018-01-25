package com.example.a103.datasearch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a103.datasearch.data.Machine;

import java.util.List;

/**
 * Created by XiaoHu Zheng on 2017/7/12.
 * Email: 1050087728@qq.com
 */

public class MachineRecyclerViewAdapter extends RecyclerView.Adapter<MachineRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "MachineRecyclerViewAdapter";
    private List<Machine> mMachineList;
    private OnMachineRecyclerViewItemClickListener mItemClickListener;

    /**
     * constructor of this class adapter,provide the data source of the adapter
     * @param machineList the list of machine
     */
    public MachineRecyclerViewAdapter(List<Machine> machineList){
        this.mMachineList=machineList;
    }

    /**
     * method to updateMachineList
     * @param machineList data source of machine
     */
    public void updateMachineList(List<Machine> machineList){
        if (mMachineList!=null){
            mMachineList.clear();
            mMachineList.addAll(machineList);
        }else {
            mMachineList=machineList;
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.machine_recyclerview_item,parent,false);
        return new ViewHolder(itemView,mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Machine machine=mMachineList.get(position);
        holder.machineName.setText(machine.getName());
    }

    @Override
    public int getItemCount() {
        return mMachineList.size();
    }

    /**
     * define a nested class
     */
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private OnMachineRecyclerViewItemClickListener mItemClickListener;
        TextView machineName;

        ViewHolder(View itemView, OnMachineRecyclerViewItemClickListener itemClickListener) {
            super(itemView);
            this.mItemClickListener=itemClickListener;
            machineName= (TextView) itemView.findViewById(R.id.tv_machine_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener!=null){
                mItemClickListener.onItemClick(v,getAdapterPosition());
            }
        }
    }

    /**
     * define a callback interface
     */
    public interface OnMachineRecyclerViewItemClickListener{
        void onItemClick(View view,int position);
    }

    /**
     * register the callback
     * @param onItemClickListener the listener of {@link OnMachineRecyclerViewItemClickListener}
     */
    public void setOnItemClickListener(OnMachineRecyclerViewItemClickListener onItemClickListener){
        this.mItemClickListener=onItemClickListener;
    }
}
