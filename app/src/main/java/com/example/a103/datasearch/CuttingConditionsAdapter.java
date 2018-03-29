package com.example.a103.datasearch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a103.datasearch.data.CuttingConditions;

import java.util.List;

/**
 * Created by XiaoHu Zheng on 2018/3/20.
 * Email: 1050087728@qq.com
 */

class CuttingConditionsAdapter extends RecyclerView.Adapter<CuttingConditionsAdapter.ViewHolder>{

    private static final String TAG = "CuttingConditionsAdapte";

    private List<CuttingConditions> mCuttingConditionsList;

    private OnRecyclerViewItemDeleteViewClickListener mDeleteViewClickListener;
    private OnRecyclerViewItemTextViewClickListener mTextViewClickListener;




    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView cuttingConditionsDeleteView;
        TextView cuttingConditionsTitle;

        private OnRecyclerViewItemTextViewClickListener mTextViewClickListener=null;
        private OnRecyclerViewItemDeleteViewClickListener mDeleteViewClickListener=null;

        ViewHolder(View itemView,OnRecyclerViewItemDeleteViewClickListener deleteViewClickListener,
                   OnRecyclerViewItemTextViewClickListener textViewClickListener){

            super(itemView);

            cuttingConditionsTitle= (TextView) itemView.findViewById(R.id.tv_cutting_conditions_title);
            cuttingConditionsDeleteView= (ImageView) itemView.findViewById(R.id.iv_delete_cutting_conditions);

            this.mDeleteViewClickListener=deleteViewClickListener;
            this.mTextViewClickListener=textViewClickListener;

            //点击模式：设置RecyclerView中整个列表项的监听回调函数
            //            itemView.setOnClickListener(this);

            //点击模式：设置RecyclerView中列表项的删除按钮监听回调函数
            cuttingConditionsDeleteView.setOnClickListener(this);

            //设置文字被点击的监听回调函数
            cuttingConditionsTitle.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.iv_delete_cutting_conditions:
                    mDeleteViewClickListener.onItemDeleteViewClick(v,getAdapterPosition());
                    return;
                case R.id.tv_cutting_conditions_title:
                    mTextViewClickListener.onItemTextViewClick(v,getAdapterPosition());
            }
        }
    }

    /**
     * 构造函数传入适配器的数据
     * @param cuttingConditionsList 数据源
     */
    CuttingConditionsAdapter(List<CuttingConditions> cuttingConditionsList){

        this.mCuttingConditionsList=cuttingConditionsList;
    }

    /**
     * 对外提供数据更新接口
     * @param list 传入的数据参数集合
     */
    void updateCuttingConditionsList(List<CuttingConditions> list){

        if (mCuttingConditionsList!=null){

            mCuttingConditionsList.clear();
            mCuttingConditionsList.addAll(list);
        }else {
            mCuttingConditionsList=list;
        }
        notifyDataSetChanged();
    }


    //观察者模式——定义接口OnRecyclerViewItemClickListener为抽象观察者，
    // 实现该接口的MaterialCategoriesManagementActivity为具体的观察者，
    // 而观察的具体主题是MaterialCategoriesAdapter的对象adapter中ViewHolder中的根布局itemView，其抽象主题是View
    interface OnRecyclerViewItemDeleteViewClickListener {
        void onItemDeleteViewClick(View view, int position);
    }

    //观察者模式——对MaterialCategoriesAdapter的对象提供一个注册的方法
    void setOnItemDeleteViewClickListener(OnRecyclerViewItemDeleteViewClickListener itemDeleteViewClickListener) {
        this.mDeleteViewClickListener = itemDeleteViewClickListener;
    }

    /**
     * interface to callback
     */
    interface OnRecyclerViewItemTextViewClickListener{
        void onItemTextViewClick(View view,int position);
    }

    /**
     * method to register callback
     * @param itemTextViewClickListener listener of TextView in RecyclerViewItem
     */
    void setOnItemTextViewClickViewListener(OnRecyclerViewItemTextViewClickListener itemTextViewClickListener){
        this.mTextViewClickListener=itemTextViewClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.cutting_conditions_item,parent,false);

        return new ViewHolder(itemView,mDeleteViewClickListener,mTextViewClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CuttingConditions cuttingConditions=mCuttingConditionsList.get(position);
        holder.cuttingConditionsTitle.setText(cuttingConditions.getMachineInfo()+
                "|"+
                cuttingConditions.getMaterialInfo()+
                "|"+
                cuttingConditions.getToolInfo());
    }

    @Override
    public int getItemCount() {
        return mCuttingConditionsList.size();
    }
}
