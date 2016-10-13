package com.example.lcy.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lcy.demo.R;
import com.example.lcy.demo.activity.OpenDetailActivity;
import com.example.lcy.demo.bean.NewDetailBean;
import com.example.lcy.demo.http.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lcy on 2016/10/11.
 */
public class NewDetailAdapter extends BaseAdapter {

    List<NewDetailBean.ListBean>listBeen;
    Context context;

    public NewDetailAdapter(List<NewDetailBean.ListBean> listBeen, Context context) {
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
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.new_detail_list_item,null);
            viewHolder=new ViewHolder(convertView);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.new_detail_list_size.setText("大小："+listBeen.get(position).getAppsize());
        viewHolder.new_detail_list_desc.setText(listBeen.get(position).getDescs());
        viewHolder.new_detail_list_gname.setText(listBeen.get(position).getAppname());
        viewHolder.new_detail_list_type.setText("类型："+listBeen.get(position).getTypename());
        Picasso.with(context).load(HttpUtils.BASE_URL+listBeen.get(position).getIconurl())
                .into(viewHolder.new_detail_list_icon);

        viewHolder.new_detail_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OpenDetailActivity.class);
                intent.putExtra("id",listBeen.get(position).getAppid());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder {
        @BindView(R.id.new_detail_list_icon)
        ImageView new_detail_list_icon;
        @BindView(R.id.new_detail_list_gname)
        TextView new_detail_list_gname;
        @BindView(R.id.new_detail_list_type)
        TextView new_detail_list_type;
        @BindView(R.id.new_detail_list_size)
        TextView new_detail_list_size;
        @BindView(R.id.new_detail_list_desc)
        TextView new_detail_list_desc;

        @BindView(R.id.new_detail_list_btn)
        Button new_detail_list_btn;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
