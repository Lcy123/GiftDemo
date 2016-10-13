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
import com.example.lcy.demo.bean.HotBean;
import com.example.lcy.demo.http.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lcy on 2016/10/9.
 */
public class HotTopAdapter extends BaseAdapter {

    List<HotBean.InfoBean.Push1Bean>push1Been;
    Context context;

    public HotTopAdapter(List<HotBean.InfoBean.Push1Bean> push1Been, Context context) {
        this.push1Been = push1Been;
        this.context = context;
    }

    @Override
    public int getCount() {
        return push1Been.size();
    }

    @Override
    public Object getItem(int position) {
        return push1Been.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.hot_list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.hot_list_gname.setText(push1Been.get(position).getName());
        viewHolder.hot_list_type.setText("类型："+push1Been.get(position).getTypename());
        viewHolder.hot_list_size.setText("大小："+push1Been.get(position).getSize());
        viewHolder.hot_list_num.setText(push1Been.get(position).getClicks()+"人在玩");
        Picasso.with(context).load(HttpUtils.BASE_URL+push1Been.get(position).getLogo())
                .into(viewHolder.hot_list_icon);

        initEvent(convertView,push1Been.get(position).getAppid());
        return convertView;
    }

    class ViewHolder{
        @BindView(R.id.hot_list_icon)
        ImageView hot_list_icon;
        @BindView(R.id.hot_list_type)
        TextView hot_list_type;
        @BindView(R.id.hot_list_gname)
        TextView hot_list_gname;
        @BindView(R.id.hot_list_num)
        TextView hot_list_num;
        @BindView(R.id.hot_list_size)
        TextView hot_list_size;

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
