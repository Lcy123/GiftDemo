package com.example.lcy.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lcy.demo.R;
import com.example.lcy.demo.activity.OpenDetailActivity;
import com.example.lcy.demo.activity.SpecialDetailActivity;
import com.example.lcy.demo.bean.ThreeDetailBean;
import com.example.lcy.demo.bean.WeekThreeBean;
import com.example.lcy.demo.http.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lcy on 2016/10/9.
 */
public class ThreeAdapter extends BaseAdapter {

    List<WeekThreeBean.ListBean>listBeen;
    Context context;

    public ThreeAdapter(List<WeekThreeBean.ListBean> listBeen, Context context) {
        this.listBeen = listBeen;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null){
            convertView= LayoutInflater.from(context).inflate(R.layout.three_list_item,null);
            viewHolder = new ViewHolder(convertView);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.three_list_gname.setText(listBeen.get(position).getName());
        viewHolder.three_list_time.setText(listBeen.get(position).getAddtime());
        Picasso.with(context).load(HttpUtils.BASE_URL+listBeen.get(position).getIconurl())
                .into(viewHolder.three_list_image);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initEvent(listBeen.get(position));
            }
        });
        return convertView;
    }
    class ViewHolder{

        @BindView(R.id.three_list_gname)
        TextView three_list_gname;
        @BindView(R.id.three_list_time)
        TextView three_list_time;
        @BindView(R.id.three_list_image)
        ImageView three_list_image;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }

    private void initEvent(WeekThreeBean.ListBean bean){
        Intent intent=new Intent(context,SpecialDetailActivity.class);
        intent.putExtra("id",bean.getSid());
        intent.putExtra("name",bean.getName());
        intent.putExtra("time",bean.getAddtime());
        intent.putExtra("image",bean.getIconurl());
        intent.putExtra("desc","导读："+bean.getDescs());
        context.startActivity(intent);
    }

}
