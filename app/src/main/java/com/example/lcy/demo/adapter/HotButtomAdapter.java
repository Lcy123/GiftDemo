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
public class HotButtomAdapter extends BaseAdapter {

    List<HotBean.InfoBean.Push2Bean>push2Been;
    Context context;

    public HotButtomAdapter(List<HotBean.InfoBean.Push2Bean> push2Been, Context context) {
        this.push2Been = push2Been;
        this.context = context;
    }

    @Override
    public int getCount() {
        return push2Been.size();
    }

    @Override
    public Object getItem(int position) {
        return push2Been.get(position);
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
                    .inflate(R.layout.hot_grid_item, parent, false);
            viewHolder = new ViewHolder(convertView);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.hot_grid_gname.setText(push2Been.get(position).getName());
        Picasso.with(context).load(HttpUtils.BASE_URL+push2Been.get(position).getLogo())
                .into(viewHolder.hot_grid_image);
        initEvent(convertView,push2Been.get(position).getAppid());
        return convertView;
    }
    class ViewHolder{
        @BindView(R.id.hot_grid_image)
        ImageView hot_grid_image;
        @BindView(R.id.hot_grid_gname)
        TextView hot_grid_gname;

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
