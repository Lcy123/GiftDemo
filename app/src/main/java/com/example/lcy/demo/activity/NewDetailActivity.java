package com.example.lcy.demo.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lcy.demo.R;
import com.example.lcy.demo.adapter.NewDetailAdapter;
import com.example.lcy.demo.bean.NewDetailBean;
import com.example.lcy.demo.http.HttpUtils;
import com.example.lcy.demo.view.MyListView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewDetailActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.new_detail_title)
    TextView new_detail_title;
    @BindView(R.id.new_detail_icon)
    ImageView new_detail_icon;
    @BindView(R.id.new_detail_authorImage)
    CircleImageView new_detail_authorImage;
    @BindView(R.id.new_detail_desc)
    TextView new_detail_desc;
    @BindView(R.id.new_detail_back)
    ImageView new_detail_back;

    @BindView(R.id.new_detail_share)
    ImageView new_detail_share;

    @BindView(R.id.new_detail_listView)
    MyListView listView;
    int id;
    List<NewDetailBean.ListBean>list;
    NewDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);
        ButterKnife.bind(this);
        id=getIntent().getIntExtra("id",id);
        initData();
        initTopData();
    }

    private void initTopData() {

        new_detail_title.setText(getIntent().getStringExtra("name"));
        new_detail_desc.setText(getIntent().getStringExtra("desc"));
        Picasso.with(this).load(HttpUtils.BASE_URL+getIntent().getStringExtra("image"))
                .into(new_detail_icon);
        Picasso.with(this).load(HttpUtils.BASE_URL+"/"+getIntent().getStringExtra("authorImage"))
                .into(new_detail_authorImage);
    }

    private void initData() {
        HttpUtils.getMyService().getWeekllChid(id).enqueue(new Callback<NewDetailBean>() {
            @Override
            public void onResponse(Call<NewDetailBean> call, Response<NewDetailBean> response) {
                NewDetailBean bean=response.body();
                initView(bean);
            }

            @Override
            public void onFailure(Call<NewDetailBean> call, Throwable t) {

            }
        });
    }

    private void initView(NewDetailBean bean) {
        List<NewDetailBean.ListBean>list=bean.getList();
        adapter=new NewDetailAdapter(list,this);
        listView.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.new_detail_back:
                Intent intent=new Intent(NewDetailActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.new_detail_list_btn:
                Intent intent2=new Intent(NewDetailActivity.this,OpenDetailActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.new_detail_share:
                Intent intent3 = new Intent(Intent.ACTION_SEND);
                intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent3.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(HttpUtils.BASE_URL+getIntent().getStringExtra("image"))));
                intent3.setType("image/*");   //分享图片
                startActivity(Intent.createChooser(intent3,"分享"));
                break;
        }
    }
}
