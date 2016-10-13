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
import com.example.lcy.demo.activity.GiftDetailActivity;
import com.example.lcy.demo.bean.SearchBean;
import com.example.lcy.demo.http.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lcy on 2016/10/11.
 */
public class SearchAdapter extends BaseAdapter {

    List<SearchBean.ListBean>listBeen;
    Context context;

    public SearchAdapter(List<SearchBean.ListBean> listBeen, Context context) {
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

        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.gift_list_item,null);
            viewHolder=new ViewHolder(convertView);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.g_name.setText(listBeen.get(position).getGname());
        viewHolder.tv_giftName.setText(listBeen.get(position).getGiftname());
        viewHolder.tv_num.setText("剩余："+listBeen.get(position).getNumber());
        viewHolder.tv_time.setText("时间:"+listBeen.get(position).getAddtime());
        viewHolder.gift_button.setText("立即领取");
        Picasso.with(context).load(HttpUtils.BASE_URL+listBeen.get(position).getIconurl())
                .into(viewHolder.search_list_icon);

        initEvent(convertView,listBeen.get(position).getId());
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.g_name)
        TextView g_name;
        @BindView(R.id.gift_name)
        TextView tv_giftName;
        @BindView(R.id.tv_number)
        TextView tv_num;
        @BindView(R.id.tv_time)
        TextView tv_time;
        @BindView(R.id.gift_item_icon)
        ImageView search_list_icon;
        @BindView(R.id.gift_button)
        TextView gift_button;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
    private void initEvent(View convertView,final String id){
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传list游戏列表id
                Intent intent=new Intent(context,GiftDetailActivity.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });
    }
}
