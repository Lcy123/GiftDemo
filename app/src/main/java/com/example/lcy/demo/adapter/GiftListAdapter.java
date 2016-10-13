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
import com.example.lcy.demo.activity.GiftDetailActivity;
import com.example.lcy.demo.activity.MainActivity;
import com.example.lcy.demo.bean.GiftListBean;
import com.example.lcy.demo.http.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lcy on 2016/9/28.
 */
public class GiftListAdapter extends BaseAdapter {

    List<GiftListBean.ListBean> list;
    Context context;

    public GiftListAdapter(List<GiftListBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.gift_list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.g_name.setText(list.get(position).getGname());
        viewHolder.tv_giftName.setText(list.get(position).getGiftname());
        viewHolder.tv_num.setText("剩余:"+list.get(position).getNumber());

        viewHolder.tv_time.setText("时间:"+list.get(position).getAddtime());
        if (list.get(position).getNumber()==0){
            viewHolder.gift_button.setText("---淘 号---");
        }else{
            viewHolder.gift_button.setText("立即领取");
        }
        String path = HttpUtils.BASE_URL+list.get(position).getIconurl();
        Picasso.with(context).load(path).into(viewHolder.tv_icon);

        GiftListBean.ListBean bean = (GiftListBean.ListBean) getItem(position);
        initEvent(convertView,bean.getId());

        return convertView;
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
        ImageView tv_icon;
        @BindView(R.id.gift_button)
        TextView gift_button;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
