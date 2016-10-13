package com.example.lcy.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lcy.demo.R;
import com.example.lcy.demo.adapter.ThreeDetailAdapter;
import com.example.lcy.demo.bean.ThreeDetailBean;
import com.example.lcy.demo.http.HttpUtils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpecialDetailActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.three_detail_back)
    ImageView three_detail_back;
    @BindView(R.id.three_detail_title)
    TextView three_detail_title;
    @BindView(R.id.three_detail_share)
    ImageView three_detail_share;
    @BindView(R.id.three_detail_icon)
    ImageView three_detail_icon;
    @BindView(R.id.three_detail_time)
    TextView three_detail_time;
    @BindView(R.id.three_detail_text)
    TextView three_detail_text;

    @BindView(R.id.three_detail_grid)
    GridView three_detail_grid;

    ThreeDetailAdapter adapter;
    int id;
    List<ThreeDetailBean.ListBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_detail);
        ButterKnife.bind(this);

        id=getIntent().getIntExtra("id",id);
        initData();
        initTopData();
    }

    private void initTopData() {
        three_detail_text.setText(getIntent().getStringExtra("desc"));
        three_detail_time.setText(getIntent().getStringExtra("time"));
        three_detail_title.setText(getIntent().getStringExtra("name"));
        Picasso.with(this).load(HttpUtils.BASE_URL+getIntent().getStringExtra("image"))
                .into(three_detail_icon);
    }

    private void initData() {
        HttpUtils.getMyService().bdxqschild(id).enqueue(new Callback<ThreeDetailBean>() {
            @Override
            public void onResponse(Call<ThreeDetailBean> call, Response<ThreeDetailBean> response) {
                ThreeDetailBean bean = response.body();
                initView(bean);
            }

            @Override
            public void onFailure(Call<ThreeDetailBean> call, Throwable t) {

            }
        });
    }

    private void initView(ThreeDetailBean bean) {

        list = bean.getList();
        adapter = new ThreeDetailAdapter(list, this);
        three_detail_grid.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.three_detail_back:
                Intent intent=new Intent(SpecialDetailActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.three_detail_share:
                Intent intent3 = new Intent(Intent.ACTION_SEND);
                intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent3.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(HttpUtils.BASE_URL+getIntent().getStringExtra("image"))));
                intent3.setType("image/*");   //分享图片
                startActivity(Intent.createChooser(intent3,"分享"));
                break;
        }
    }
}
