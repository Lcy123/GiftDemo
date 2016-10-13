package com.example.lcy.demo.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.lcy.demo.R;
import com.example.lcy.demo.bean.OpenDetailBean;
import com.example.lcy.demo.http.HttpUtils;
import com.example.lcy.demo.http.MyService;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpenDetailActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.open_detail_back2)
    ImageView open_detail_back2;
    @BindView(R.id.open_detail_title)
    TextView open_detail_title;

    @BindView(R.id.open_detail_gameTest)
    TextView open_detail_gameTest;

    @BindView(R.id.open_detail_down)
    Button open_detail_down;

    @BindView(R.id.open_detail_icon)
    ImageView open_detail_icon;
    @BindView(R.id.open_detail_gname)
    TextView open_detail_gname;
    @BindView(R.id.open_detail_type)
    TextView open_detail_type;
    @BindView(R.id.open_detail_size)
    TextView open_detail_size;

    @BindView(R.id.open_detail_images)
    LinearLayout linearLayout;

    LinearLayout.LayoutParams params;
    String id;
    OpenDetailBean.AppBean appbean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_detail);
        ButterKnife.bind(this);
        open_detail_back2.setOnClickListener(this);

        Display display=getWindowManager().getDefaultDisplay();
        int width=display.getWidth()/2;
        int height=700;

        params=new LinearLayout.LayoutParams(width,height);
        params.setMargins(0,0,0,0);

        id = getIntent().getStringExtra("id");

        MyService service = HttpUtils.getMyService();
        service.getAppInfo(id).enqueue(new Callback<OpenDetailBean>() {
            @Override
            public void onResponse(Call<OpenDetailBean> call, Response<OpenDetailBean> response) {
                OpenDetailBean bean = response.body();
                initView(bean);
            }

            @Override
            public void onFailure(Call<OpenDetailBean> call, Throwable t) {

            }
        });
    }

    private void initView(OpenDetailBean bean) {

        appbean = bean.getApp();
        open_detail_title.setText(appbean.getName());
        Picasso.with(this).load(HttpUtils.BASE_URL + appbean.getLogo()).into(open_detail_icon);
        open_detail_gname.setText(appbean.getName());
        open_detail_type.setText("类型：" + appbean.getTypename());
        if (appbean.getAppsize() != null && !appbean.getAppsize().equals("")) {
            open_detail_size.setText("大小：" + appbean.getAppsize());
        } else {
            open_detail_size.setText("大小: 未知");
        }
        open_detail_gameTest.setText(appbean.getDescription());

        if (appbean.getDownload_addr().equals("")){
            open_detail_down.setText("暂无下载");
            open_detail_down.setBackgroundResource(R.color.colorDark);
            open_detail_down.setClickable(false);
        }else {
            open_detail_down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1=new Intent();
                    intent1.setAction(Intent.ACTION_VIEW);
                    intent1.setData(Uri.parse(appbean.getDownload_addr()));
                    startActivity(intent1);
                }
            });

        }

        List<OpenDetailBean.ImgBean> images = bean.getImg();
        for (int i=0;i<images.size();i++){
        ImageView img=new ImageView(this);
         img.setScaleType(ImageView.ScaleType.CENTER_CROP);
         img.setLayoutParams(params);
        Picasso.with(this).load(HttpUtils.BASE_URL+images.get(i).getAddress())
                .into(img);
          linearLayout.addView(img);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_detail_back2:
                Intent intent = new Intent(OpenDetailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.open_detail_share:
                Intent intent2 = new Intent(Intent.ACTION_SEND);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //传输图片或者文件 采用流的方式
                intent2.putExtra(Intent.EXTRA_STREAM, Uri.parse(HttpUtils.BASE_URL+appbean.getLogo()));
                intent2.setType("image/*");   //分享图片
                startActivity(Intent.createChooser(intent2,"分享"));
                break;
        }
    }
}
