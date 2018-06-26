package com.aixuetang.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * User: zhaokun
 * Date: 2018-06-22
 * Time: 13:08
 * FIXME
 */
public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> secondList;
    private int index = -1;//标记当前选择的选项

    public FirstAdapter(Context context, ArrayList<String> secondList) {
        this.context = context;
        this.secondList = secondList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_radio, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_question_item.setText(secondList.get(position));
        holder.rb_question_item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
//                    Toast.makeText(context,"你选择的选项是"+secondList.get(position),Toast.LENGTH_SHORT).show();
                    index = position;
                    notifyDataSetChanged();
                }
            }
        });
        if(index==position){
            holder.rb_question_item.setChecked(true);
        } else {
            holder.rb_question_item.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return secondList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        RadioButton rb_question_item;
        TextView tv_question_item;
        public MyViewHolder(View itemView) {
            super(itemView);
            rb_question_item = itemView.findViewById(R.id.rb_question_item);
            tv_question_item = itemView.findViewById(R.id.tv_question_item);
        }
    }

}