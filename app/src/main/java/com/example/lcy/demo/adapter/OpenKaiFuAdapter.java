package com.example.lcy.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lcy.demo.R;
import com.example.lcy.demo.activity.OpenDetailActivity;
import com.example.lcy.demo.bean.KaiFuBean;
import com.example.lcy.demo.http.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lcy on 2016/10/7.
 */
public class OpenKaiFuAdapter extends BaseExpandableListAdapter {

    KaiFuBean kaiFuBean;
    List<String> keys;
    Map<String,List<KaiFuBean.InfoBean>> result;
    Context context;

    public OpenKaiFuAdapter(List<String> keys,Map<String,List<KaiFuBean.InfoBean>> result, Context context) {
        this.keys=keys;
        this.result=result;
        this.context = context;
    }
    @Override
    public int getGroupCount() {
        return keys == null ? 0 : keys.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return result.get(keys.get(groupPosition)) == null ? 0 : result.get(keys.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return keys.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return result.get(keys.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.open_kaifu_group_item,null);
            groupViewHolder=new GroupViewHolder(convertView);
        }else {
            groupViewHolder= (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.open_kaiFu_addTime.setText(keys.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView ==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.open_kaifu_item,null);
            viewHolder = new ViewHolder(convertView);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        KaiFuBean.InfoBean bean = result.get(keys.get(groupPosition)).get(childPosition);
        viewHolder.open_gname.setText(bean.getGname());
        viewHolder.open_operators.setText("运营商:"+bean.getOperators());
        viewHolder.open_time.setText(bean.getLinkurl());
        viewHolder.open_area.setText(bean.getArea());
        Picasso.with(context).load(HttpUtils.BASE_URL+bean.getIconurl()).into(viewHolder.open_icon);

        initEvent(convertView,bean.getGid());
        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHolder{
        @BindView(R.id.open_icon)
        ImageView open_icon;
        @BindView(R.id.open_gname)
        TextView open_gname;
        @BindView(R.id.open_time)
        TextView open_time;
        @BindView(R.id.open_area)
        TextView open_area;
        @BindView(R.id.open_operators)
        TextView open_operators;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }

    class GroupViewHolder{
        @BindView(R.id.open_kaiFu_addTime)
        TextView open_kaiFu_addTime;

        public GroupViewHolder(View view) {
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
