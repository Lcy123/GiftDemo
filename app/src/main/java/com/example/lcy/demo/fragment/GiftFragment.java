package com.example.lcy.demo.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lcy.demo.R;
import com.example.lcy.demo.activity.GiftDetailActivity;
import com.example.lcy.demo.activity.MainActivity;
import com.example.lcy.demo.adapter.GiftAdAdapter;
import com.example.lcy.demo.adapter.GiftListAdapter;
import com.example.lcy.demo.bean.GiftListBean;
import com.example.lcy.demo.http.HttpUtils;
import com.example.lcy.demo.http.MyService;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class GiftFragment extends Fragment {

    AutoScrollViewPager gift_pagerView;
    LinearLayout layout_point_container;
    List<View> viewList;
    PullToRefreshListView gift_list;
    ListView refreshableView;
    View view1;
    int page = 1;

    private Context context;

    public GiftFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gift, container, false);
        gift_list = (PullToRefreshListView) view.findViewById(R.id.gift_listView);
        view1 = LayoutInflater.from(getActivity()).inflate(R.layout.gift_head_item, null);
        gift_pagerView = (AutoScrollViewPager) view1.findViewById(R.id.gift_pagerView);
        layout_point_container = (LinearLayout) view1.findViewById(R.id.layout_point_container);

        initData();
        return view;
    }

    private void initData(){
        MyService service = HttpUtils.getMyService();
        service.getGiftList(page)
                .enqueue(new Callback<GiftListBean>() {
                    @Override
                    public void onResponse(Call<GiftListBean> call, Response<GiftListBean> response) {
                        GiftListBean bean = response.body();
                        initAd(bean);
                        initList(bean);
                    }
                    @Override
                    public void onFailure(Call<GiftListBean> call, Throwable t) {

                    }
                });
    }

    private void initAd(GiftListBean bean) {

        final List<GiftListBean.AdBean> adBeanList = bean.getAd();
        viewList = new ArrayList<>();
        for (int i = 0; i < adBeanList.size(); i++) {
            ImageView image = new ImageView(context);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            String picUrl = adBeanList.get(i).getIconurl();
            Picasso.with(context).load(HttpUtils.BASE_URL + picUrl).into(image);
            viewList.add(image);
            ImageView image_point = new ImageView(context);
            image_point.setPadding(10, 0, 10, 0);
            if (i == 0) {
                image_point.setImageResource(R.drawable.ad_point_select);
            } else {
                image_point.setImageResource(R.drawable.ad_point_default);
            }
            layout_point_container.addView(image_point);

        }
        GiftAdAdapter adAdapter = new GiftAdAdapter(viewList);
        //
        gift_pagerView.setAdapter(adAdapter);
        gift_pagerView.startAutoScroll();
        gift_pagerView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(final int position) {
                int count = layout_point_container.getChildCount();
                for (int i = 0; i < count; i++) {
                    ImageView img = (ImageView) layout_point_container.getChildAt(i);
                    if (position == i) {
                        img.setImageResource(R.drawable.ad_point_select);
                    } else {
                        img.setImageResource(R.drawable.ad_point_default);
                    }
                }
                ImageView imageView = (ImageView) viewList.get(position);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //传viewpager轮播的giftid
                        String giftid = adBeanList.get(position).getGiftid();
                        Intent intent = new Intent(context, GiftDetailActivity.class);
                        intent.putExtra("id", giftid);
                        context.startActivity(intent);
                    }
                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initList(GiftListBean bean) {
        List<GiftListBean.ListBean> list = bean.getList();
        GiftListAdapter listAdapter = new GiftListAdapter(list, getActivity());
        //获取内部封装的listview
        refreshableView=gift_list.getRefreshableView();
        //添加头部视图
        refreshableView.addHeaderView(view1);
        refreshableView.setAdapter(listAdapter);

        gift_list.setMode(PullToRefreshBase.Mode.BOTH);
        gift_list.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ILoadingLayout proxy = gift_list.getLoadingLayoutProxy(true, false);
                        proxy.setRefreshingLabel("正在加载");
                        proxy.setLastUpdatedLabel("上次更新时间:");
                        gift_list.onRefreshComplete();
                    }
                },1000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ILoadingLayout proxy = gift_list.getLoadingLayoutProxy(false, true);
                        proxy.setRefreshingLabel("查看更多");
                        gift_list.onRefreshComplete();
                    }
                },1000);
            }
        });

    }



}
