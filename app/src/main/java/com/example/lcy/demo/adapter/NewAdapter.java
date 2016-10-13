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
import com.example.lcy.demo.activity.NewDetailActivity;
import com.example.lcy.demo.bean.NewGameBean;
import com.example.lcy.demo.http.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lcy on 2016/10/9.
 */
public class NewAdapter extends BaseAdapter {
    List<NewGameBean.ListBean>listBeen;
    Context context;

    public NewAdapter(List<NewGameBean.ListBean> listBeen, Context context) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.new_list_item,null);
            viewHolder = new ViewHolder(convertView);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.new_list_name.setText(listBeen.get(position).getName());
        Picasso.with(context).load(HttpUtils.BASE_URL+listBeen.get(position).getIconurl())
                .into(viewHolder.new_list_image);
        Picasso.with(context).load(HttpUtils.BASE_URL+"/"+listBeen.get(position).getAuthorimg())
                .into(viewHolder.new_list_authorImage);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initEvent(listBeen.get(position));
            }
        });
        return convertView;
    }
    class ViewHolder{

       @BindView(R.id.new_list_image)
       ImageView new_list_image;
        @BindView(R.id.new_list_authorImage)
        ImageView new_list_authorImage;
        @BindView(R.id.new_list_name)
        TextView new_list_name;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
    private void initEvent(NewGameBean.ListBean bean){
        Intent intent=new Intent(context, NewDetailActivity.class);
        intent.putExtra("id",bean.getId());
        intent.putExtra("name",bean.getName());
        intent.putExtra("time",bean.getAddtime());
        intent.putExtra("image",bean.getIconurl());
        intent.putExtra("authorImage",bean.getAuthorimg());
        intent.putExtra("desc",bean.getDescs());
        context.startActivity(intent);
    }
}
