package com.example.a103.datasearch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a103.datasearch.data.MaterialCategories;

import java.util.List;


/**
 * Created by zhengxiaohu on 2017/3/28.
 */

public class MaterialCategoriesAdapter extends
        RecyclerView.Adapter<MaterialCategoriesAdapter.ViewHolder> {

    private List<MaterialCategories> materialCategoriesList;
    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView materialCategoriesDeleteView;
        TextView materialCategoriesName;
        public ViewHolder(View itemView) {
            super(itemView);
            materialCategoriesName= (TextView) itemView.
                    findViewById(R.id.tv_material_categories_name);
            materialCategoriesDeleteView= (ImageView) itemView.findViewById(R.id.iv_delete_categories);
        }
    }

    public MaterialCategoriesAdapter(List<MaterialCategories> materialCategoriesList){
        //通过构造函数传入适配器的数据源
        this.materialCategoriesList=materialCategoriesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.material_categories_item,parent,false);
        final ViewHolder holder=new ViewHolder(itemView);
        //点击删除按钮的监听函数
        holder.materialCategoriesDeleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                MaterialCategories materialCategories=materialCategoriesList.get(position);
                Toast.makeText(v.getContext(),"点击了删除!",Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
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
