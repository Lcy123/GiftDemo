package com.example.lcy.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lcy.demo.R;
import com.example.lcy.demo.activity.OpenDetailActivity;
import com.example.lcy.demo.bean.ThreeDetailBean;
import com.example.lcy.demo.http.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lcy on 2016/10/10.
 */
public class ThreeDetailAdapter extends BaseAdapter {

    List<ThreeDetailBean.ListBean> listBeen;
    Context context;

    public ThreeDetailAdapter(List<ThreeDetailBean.ListBean> listBeen, Context context) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.three_detail_grid_item, null);
            viewHolder = new ViewHolder(convertView);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.three_detail_grid_name.setText(listBeen.get(position).getAppname());
        Picasso.with(context).load(HttpUtils.BASE_URL + listBeen.get(position).getAppicon())
                .into(viewHolder.three_detail_grid_icon);
        initEvent(convertView,listBeen.get(position).getAppid());
        return convertView;
    }

    class ViewHolder {

        @BindView(R.id.three_detail_grid_icon)
        ImageView three_detail_grid_icon;

        @BindView(R.id.three_detail_grid_name)
        TextView three_detail_grid_name;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
    private void initEvent(View convertView,final String id){
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OpenDetailActivity.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });
    }
}
