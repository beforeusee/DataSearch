package com.example.a103.datasearch;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.a103.datasearch.data.MaterialCategories;
import java.util.List;


/**
 * Created by zhengxiaohu on 2017/3/28.
 */

public class MaterialCategoriesAdapter extends
        RecyclerView.Adapter<MaterialCategoriesAdapter.ViewHolder>{

    private static final String TAG = "MaterialCategoriesAdapter";
    private List<MaterialCategories> materialCategoriesList;
    private OnRecyclerViewItemClickListener mOnItemClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView materialCategoriesDeleteView;
        TextView materialCategoriesName;
        private OnRecyclerViewItemClickListener mOnItemClickListener=null; //声明接口变量

        public ViewHolder(View itemView,OnRecyclerViewItemClickListener listener) {
            super(itemView);
            materialCategoriesName= (TextView) itemView.
                    findViewById(R.id.tv_material_categories_name);
            materialCategoriesDeleteView= (ImageView) itemView.findViewById(R.id.iv_delete_categories);

            this.mOnItemClickListener=listener;
//            itemView.setOnClickListener(this);    //点击模式：设置RecyclerView中整个列表项的监听回调函数
            materialCategoriesDeleteView.setOnClickListener(this); //点击模式：设置RecyclerView中列表项的删除按钮监听回调函数
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener!=null){
                mOnItemClickListener.onItemClick(v,getAdapterPosition());
                Log.d(TAG, "onClick: 静态类ViewHolder中点击Item的adapterPosition位置: "+getAdapterPosition());
            }
        }
    }

    MaterialCategoriesAdapter(List<MaterialCategories> materialCategoriesList){
        //通过构造函数传入适配器的数据源
        this.materialCategoriesList=materialCategoriesList;
    }

    void updateMaterialCategoriesList(List<MaterialCategories> list){
        if (materialCategoriesList!=null){
            materialCategoriesList.clear();
            materialCategoriesList.addAll(list);
        }else {
            materialCategoriesList=list;
        }
        notifyDataSetChanged();

    }

    //观察者模式——定义接口OnRecyclerViewItemClickListener为抽象观察者，
    // 实现该接口的MaterialCategoriesManagementActivity为具体的观察者，
    // 而观察的具体主题是MaterialCategoriesAdapter的对象adapter中ViewHolder中的根布局itemView，其抽象主题是View
    interface OnRecyclerViewItemClickListener{
        void onItemClick(View view,int position);
    }

    //观察者模式——对MaterialCategoriesAdapter的对象提供一个注册的方法
    void setOnItemClickListener(OnRecyclerViewItemClickListener itemClickListener) {
        this.mOnItemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.material_categories_item,parent,false);

        return new ViewHolder(itemView,mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MaterialCategories materialCategories=materialCategoriesList.get(position);
        holder.materialCategoriesName.setText(materialCategories.getName());
    }

    @Override
    public int getItemCount() {
        return materialCategoriesList.size();
    }
}
