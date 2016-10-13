package com.example.lcy.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lcy.demo.R;
import com.example.lcy.demo.bean.GiftDetailBean;
import com.example.lcy.demo.http.HttpUtils;
import com.example.lcy.demo.http.MyService;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GiftDetailActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.gift_detail_title)
    TextView gift_detail_title;
    @BindView(R.id.gift_detail_icon)
    ImageView gift_detail_icon;
    @BindView(R.id.gift_detail_num)
    TextView gift_detail_num;
    @BindView(R.id.gift_detail_time)
    TextView gift_detail_time;
    @BindView(R.id.gift_explains)
    TextView gift_explains;
    @BindView(R.id.gift_descs)
    TextView gift_descs;
    @BindView(R.id.gift_bottom_get)
    Button gift_bottom_get;

    @BindView(R.id.gift_back)
    ImageView gift_back;

    @BindView(R.id.gift_share)
    ImageView gift_share;

    String id;
    GiftDetailBean.InfoBean infoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_detail);
        ButterKnife.bind(this);

        //获取id
        id = getIntent().getStringExtra("id");

        MyService service = HttpUtils.getMyService();
        service.getGiftInfo(id).enqueue(new Callback<GiftDetailBean>() {
            @Override
            public void onResponse(Call<GiftDetailBean> call, Response<GiftDetailBean> response) {
                GiftDetailBean bean = response.body();
                initView(bean);
            }

            @Override
            public void onFailure(Call<GiftDetailBean> call, Throwable t) {

            }
        });
    }

    private void initView(GiftDetailBean bean) {

        infoBean = bean.getInfo();
        gift_detail_title.setText(infoBean.getGname() + "-" + infoBean.getGiftname());
        gift_detail_time.setText("有效期：" + infoBean.getOvertime());
        gift_detail_num.setText("礼包剩余：" + infoBean.getExchanges());
        gift_explains.setText(infoBean.getExplains());
        gift_descs.setText(infoBean.getDescs());
        if (infoBean.getExchanges() == 0) {
            gift_bottom_get.setText("马上淘号");

        } else {

            gift_bottom_get.setText("立即领取");
        }

        String path = HttpUtils.BASE_URL + infoBean.getIconurl();
        Picasso.with(this).load(path).into(gift_detail_icon);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gift_back:
                Intent intent=new Intent(GiftDetailActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.gift_share:
                Intent intent2 = new Intent(Intent.ACTION_SEND);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent2.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(HttpUtils.BASE_URL+infoBean.getIconurl())));  //传输图片或者文件 采用流的方式
                intent2.setType("image/*");   //分享图片
                startActivity(Intent.createChooser(intent2,"分享"));
                break;
        }
    }
}
