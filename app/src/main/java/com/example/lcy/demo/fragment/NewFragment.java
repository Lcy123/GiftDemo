package com.example.lcy.demo.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lcy.demo.R;
import com.example.lcy.demo.adapter.NewAdapter;
import com.example.lcy.demo.adapter.ThreeAdapter;
import com.example.lcy.demo.bean.NewGameBean;
import com.example.lcy.demo.bean.WeekThreeBean;
import com.example.lcy.demo.http.HttpUtils;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewFragment extends Fragment {

    @BindView(R.id.new_listView)
    PullToRefreshListView new_listView;
    int pageNo=2;
    NewAdapter adapter;

    public NewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_new, container, false);
        ButterKnife.bind(this,view);
        initData();

        new_listView.setMode(PullToRefreshBase.Mode.BOTH);
        new_listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ILoadingLayout proxy = new_listView.getLoadingLayoutProxy(true, false);
                        proxy.setRefreshingLabel("正在加载");
                        proxy.setLastUpdatedLabel("上次更新时间:");
                        new_listView.onRefreshComplete();
                    }
                },1000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ILoadingLayout proxy = new_listView.getLoadingLayoutProxy(false, true);
                        proxy.setRefreshingLabel("查看更多");
                        proxy.setReleaseLabel("松开载入更多");
                        new_listView.onRefreshComplete();
                    }
                },1000);
            }
        });
        return view;
    }

    private void initData() {
        HttpUtils.getMyService().getWeekll(pageNo).enqueue(new Callback<NewGameBean>() {
            @Override
            public void onResponse(Call<NewGameBean> call, Response<NewGameBean> response) {
                NewGameBean bean=response.body();
                List<NewGameBean.ListBean> list=bean.getList();
                adapter=new NewAdapter(list,getContext());
                new_listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<NewGameBean> call, Throwable t) {

            }
        });
    }

}
