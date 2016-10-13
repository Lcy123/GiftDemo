package com.example.lcy.demo.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.lcy.demo.R;
import com.example.lcy.demo.activity.OpenDetailActivity;
import com.example.lcy.demo.adapter.HotButtomAdapter;
import com.example.lcy.demo.adapter.HotTopAdapter;
import com.example.lcy.demo.bean.HotBean;
import com.example.lcy.demo.http.HttpUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment {

    @BindView(R.id.hot_listView)
    ListView hot_listView;
    @BindView(R.id.hot_gridView)
    GridView hot_gridView;

    Context context;

    public HotFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_hot, container, false);
        ButterKnife.bind(this,view);
        initData();
        return view;
    }

    private void initData() {
        HttpUtils.getMyService().hotpushForAndroid().enqueue(new Callback<HotBean>() {
            @Override
            public void onResponse(Call<HotBean> call, Response<HotBean> response) {
                HotBean bean=response.body();
                initList(bean);
                initGrid(bean);
            }

            @Override
            public void onFailure(Call<HotBean> call, Throwable t) {

            }
        });
    }

    private void initList(HotBean bean) {
        List<HotBean.InfoBean.Push1Bean>list=bean.getInfo().getPush1();
        HotTopAdapter listAdapter=new HotTopAdapter(list,context);
        hot_listView.setAdapter(listAdapter);

    }

    private void initGrid(HotBean bean) {
        List<HotBean.InfoBean.Push2Bean>grid=bean.getInfo().getPush2();
        HotButtomAdapter gridAdapter=new HotButtomAdapter(grid,context);
        hot_gridView.setAdapter(gridAdapter);


    }

}
