package com.example.lcy.demo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lcy.demo.R;
import com.example.lcy.demo.adapter.SearchAdapter;
import com.example.lcy.demo.bean.SearchBean;
import com.example.lcy.demo.http.HttpUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.search_back)
    ImageView search_back;
    @BindView(R.id.search_search)
    TextView search_search;
    @BindView(R.id.search_listView)
    ListView listView;
    @BindView(R.id.search_edit)
    EditText search_edit;

    SearchAdapter adapter;
    String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        HttpUtils.getMyService().searchGift(key).enqueue(new Callback<SearchBean>() {
            @Override
            public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
                SearchBean bean = response.body();
                //initView(bean);
                List<SearchBean.ListBean>list = bean.getList();
                adapter = new SearchAdapter(list,SearchActivity.this);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<SearchBean> call, Throwable t) {

            }
        });
    }

//    private void initView(SearchBean bean) {
//        List<SearchBean.ListBean>list = bean.getList();
//        adapter = new SearchAdapter(list, this);
//        listView.setAdapter(adapter);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_back:
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.search_search:
                key = search_edit.getText().toString();
                if (key.equals("")) {
                    Toast.makeText(SearchActivity.this, "无内容", Toast.LENGTH_SHORT).show();
                } else {
                    HttpUtils.getMyService().searchGift(key).enqueue(new Callback<SearchBean>() {
                        @Override
                        public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
                            SearchBean bean = response.body();
                            List<SearchBean.ListBean>list = bean.getList();
                            adapter = new SearchAdapter(list, SearchActivity.this);
                            listView.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<SearchBean> call, Throwable t) {

                        }
                    });
                }
                break;
        }
    }
}
