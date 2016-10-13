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
import com.example.lcy.demo.adapter.OpenTestAdapter;
import com.example.lcy.demo.adapter.ThreeAdapter;
import com.example.lcy.demo.bean.TextBean;
import com.example.lcy.demo.bean.WeekThreeBean;
import com.example.lcy.demo.http.HttpUtils;
import com.example.lcy.demo.http.MyService;
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
public class ThreeFragment extends Fragment {

    @BindView(R.id.three_listView)
    PullToRefreshListView three_listView;
    int pageNo=5;
    ThreeAdapter adapter;
    private Context context;

    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_three, container, false);
        ButterKnife.bind(this,view);
        initData();

        three_listView.setMode(PullToRefreshBase.Mode.BOTH);
        three_listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ILoadingLayout proxy = three_listView.getLoadingLayoutProxy(true, false);
                        proxy.setRefreshingLabel("正在加载");
                        proxy.setLastUpdatedLabel("上次更新时间:");
                        three_listView.onRefreshComplete();
                    }
                },1000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ILoadingLayout proxy = three_listView.getLoadingLayoutProxy(false, true);
                        proxy.setRefreshingLabel("查看更多");
                        proxy.setReleaseLabel("松开载入更多");
                        three_listView.onRefreshComplete();
                    }
                },1000);
            }
        });
        return view;
    }

    private void initData() {
        MyService service = HttpUtils.getMyService();
        service.bdxqs(pageNo).enqueue(new Callback<WeekThreeBean>() {
            @Override
            public void onResponse(Call<WeekThreeBean> call, Response<WeekThreeBean> response) {
                WeekThreeBean bean=response.body();
                List<WeekThreeBean.ListBean>list=bean.getList();
                adapter=new ThreeAdapter(list,context);
                three_listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<WeekThreeBean> call, Throwable t) {

            }
        });

    }

}
